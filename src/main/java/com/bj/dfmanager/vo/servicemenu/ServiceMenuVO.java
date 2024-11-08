package com.bj.dfmanager.vo.servicemenu;

import lombok.Data;

/**
 * 服务目录菜单 VO
 */
@Data
public class ServiceMenuVO {

    // 菜单ID
    private Integer id;

    // 上级菜单ID
    private Integer pid;

    // 菜单名称
    private String name;

}