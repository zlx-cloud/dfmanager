package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bj.dfmanager.config.HybaseTable;
import com.bj.dfmanager.entity.HybaseTableInfo;
import com.bj.dfmanager.entity.MonitorTask;
import com.bj.dfmanager.mapper.MonitorTaskMapper;
import com.bj.dfmanager.service.BgMonitorService;
import com.bj.dfmanager.vo.common.Result;
import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.TRSException;
import com.trs.hybase.client.TRSRecord;
import com.trs.hybase.client.TRSResultSet;
import com.trs.hybase.client.params.ConnectParams;
import com.trs.hybase.client.params.SearchParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BgMonitorServiceImpl implements BgMonitorService {

    @Value("${hybase.url}")
    private String trsUrl;
    @Value("${hybase.username}")
    private String trsUserName;
    @Value("${hybase.password}")
    private String trsPassword;

    @Resource
    private HybaseTable hybaseTable;
    @Resource
    private MonitorTaskMapper monitorTaskMapper;

    private TRSConnection trsConn = null;

    private void getTrsConn() {
        if (trsConn == null)
            trsConn = new TRSConnection(trsUrl, trsUserName, trsPassword, new ConnectParams());
    }

    private void closeTrsConn() {
        if (trsConn != null) {
            trsConn.close();
            trsConn = null;
        }
    }

    /**
     * 服务节点监控
     */
    @Override
    public Result serviceNodeMonitor() {
        List<MonitorTask> list = monitorTaskMapper.serviceNodeMonitor();
        return Result.success(list, "查询服务节点监控信息成功");
    }

    /**
     * 数据窗口检测
     */
    @Override
    public Result dataMonitor() {
        // 返回结果
        List<Map<String, Object>> result = new ArrayList<>();

        // 获取连接
        getTrsConn();

        // 获取配置
        List<HybaseTableInfo> tables = hybaseTable.getTables();
        for (HybaseTableInfo table : tables) {
            // 查询
            Map<String, Object> map = queryHybaseData(table);
            result.add(map);
        }

        // 关闭连接
        closeTrsConn();

        return Result.success(result, "查询成功");
    }

    // 查询
    private Map<String, Object> queryHybaseData(HybaseTableInfo table) {
        Map<String, Object> map = new HashMap<>();
        map.put("tableName", table.getTableName());
        map.put("tableComment", table.getTableComment());

        SearchParams param = new SearchParams();
        param.setSortMethod("-" + table.getEtlDate());

        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(table.getEtlDate())) {
            sb.append(table.getEtlDate()).append(";");
        }
        if (StringUtils.isNotEmpty(table.getBusinessDate())) {
            sb.append(table.getBusinessDate()).append(";");
        }
        String readColumns = sb.substring(0, sb.length() - 1);
        param.setReadColumns(readColumns);

        try {
            TRSResultSet resultSet = trsConn.executeSelect(table.getTableName(), "", 0, 1, param);
            while (resultSet.moveNext()) {
                TRSRecord re = resultSet.get();
                if (StringUtils.isNotEmpty(table.getEtlDate())) {
                    map.put("etlDate", re.getString(table.getEtlDate()));
                }
                if (StringUtils.isNotEmpty(table.getBusinessDate())) {
                    map.put("businessDate", re.getString(table.getBusinessDate()));
                }
            }
        } catch (TRSException e) {
            e.printStackTrace();
            map.put("etlDate", "");
            map.put("businessDate", "");
        }
        return map;
    }

    @Override
    public Result serviceNodeMonitorList() {
        List<MonitorTask> list = monitorTaskMapper.serviceNodeMonitorList();
        return Result.success(list, "查询服务节点监控信息列表成功");
    }

}