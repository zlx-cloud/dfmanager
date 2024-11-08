package com.bj.dfmanager.vo.service;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务目录查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceInfoSearchVO extends PageVO {

    // 服务目录菜单ID
    private Integer serviceMenuId;

    // 服务编码
    private String serviceCode;

    // 服务名称
    private String serviceName;

    // 服务URL
    private String serviceUrl;

    // 服务状态
    private String serviceStatus;

}