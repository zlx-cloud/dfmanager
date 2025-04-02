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
    //private String developer;

    // 需求状态
    private String demandStatus;

    // 建模背景/用途
    private String background;

    // 模型需求描述
    private String demandDesc;

    // 核查策略
    private String checkStrategy;

    // 模型预期上线时间
    private String onlineTime;

    // 模型预期下线时间
    private String offlineTime;

    // 申请人姓名
    // private String applyUser;

    // 申请人单位
    private String applyDepartment;

    // 申请人联系方式
    private String applyContact;

    // 建模申请类型：公开 不公开
    private String applyType;

    // 0-暂存 1-保存
    //private String tempStore;

    // 退回意见
    private String advice;

}
