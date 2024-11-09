package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.MonitorTaskError;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface MonitorTaskErrorMapper extends BaseMapper<MonitorTaskError> {

    /**
     * 异常信息
     */
    @Select("SELECT TASK_DESC,COUNT(1) COUNT FROM T_MONITOR_TASK_ERROR " +
            "GROUP BY TASK_DESC ORDER BY COUNT DESC,TASK_DESC")
    List<Map<String, Object>> errorInfo();

    /**
     * 访问频次
     */
    @Select("SELECT A.MODEL_ID,B.MODEL_NAME,SUM(A.TOTAL) COUNT FROM T_MODEL_RESULT_COUNT A " +
            "LEFT JOIN T_MODEL B ON A.MODEL_ID = B.ID " +
            "WHERE TO_CHAR(A.BUSI_DATE,'YYYYMMDD') = CURDATE() " +
            "GROUP BY A.MODEL_ID,B.MODEL_NAME ORDER BY COUNT DESC,A.MODEL_ID LIMIT 10")
    List<Map<String, Object>> frequency();

    /**
     * 访问频次全部
     */
    @Select("SELECT A.MODEL_ID,B.MODEL_NAME,SUM(A.TOTAL) COUNT FROM T_MODEL_RESULT_COUNT A " +
            "LEFT JOIN T_MODEL B ON A.MODEL_ID = B.ID " +
            "WHERE TO_CHAR(A.BUSI_DATE,'YYYYMMDD') = CURDATE() " +
            "GROUP BY A.MODEL_ID,B.MODEL_NAME ORDER BY COUNT DESC,A.MODEL_ID")
    List<Map<String, Object>> frequencyAll();

    /**
     * 响应时长
     */
    @Select("SELECT A.MODEL_ID,B.MODEL_NAME,ROUND(SUM(A.DURATION)/COUNT(1),0) VALUE " +
            "FROM T_MODEL_RESULT_COUNT A LEFT JOIN T_MODEL B ON A.MODEL_ID = B.ID " +
            "WHERE A.BUSI_DATE = TO_CHAR(TRUNC(SYSDATE),'YYYYMMDD') " +
            "GROUP BY A.MODEL_ID,B.MODEL_NAME ORDER BY VALUE DESC,A.MODEL_ID LIMIT 10")
    List<Map<String, Object>> responseTime();

    /**
     * 响应时长全部
     */
    @Select("SELECT A.MODEL_ID,B.MODEL_NAME,ROUND(SUM(A.DURATION)/COUNT(1),0) VALUE " +
            "FROM T_MODEL_RESULT_COUNT A LEFT JOIN T_MODEL B ON A.MODEL_ID = B.ID " +
            "WHERE A.BUSI_DATE = TO_CHAR(TRUNC(SYSDATE),'YYYYMMDD') " +
            "GROUP BY A.MODEL_ID,B.MODEL_NAME ORDER BY VALUE DESC,A.MODEL_ID")
    List<Map<String, Object>> responseTimeAll();

    /**
     * 异常信息预警
     */
    @Select("SELECT * FROM T_MONITOR_TASK_ERROR WHERE STASTUS = '0' ORDER BY ERROR_TIME DESC LIMIT 5")
    List<MonitorTaskError> exceptionInfoWarn();

}