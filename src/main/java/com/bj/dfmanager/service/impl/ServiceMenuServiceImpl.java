package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bj.dfmanager.entity.ServiceInfo;
import com.bj.dfmanager.entity.ServiceMenu;
import com.bj.dfmanager.mapper.ServiceInfoMapper;
import com.bj.dfmanager.mapper.ServiceMenuMapper;
import com.bj.dfmanager.service.ServiceMenuService;
import com.bj.dfmanager.util.MenuUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.servicemenu.ServiceMenuVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ServiceMenuServiceImpl implements ServiceMenuService {

    @Resource
    private ServiceMenuMapper serviceMenuMapper;
    @Resource
    private ServiceInfoMapper serviceInfoMapper;

    /**
     * 服务目录菜单树
     */
    @Override
    public Result serviceMenuTree() {
        List<ServiceMenu> menuList = serviceMenuMapper.getAllServiceMenu();
        // 生成菜单树
        List<ServiceMenu> menuTree = MenuUtils.getServiceMenuChildren(menuList, 999);
        return Result.success(menuTree, "查询服务目录树成功");
    }

    /**
     * 添加/修改用服务目录菜单
     */
    @Override
    public Result addOrUpdate(ServiceMenuVO serviceMenuVO) {
        int num;
        ServiceMenu serviceMenu = new ServiceMenu();
        BeanUtils.copyProperties(serviceMenuVO, serviceMenu);
        if (null == serviceMenuVO.getId()) {
            num = serviceMenuMapper.insert(serviceMenu);
        } else {
            num = serviceMenuMapper.updateById(serviceMenu);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询服务目录菜单信息
     */
    @Override
    public Result queryById(Integer userId) {
        ServiceMenu serviceMenu = serviceMenuMapper.selectById(userId);
        return Result.success(serviceMenu, "查询服务目录节点信息成功");
    }

    /**
     * 删除服务目录菜单信息
     */
    @Override
    public Result delete(Integer id) {
        // 查询是否有子节点
        LambdaQueryWrapper<ServiceMenu> menuQueryWrapper = new LambdaQueryWrapper<>();
        menuQueryWrapper.eq(ServiceMenu::getPid, id);
        int menuCount = serviceMenuMapper.selectCount(menuQueryWrapper).intValue();
        if (menuCount > 0) {
            return Result.fail(null, "该节点下存在子节点，不可以删除");
        }

        // 查询菜单下服务个数
        LambdaQueryWrapper<ServiceInfo> infoQueryWrapper = new LambdaQueryWrapper<>();
        infoQueryWrapper.eq(ServiceInfo::getServiceMenuId, id);
        int infoCount = serviceInfoMapper.selectCount(infoQueryWrapper).intValue();
        if (infoCount > 0) {
            return Result.fail(null, "该节点下存在服务，不可以删除");
        }

        int num = serviceMenuMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除服务目录菜单成功");
        } else {
            return Result.fail(null, "删除服务目录菜单失败");
        }

    }

}