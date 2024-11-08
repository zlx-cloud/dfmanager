package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 角色菜单
     */
    @Select("SELECT M.*,CASE WHEN RM.MENU_ID IS NOT NULL THEN TRUE ELSE FALSE END AS CHECKED FROM T_MENU M " +
            "LEFT JOIN T_ROLE_MENU RM ON M.ID = RM.MENU_ID AND RM.ROLE_ID = #{roleId}")
    List<Menu> getRoleMenu(@Param("roleId") Integer roleId);

    /**
     * 用户菜单
     */
    @Select("SELECT M.*,TRUE AS CHECKED FROM T_MENU M WHERE M.ID IN (SELECT DISTINCT MENU_ID FROM T_ROLE_MENU RM WHERE RM.ROLE_ID " +
            "IN (SELECT UR.ROLE_ID FROM T_USER_ROLE UR WHERE UR.USER_ID = #{userId}))")
    List<Menu> getUserMenu(@Param("userId") Integer userId);

}