package com.bj.dfmanager.vo.role;

import lombok.Data;

/**
 * 用户 VO
 */
@Data
public class RoleVO {

    private Integer roleId;

    // 角色名称
    private String roleName;

    // 角色描述
    private String roleDesc;

    // 角色状态
    private String roleStatus;

}