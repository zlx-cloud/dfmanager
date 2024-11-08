package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 菜单
 */
@Data
@TableName(value = "DFMANAGER.T_TARGET_CHECK_RULE")
public class TargetCheckRule {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 规则编码
    @TableField("RULE_CODE")
    private String ruleCode;

    // 规则名称
    @TableField("RULE_NAME")
    private String ruleName;

    // 参数类型
    @TableField("PARAM_TYPE")
    private String paramType;

    // 是否必填
    @TableField("IS_NULL")
    private String isNull;

    // 参数格式
    @TableField("PARAM_FORMAT")
    private String paramFormat;

    // 参数范围-起
    @TableField("PARAM_RANGE_START")
    private String paramRangeStart;

    // 参数范围-止
    @TableField("PARAM_RANGE_END")
    private String paramRangeEnd;

    // 参数枚举
    @TableField("PARAM_ENUM")
    private String paramEnum;

    // 指标逻辑表达式的校验规则
    @TableField("EXPRESSION_RULE")
    private String expressionRule;

    // 规则描述
    @TableField("RULE_DESC")
    private String ruleDesc;

    // 规则状态
    @TableField("RULE_STATUS")
    private String ruleStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}