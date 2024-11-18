package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 指标
 */
@Data
@TableName(value = "DFMANAGER.T_TARGET")
public class Target {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 指标编码
    @TableField("TARGET_CODE")
    private String targetCode;

    // 指标名称
    @TableField("TARGET_NAME")
    private String targetName;

    // 请求方式
    @TableField("CALL_METHOD")
    private String callMethod;

    // 同步/异步
    @TableField("ASYNC")
    private String async;

    // 指标参数
    @TableField("TARGET_PARAM")
    private String targetParam;

    // 返回数据结构
    @TableField("RETURN_STRUCTURE")
    private String returnStructure;

    // 返回数据模式
    @TableField("RETURN_MODEL")
    private String returnModel;

    // 指标逻辑
    @TableField("TARGET_LOGIC")
    private String targetLogic;

    // 指标描述
    @TableField("TARGET_DESC")
    private String targetDesc;

    // 适用范围
    @TableField("APPLICABLE_SCOPE")
    private String applicableScope;

    // 指标状态
    @TableField("TARGET_STATUS")
    private String targetStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    // 请求方法
    @TableField("REQUEST_METHOD")
    private String requestMethod;

}