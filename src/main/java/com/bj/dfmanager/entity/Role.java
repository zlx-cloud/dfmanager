package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 角色
 */
@Data
@TableName(value = "DFMANAGER.T_ROLE")
public class Role {

    @TableId(type = IdType.AUTO)
    private Integer roleId;

    // 角色名称
    @TableField("ROLE_NAME")
    private String roleName;

    // 角色描述
    @TableField("ROLE_DESC")
    private String roleDesc;

    // 角色状态
    @TableField("ROLE_STATUS")
    private String roleStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}