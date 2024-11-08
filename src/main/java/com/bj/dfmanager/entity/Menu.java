package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 菜单
 */
@Data
@TableName(value = "DFMANAGER.T_MENU")
public class Menu {

    // 菜单ID
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 上级菜单ID
    @TableField("PID")
    private Integer pid;

    // 菜单名称
    @TableField("NAME")
    private String name;

    // 访问路径
    @TableField("URL")
    private String url;

    // 是否选中
    @TableField(exist = false)
    private Boolean checked;

    // 子菜单
    @TableField(exist = false)
    private List<Menu> children;

}
