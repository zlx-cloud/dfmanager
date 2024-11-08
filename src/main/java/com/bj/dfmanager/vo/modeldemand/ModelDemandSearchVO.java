package com.bj.dfmanager.vo.modeldemand;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模型需求查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModelDemandSearchVO extends PageVO {

    // 模型名称
    private String modelName;

    // 模型类型
    private String modelType;

    // 使用范围
    private String useScope;

    // 需求单位
    private String demandUnit;

    // 开发人员
    private String developer;

    // 需求状态
    private String demandStatus;

}