package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 模型下指标
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL_TARGET")
public class ModelTarget {

    // 模型ID
    @TableField("MODEL_ID")
    private Integer modelId;

    // 指标ID
    @TableField("TARGET_ID")
    private Integer targetId;

    // 运算符
    @TableField("OPERATOR")
    private String operator;

    // 值域
    @TableField("VALUE_RANGE")
    private String valueRange;

    // 编码
    @TableField("CODE")
    private String code;

    // 规则ID
    @TableField("RULE_ID")
    private Integer ruleId;

}