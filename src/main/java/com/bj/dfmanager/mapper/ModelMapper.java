package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Model;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ModelMapper extends BaseMapper<Model> {

    IPage<Model> queryList(@Param("userId") String userId,
                           @Param("modelName") String modelName,
                           @Param("modelStatus") String modelStatus,
                           Page<Object> objectPage);

    @Select("SELECT m.*,mg.GRADE AS GRADE_ID,tkv.VALUE1 AS GRADE_NAME,u.REAL_NAME,a.FULL_NAME as department_name," +
            "au.REAL_NAME AS APPLY_USER_NAME FROM T_MODEL m " +
            "LEFT JOIN T_MODEL_GRADE mg on m.ID = mg.MODEL_ID " +
            "LEFT JOIN (SELECT TO_NUMBER(KEY) AS KEY,VALUE1 FROM F_TASK_KEY_VALUE WHERE TYPE='DJFL') tkv " +
            "ON tkv.KEY = mg.GRADE " +
            "LEFT JOIN T_USER u ON u.USER_ID = m.USER_ID " +
            "LEFT JOIN T_DEPT A ON m.DEPARTMENT = A.ID " +
            "LEFT JOIN T_USER au ON au.USER_ID = m.APPLY_USER " +
            "WHERE m.ID = #{id}")
    Model selectModelById(@Param("id") Integer id);

    IPage<Model> queryPublicList(@Param("modelName") String modelName,
                                 @Param("modelStatus") String modelStatus,
                                 Page<Object> objectPage);

}
