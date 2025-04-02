package com.bj.dfmanager.vo.target;

import lombok.Data;

/**
 * 指标 VO
 */
@Data
public class TargetVO {

    private Integer id;

    // 指标编码
    private String targetCode;

    // 指标名称
    private String targetName;

    // 请求方式
    private String callMethod;

    // 同步/异步
    private String async;

    // 指标参数
    private String targetParam;

    // 返回数据结构
    private String returnStructure;

    // 返回数据模式
    private String returnModel;

    // 指标逻辑
    private String targetLogic;

    // 指标描述
    private String targetDesc;

    // 适用范围
    private String applicableScope;

    // 指标状态
    private String targetStatus;

    // 人员ID
    private String persId;

    private String birthDay;

    private String countryCode;

    private String certNo;

    private Object data;

    private String gender;

    // 请求方法
    private String requestMethod;

    private String targetReasonCreated;

    private String targetInstructions;

}
