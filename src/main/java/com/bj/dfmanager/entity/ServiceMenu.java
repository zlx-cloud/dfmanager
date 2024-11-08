package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 服务目录菜单
 */
@Data
@TableName(value = "DFMANAGER.T_SERVICE_MENU")
public class ServiceMenu {

    // 菜单ID
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 上级菜单ID
    @TableField("PID")
    private Integer pid;

    // 菜单名称
    @TableField("NAME")
    private String name;

    // 子菜单
    @TableField(exist = false)
    private List<ServiceMenu> children;

}