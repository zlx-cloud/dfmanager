package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.MonitorTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MonitorTaskMapper extends BaseMapper<MonitorTask> {

    /**
     * 更新监控任务结果
     */
    @Update("UPDATE DFMANAGER.T_MONITOR_TASK SET MONITOR_STATUS = #{monitorStatus},TASK_TIME = #{taskTime}," +
            "UPDATE_TIME = SYSDATE WHERE TASK_CODE = #{taskCode}")
    int updateMonitorInfo(@Param("monitorStatus") String monitorStatus,
                           @Param("taskTime") String taskTime,
                           @Param("taskCode") String taskCode);

    /**
     * 查询监控信息（探测用）
     */
    @Select("SELECT COUNT(1) FROM DFMANAGER.T_MONITOR_TASK WHERE TASK_CODE = #{taskCode}")
    int queryCountByTaskCode(@Param("taskCode") String taskCode);

    /**
     * 服务节点监控
     */
    @Select("SELECT * FROM DFMANAGER.T_MONITOR_TASK WHERE TYPE = 'PICTURE' ORDER BY SORT")
    List<MonitorTask> serviceNodeMonitor();

    /**
     * 服务节点监控列表
     */
    @Select("SELECT * FROM DFMANAGER.T_MONITOR_TASK ORDER BY SORT")
    List<MonitorTask> serviceNodeMonitorList();

}