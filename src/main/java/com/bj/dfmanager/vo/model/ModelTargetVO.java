package com.bj.dfmanager.vo.model;

import lombok.Data;

/**
 * 模型指标 VO
 */
@Data
public class ModelTargetVO {

    // 模型ID
    private Integer modelId;

    // 指标ID
    private Integer targetId;

    // 运算符
    private String operator;

    // 值域
    private String valueRange;

    // 编码
    private String code;

    // 规则id
    private Integer ruleId;

}