package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.Target;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TargetMapper extends BaseMapper<Target> {

    /**
     * 查询用户系统已拥有的指标
     */
    @Select("SELECT T.* FROM T_SYSTEM_TARGET_REL STR LEFT JOIN T_TARGET T ON STR.TARGET_ID = T.ID " +
            "WHERE STR.SYSTEM_ID = #{id} AND T.TARGET_STATUS = 'Y' ORDER BY T.CREATE_TIME DESC")
    List<Target> userSystemHaveTarget(@Param("id") Integer id);

    /**
     * 查询用户系统未拥有的指标
     */
    @Select("SELECT T.* FROM T_TARGET T WHERE T.TARGET_STATUS ='Y' AND T.ID NOT IN " +
            "(SELECT STR.TARGET_ID FROM T_SYSTEM_TARGET_REL STR WHERE STR.SYSTEM_ID = #{id}) ORDER BY T.CREATE_TIME DESC")
    List<Target> userSystemNotHaveTarget(@Param("id") Integer id);

    /**
     * 根据启停状态查询指标数量
     */
    @Select("SELECT COUNT(1) FROM T_TARGET WHERE TARGET_STATUS = #{targetStatus}")
    int queryTargetCountByStatus(@Param("targetStatus") String targetStatus);
}