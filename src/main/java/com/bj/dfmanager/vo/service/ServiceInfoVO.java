package com.bj.dfmanager.vo.service;

import lombok.Data;

/**
 * 服务目录 VO
 */
@Data
public class ServiceInfoVO {

    // ID
    private Integer id;

    // 服务目录菜单ID
    private Integer serviceMenuId;

    // 服务编码
    private String serviceCode;

    // 服务名称
    private String serviceName;

    // 服务URL
    private String serviceUrl;

    // 服务描述
    private String serviceDesc;

    // 服务API
    private String serviceApi;

    // 服务状态
    private String serviceStatus;

}