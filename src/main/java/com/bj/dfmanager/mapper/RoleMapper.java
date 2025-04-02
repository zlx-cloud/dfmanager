package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询用户已拥有的角色
     */
    @Select("SELECT R.* FROM T_USER_ROLE UR LEFT JOIN T_ROLE R ON UR.ROLE_ID = R.ROLE_ID " +
            "WHERE UR.USER_ID = #{userId} AND R.ROLE_STATUS = 'Y' ORDER BY CREATE_TIME DESC")
    List<Role> userHaveRole(@Param("userId") Integer userId);

    /**
     * 查询用户已拥有的角色
     */
    @Select("SELECT R.ROLE_ID FROM T_USER_ROLE UR LEFT JOIN T_ROLE R ON UR.ROLE_ID = R.ROLE_ID " +
            "WHERE UR.USER_ID = #{userId} AND R.ROLE_STATUS = 'Y'")
    List<Integer> userHaveRoleId(@Param("userId") Integer userId);

    /**
     * 查询用户未拥有的角色
     */
    @Select("SELECT * FROM T_ROLE R WHERE R.ROLE_STATUS ='Y' AND R.ROLE_ID NOT IN " +
            "(SELECT UR.ROLE_ID FROM T_USER_ROLE UR WHERE USER_ID = #{userId}) ORDER BY R.CREATE_TIME DESC")
    List<Role> userNotHaveRole(@Param("userId") Integer userId);

}
