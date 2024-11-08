package com.bj.dfmanager.vo.role;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleSearchVO extends PageVO {

    // 角色名称
    private String roleName;

    // 角色状态
    private String roleStatus;

}