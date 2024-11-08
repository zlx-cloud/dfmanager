package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 模板
 */
@Data
@TableName(value = "DFMANAGER.T_TEMPLATE")
public class Template {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 标题
    @TableField("TITLE")
    private String title;

    // 描述
    @TableField("DESCRIPTION")
    private String description;

    // 代码
    @TableField("CODE")
    private String code;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}