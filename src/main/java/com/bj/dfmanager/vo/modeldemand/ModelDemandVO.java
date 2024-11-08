package com.bj.dfmanager.vo.modeldemand;

import lombok.Data;

/**
 * 模型需求 VO
 */
@Data
public class ModelDemandVO {

    private Integer id;

    // 模型名称
    private String modelName;

    // 模型类型
    private String modelType;

    // 模型需求
    private String modelDemand;

    // 使用范围
    private String useScope;

    // 需求单位
    private String demandUnit;

    // 开发人员
    private String developer;

    // 需求状态
    private String demandStatus;

}