package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ModelMapper extends BaseMapper<Model> {

    IPage<Model> queryList(@Param("modelName") String modelName,
                           @Param("modelStatus") String modelStatus,
                           Page<Object> objectPage);

    @Select("SELECT m.*,mg.GRADE AS GRADE_ID,tkv.VALUE1 AS GRADE_NAME,u.REAL_NAME FROM T_MODEL m " +
            "LEFT JOIN T_MODEL_GRADE mg on m.ID = mg.MODEL_ID " +
            "LEFT JOIN (SELECT TO_NUMBER(KEY) AS KEY,VALUE1 FROM F_TASK_KEY_VALUE WHERE TYPE='DJFL') tkv " +
            "ON tkv.KEY = mg.GRADE " +
            "LEFT JOIN T_USER u ON u.USER_ID = m.USER_ID "+
            "WHERE m.ID = #{id}")
    Model selectModelById(@Param("id") Integer id);

}