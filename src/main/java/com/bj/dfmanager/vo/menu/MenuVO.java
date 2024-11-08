package com.bj.dfmanager.vo.menu;

import lombok.Data;

import java.util.List;

/**
 * 菜单 VO
 */
@Data
public class MenuVO {

    // 角色ID
    private Integer roleId;

    // 菜单ID
    private List<Integer> menuList;

}