package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ServiceInfo;
import com.bj.dfmanager.entity.Target;
import com.bj.dfmanager.entity.UserSystemInfo;
import com.bj.dfmanager.mapper.*;
import com.bj.dfmanager.service.UserSystemInfoService;
import com.bj.dfmanager.util.AESUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoSearchVO;
import com.bj.dfmanager.vo.usersysteminfo.UserSystemInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserSystemInfoServiceImpl implements UserSystemInfoService {

    @Resource
    private UserSystemInfoMapper userSystemInfoMapper;
    @Resource
    private ServiceInfoMapper serviceInfoMapper;
    @Resource
    private UserSystemServiceMapper userSystemServiceMapper;
    @Resource
    private TargetMapper targetMapper;
    @Resource
    private UserSystemTargetMapper userSystemTargetMapper;

    /**
     * 查询用户系统信息列表
     */
    @Override
    public Result queryList(UserSystemInfoSearchVO userSystemInfoSearchVO) {
        LambdaQueryWrapper<UserSystemInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getSystemCode()),
                UserSystemInfo::getSystemCode, userSystemInfoSearchVO.getSystemCode());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getSystemName()),
                UserSystemInfo::getSystemName, userSystemInfoSearchVO.getSystemName());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getLoginName()),
                UserSystemInfo::getLoginName, userSystemInfoSearchVO.getLoginName());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getAuthCode()),
                UserSystemInfo::getAuthCode, userSystemInfoSearchVO.getAuthCode());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getContactUser()),
                UserSystemInfo::getContactUser, userSystemInfoSearchVO.getContactUser());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getTelInfo()),
                UserSystemInfo::getTelInfo, userSystemInfoSearchVO.getTelInfo());
        queryWrapper.like(StringUtils.isNotEmpty(userSystemInfoSearchVO.getCertNo()),
                UserSystemInfo::getCertNo, userSystemInfoSearchVO.getCertNo());
        queryWrapper.eq(StringUtils.isNotEmpty(userSystemInfoSearchVO.getStatus()),
                UserSystemInfo::getStatus, userSystemInfoSearchVO.getStatus());
        queryWrapper.orderByDesc(UserSystemInfo::getCreateTime);
        IPage<UserSystemInfo> page = userSystemInfoMapper.selectPage(new Page<>(userSystemInfoSearchVO.getCurrent(),
                userSystemInfoSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询用户系统信息列表成功");
    }

    /**
     * 判断系统编码是否存在
     */
    @Override
    public Result systemCodeIsExist(String systemCode) {
        LambdaQueryWrapper<UserSystemInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserSystemInfo::getSystemCode, systemCode);
        int count = userSystemInfoMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "系统编码不存在，可以添加");
        } else {
            return Result.fail(null, "系统编码存在，不可以添加");
        }
    }

    /**
     * 添加/修改用户系统信息
     */
    @Override
    public Result addOrUpdate(UserSystemInfoVO userSystemInfoVO) {
        int num;
        UserSystemInfo userSystemInfo = new UserSystemInfo();
        BeanUtils.copyProperties(userSystemInfoVO, userSystemInfo);
        userSystemInfo.setLoginPwd(AESUtils.encryptN(userSystemInfo.getLoginPwd()));
        if (null == userSystemInfo.getId()) {
            userSystemInfo.setStatus("Y");
            userSystemInfo.setCreateTime(new Date());
            num = userSystemInfoMapper.insert(userSystemInfo);
        } else {
            userSystemInfo.setUpdateTime(new Date());
            num = userSystemInfoMapper.updateById(userSystemInfo);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询用户系统信息
     */
    @Override
    public Result queryById(Integer id) {
        UserSystemInfo userSystemInfo = userSystemInfoMapper.selectById(id);
        userSystemInfo.setLoginPwd(AESUtils.decryptN(userSystemInfo.getLoginPwd()));
        return Result.success(userSystemInfo, "查询用户系统信息成功");
    }

    /**
     * 删除用户系统信息
     */
    @Override
    public Result delete(Integer id) {
        int num = userSystemInfoMapper.deleteById(id);
        if (num > 0) {
            // 删除用户系统绑定的服务
            Map serviceMap = new HashMap();
            serviceMap.put("SYSTEM_ID", id);
            userSystemServiceMapper.deleteByMap(serviceMap);

            // 删除用户系统绑定的指标
            Map targetMap = new HashMap();
            targetMap.put("SYSTEM_ID", id);
            userSystemTargetMapper.deleteByMap(targetMap);
            return Result.success(null, "删除用户系统信息成功");
        } else {
            return Result.fail(null, "删除用户系统信息失败");
        }
    }

    /**
     * 启用/停用用户系统信息
     */
    @Override
    public Result startOrStop(UserSystemInfoVO userSystemInfoVO) {
        UserSystemInfo userSystemInfo = new UserSystemInfo();
        BeanUtils.copyProperties(userSystemInfoVO, userSystemInfo);
        userSystemInfo.setUpdateTime(new Date());
        int num = userSystemInfoMapper.updateById(userSystemInfo);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

    /**
     * 查询用户系统已拥有的服务
     */
    @Override
    public Result userSystemHaveService(Integer id) {
        List<ServiceInfo> serviceInfoList = serviceInfoMapper.userSystemHaveService(id);
        return Result.success(serviceInfoList, "查询用户系统已拥有的服务成功");
    }

    /**
     * 查询用户系统未拥有的服务
     */
    @Override
    public Result userSystemNotHaveService(Integer id) {
        List<ServiceInfo> serviceInfoList = serviceInfoMapper.userSystemNotHaveService(id);
        return Result.success(serviceInfoList, "查询用户系统未拥有的服务成功");
    }

    /**
     * 用户系统绑定服务
     */
    @Override
    public Result userSystemBindService(UserSystemInfoVO userSystemInfoVO) {
        // 先删除用户系统已绑定的服务
        Map map = new HashMap();
        map.put("SYSTEM_ID", userSystemInfoVO.getId());
        userSystemServiceMapper.deleteByMap(map);

        // 绑定新服务
        if (CollectionUtils.isNotEmpty(userSystemInfoVO.getServiceList())) {
            userSystemServiceMapper.userSystemBindService(userSystemInfoVO.getId(), userSystemInfoVO.getServiceList());
        }
        return Result.success(null, "用户系统绑定服务成功");
    }

    /**
     * 查询用户系统已拥有的指标
     */
    @Override
    public Result userSystemHaveTarget(Integer id) {
        List<Target> targetList = targetMapper.userSystemHaveTarget(id);
        return Result.success(targetList, "查询用户系统已拥有的指标成功");
    }

    /**
     * 查询用户系统未拥有的指标
     */
    @Override
    public Result userSystemNotHaveTarget(Integer id) {
        List<Target> targetList = targetMapper.userSystemNotHaveTarget(id);
        return Result.success(targetList, "查询用户系统已拥有的指标成功");
    }

    /**
     * 用户系统绑定指标
     */
    @Override
    public Result userSystemBindTarget(UserSystemInfoVO userSystemInfoVO) {
        // 先删除用户系统已绑定的指标
        Map map = new HashMap();
        map.put("SYSTEM_ID", userSystemInfoVO.getId());
        userSystemTargetMapper.deleteByMap(map);

        // 绑定新服务
        if (CollectionUtils.isNotEmpty(userSystemInfoVO.getTargetList())) {
            userSystemTargetMapper.userSystemBindTarget(userSystemInfoVO.getId(), userSystemInfoVO.getTargetList());
        }
        return Result.success(null, "用户系统绑定指标成功");
    }

}