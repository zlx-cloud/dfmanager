package com.bj.dfmanager.vo.targetcheckrule;

import lombok.Data;

@Data
public class TargetCheckRuleVO {

    private Integer id;

    // 规则编码
    private String ruleCode;

    // 规则名称
    private String ruleName;

    // 参数类型
    private String paramType;

    // 是否必填
    private String isNull;

    // 参数格式
    private String paramFormat;

    // 参数范围-起
    private String paramRangeStart;

    // 参数范围-止
    private String paramRangeEnd;

    // 参数枚举
    private String paramEnum;

    // 指标逻辑表达式的校验规则
    private String expressionRule;

    // 规则描述
    private String ruleDesc;

    // 规则状态
    private String ruleStatus;

}