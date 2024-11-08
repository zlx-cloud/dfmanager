package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ServiceInfo;
import com.bj.dfmanager.mapper.ServiceInfoMapper;
import com.bj.dfmanager.service.ServiceInfoService;
import com.bj.dfmanager.util.HttpUtils;
import com.bj.dfmanager.vo.common.PostResponseVO;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.service.ServiceInfoSearchVO;
import com.bj.dfmanager.vo.service.ServiceInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class ServiceInfoServiceImpl implements ServiceInfoService {

    @Resource
    private ServiceInfoMapper serviceInfoMapper;
    @Value("${dfservice.prefix}")
    private String urlPrefix;

    /**
     * 查询服务目录列表
     */
    @Override
    public Result queryList(ServiceInfoSearchVO serviceInfoSearchVO) {
        LambdaQueryWrapper<ServiceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != serviceInfoSearchVO.getServiceMenuId(),
                ServiceInfo::getServiceMenuId, serviceInfoSearchVO.getServiceMenuId());
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceCode()),
                ServiceInfo::getServiceCode, serviceInfoSearchVO.getServiceCode());
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceName()),
                ServiceInfo::getServiceName, serviceInfoSearchVO.getServiceName());
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceUrl()),
                ServiceInfo::getServiceUrl, serviceInfoSearchVO.getServiceUrl());
        queryWrapper.eq(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceStatus()),
                ServiceInfo::getServiceStatus, serviceInfoSearchVO.getServiceStatus());
        queryWrapper.orderByDesc(ServiceInfo::getCreateTime);
        IPage<ServiceInfo> page = serviceInfoMapper.selectPage(new Page<>(serviceInfoSearchVO.getCurrent(),
                serviceInfoSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询服务列表成功");
    }

    /**
     * 判断服务编码是存在
     */
    @Override
    public Result serviceCodeIsExist(String serviceCode) {
        LambdaQueryWrapper<ServiceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ServiceInfo::getServiceCode, serviceCode);
        int count = serviceInfoMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "服务编码不存在，可以创建");
        } else {
            return Result.fail(null, "服务编码存在，不可以创建");
        }
    }

    /**
     * 添加/修改服务目录
     */
    @Override
    public Result addOrUpdate(ServiceInfoVO serviceInfoVO) {
        int num;
        ServiceInfo serviceInfo = new ServiceInfo();
        BeanUtils.copyProperties(serviceInfoVO, serviceInfo);
        if (null == serviceInfo.getId()) {
            serviceInfo.setServiceStatus("N");
            serviceInfo.setCreateTime(new Date());
            num = serviceInfoMapper.insert(serviceInfo);
        } else {
            serviceInfo.setUpdateTime(new Date());
            num = serviceInfoMapper.updateById(serviceInfo);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询服务目录
     */
    @Override
    public Result queryById(Integer id) {
        ServiceInfo serviceInfo = serviceInfoMapper.selectById(id);
        return Result.success(serviceInfo, "查询服务成功");
    }

    /**
     * 删除服务目录
     */
    @Override
    public Result delete(Integer id) {
        int num = serviceInfoMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除服务成功");
        } else {
            return Result.fail(null, "删除服务目录失败");
        }
    }

    /**
     * 启用/停用服务目录
     */
    @Override
    public Result startOrStop(ServiceInfoVO serviceInfoVO) {
        ServiceInfo serviceInfo = new ServiceInfo();
        BeanUtils.copyProperties(serviceInfoVO, serviceInfo);
        serviceInfo.setUpdateTime(new Date());
        int num = serviceInfoMapper.updateById(serviceInfo);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

    /**
     * 查询启用状态下服务接口
     */
    @Override
    public Result queryEnableService(ServiceInfoSearchVO serviceInfoSearchVO) {
        LambdaQueryWrapper<ServiceInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceCode()),
                ServiceInfo::getServiceCode, serviceInfoSearchVO.getServiceCode());
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceName()),
                ServiceInfo::getServiceName, serviceInfoSearchVO.getServiceName());
        queryWrapper.like(StringUtils.isNotEmpty(serviceInfoSearchVO.getServiceUrl()),
                ServiceInfo::getServiceUrl, serviceInfoSearchVO.getServiceUrl());
        queryWrapper.eq(ServiceInfo::getServiceStatus, "Y");
        queryWrapper.orderByDesc(ServiceInfo::getCreateTime);
        IPage<ServiceInfo> page = serviceInfoMapper.selectPage(new Page<>(serviceInfoSearchVO.getCurrent(),
                serviceInfoSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询启用状态下服务接口列表成功");
    }

    /**
     * 接口探测
     */
    @Override
    public Result serviceTest(Integer id) {
        ServiceInfo serviceInfo = serviceInfoMapper.selectById(id);
        PostResponseVO postResponseVO = HttpUtils.doPost(urlPrefix + serviceInfo.getServiceUrl() + "/ping", "{\"key\":\"value\"}");
        return Result.success(postResponseVO, "接口探测成功");
    }

}