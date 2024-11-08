package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

}