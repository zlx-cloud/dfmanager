package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.ServiceMenu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ServiceMenuMapper extends BaseMapper<ServiceMenu> {

    /**
     * 角色菜单
     */
    @Select("SELECT * FROM DFMANAGER.T_SERVICE_MENU")
    List<ServiceMenu> getAllServiceMenu();

}