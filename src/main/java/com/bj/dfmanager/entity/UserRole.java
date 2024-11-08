package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色
 */
@Data
@TableName(value = "DFMANAGER.T_USER_ROLE")
public class UserRole {

    // 用户ID
    @TableField("USER_ID")
    private Integer userId;

    // 角色ID
    @TableField("ROLE_ID")
    private Integer roleId;

}