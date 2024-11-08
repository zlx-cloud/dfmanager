package com.bj.dfmanager.util;

import com.bj.dfmanager.entity.Menu;
import com.bj.dfmanager.entity.ServiceMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuUtils {

    /**
     * 菜单层级
     */
    public static List<Menu> getMenuChildren(List<Menu> menuList, Integer pid) {
        List<Menu> menuTree = new ArrayList<>();
        for (Menu menu : menuList) {
            if (pid.equals(menu.getPid())) {
                menu.setChildren(getMenuChildren(menuList, menu.getId()));
                menuTree.add(menu);
            }
        }
        return menuTree;
    }

    /**
     * 菜单层级
     */
    public static List<ServiceMenu> getServiceMenuChildren(List<ServiceMenu> menuList, Integer pid) {
        List<ServiceMenu> menuTree = new ArrayList<>();
        for (ServiceMenu menu : menuList) {
            if (pid.equals(menu.getPid())) {
                menu.setChildren(getServiceMenuChildren(menuList, menu.getId()));
                menuTree.add(menu);
            }
        }
        return menuTree;
    }

}