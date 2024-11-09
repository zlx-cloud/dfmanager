package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ModelResultMapper extends BaseMapper<ModelResult> {

    /**
     * 模型结果查询
     */
    IPage<ModelResult> queryResultList(@Param("modelName") String modelName,
                                       @Param("warnFlag") String warnFlag,
                                       @Param("certNo") String certNo,
                                       @Param("fltno") String fltno,
                                       @Param("fltDateStart") String fltDateStart,
                                       @Param("fltDateEnd") String fltDateEnd,
                                       Page<ModelResult> page);

    /**
     * 模型结果查询
     */
    @Select("SELECT RESULT_DETAIL FROM T_MODEL_RESULT WHERE ID = #{id}")
    String queryResultDetail(@Param("id") String id);

    /**
     * 当日服务次数
     */
    @Select("SELECT IFNULL(SUM(TOTAL),0) FROM T_MODEL_RESULT_COUNT WHERE BUSI_DATE = TO_CHAR(TRUNC(SYSDATE),'YYYYMMDD')")
    int todayServiceCount();

    /**
     * 特定模型当日服务次数
     */
    @Select("SELECT IFNULL(SUM(TOTAL),0) FROM T_MODEL_RESULT_COUNT WHERE BUSI_DATE = TO_CHAR(TRUNC(SYSDATE),'YYYYMMDD') " +
            "AND MODEL_ID = #{id}")
    int modelTodayServiceCount(@Param("id") String id);

    /**
     * 服务次数趋势
     */
    List<Map<String, Integer>> modelCountTrend(@Param("id") String id);

    /**
     * 响应时长趋势
     */
    List<Map<String, Object>> modelTimeTrend(@Param("id") String id);

    /**
     * 监控接口的正确率和错误率
     */
    @Select("SELECT IFNULL(SUM(TOTAL),0) FROM T_MODEL_RESULT_COUNT " +
            "WHERE BUSI_DATE = TO_CHAR(TRUNC(SYSDATE),'YYYYMMDD') AND ERROR_FLAG = #{errorFlag}")
    int queryCountByErrorFlag(@Param("errorFlag") String errorFlag);

    /**
     * 磁盘监控
     */
    @Select("SELECT * FROM T_MONITOR_DISK")
    List<Map<String, Object>> diskMonitor();

}