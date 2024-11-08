package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色菜单
 */
@Data
@TableName(value = "DFMANAGER.T_ROLE_MENU")
public class RoleMenu {

    // 角色ID
    @TableField("ROLE_ID")
    private Integer roleId;

    // 菜单ID
    @TableField("MENU_ID")
    private Integer menuId;

}
