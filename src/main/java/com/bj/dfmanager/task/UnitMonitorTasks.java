package com.bj.dfmanager.task;

import com.bj.dfmanager.mapper.MonitorTaskMapper;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * 组件监控任务
 */
@Component
@Slf4j
public class UnitMonitorTasks {

    @Value("${mq.host}")
    private String mqHost;
    @Value("${mq.port}")
    private Integer mqPort;
    @Value("${mq.username}")
    private String mqUserName;
    @Value("${mq.password}")
    private String mqPassword;
    @Value("${mq.timeout}")
    private int mqTimeOut;

    @Value("${kafka.server}")
    private String kafkaServer;
    @Value("${kafka.timeout}")
    private String kafkaTimeOut;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private MonitorTaskMapper monitorTaskMapper;

    /**
     * redis监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void redisMonitor() {
        // 任务状态
        String monitorStatus = "0";
        try {
            redisTemplate.opsForValue().set("unit_monitor_task_redis", "redis");
            monitorStatus = "1";
        } catch (Exception e) {
            e.printStackTrace();
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "redis");
    }

    /**
     * kafka监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void kafkaMonitor() {
        // 任务状态
        String monitorStatus = "0";

        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, kafkaTimeOut);
        properties.put(AdminClientConfig.DEFAULT_API_TIMEOUT_MS_CONFIG, kafkaTimeOut);

        AdminClient adminClient = AdminClient.create(properties);

        try {
            DescribeClusterResult result = adminClient.describeCluster();
            result.nodes().get();
            monitorStatus = "1";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            adminClient.close();
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "kafka");
    }

    /**
     * rabbitmq监控
     */
    @Scheduled(fixedDelayString = "${task.fixedDelay}")
    public void rabbitMqMonitor() {
        // 任务状态
        String monitorStatus = "0";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(mqHost);
        factory.setPort(mqPort);
        factory.setUsername(mqUserName);
        factory.setPassword(mqPassword);
        factory.setConnectionTimeout(mqTimeOut);

        try {
            Connection connection = factory.newConnection();
            log.info("=============================rabbitmq连接状态：{}", connection.isOpen());
            if (connection.isOpen()) {
                monitorStatus = "1";
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        monitorTaskMapper.updateMonitorInfo(monitorStatus, null, "rabbitmq");
    }

}