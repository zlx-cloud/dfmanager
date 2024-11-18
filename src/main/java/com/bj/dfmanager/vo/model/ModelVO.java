package com.bj.dfmanager.vo.model;

import lombok.Data;

import java.util.List;

/**
 * 模型 VO
 */
@Data
public class ModelVO {

    private Integer id;

    // 模型名称
    private String modelName;

    // 模型描述
    private String modelDesc;

    // 应用场景
    private String applicationScenarios;

    // 有效期
    private String effectiveDate;

    // 模型指标
    private List<ModelTargetVO> modelTargetList;

    // 预警规则
    private String warnCondition;

    // 进入条件状态
    private String inConditionStatus;

    // 进入条件
    private String inCondition;

    // 排他规则状态
    private String exConditionStatus;

    // 排他规则
    private String exCondition;

    // 模型状态
    private String modelStatus;

    // 预警状态
    private String warnFlag;

    // 等级分类ID
    private Integer gradeId;

}