package com.bj.dfmanager.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Target;
import com.bj.dfmanager.mapper.TargetMapper;
import com.bj.dfmanager.service.TargetService;
import com.bj.dfmanager.util.HttpUtils;
import com.bj.dfmanager.vo.common.PostResponseVO;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.target.DfServiceVO;
import com.bj.dfmanager.vo.target.DfTargetVO;
import com.bj.dfmanager.vo.target.TargetSearchVO;
import com.bj.dfmanager.vo.target.TargetVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TargetServiceImpl implements TargetService {

    @Resource
    private TargetMapper targetMapper;
    @Value("${dfservice.reflustCatch}")
    private String reflustCatchUrl;
    @Value("${dfservice.targetTest}")
    private String targetTestUrl;

    /**
     * 查询指标列表
     */
    @Override
    public Result queryList(TargetSearchVO targetSearchVO) {
        LambdaQueryWrapper<Target> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(targetSearchVO.getTargetCode()),
                Target::getTargetCode, targetSearchVO.getTargetCode());
        queryWrapper.like(StringUtils.isNotEmpty(targetSearchVO.getTargetName()),
                Target::getTargetName, targetSearchVO.getTargetName());
        queryWrapper.like(StringUtils.isNotEmpty(targetSearchVO.getRequestMethod()),
                Target::getRequestMethod, targetSearchVO.getRequestMethod());
        queryWrapper.eq(StringUtils.isNotEmpty(targetSearchVO.getAsync()),
                Target::getAsync, targetSearchVO.getAsync());
        queryWrapper.eq(StringUtils.isNotEmpty(targetSearchVO.getApplicableScope()),
                Target::getApplicableScope, targetSearchVO.getApplicableScope());
        queryWrapper.eq(StringUtils.isNotEmpty(targetSearchVO.getTargetStatus()),
                Target::getTargetStatus, targetSearchVO.getTargetStatus());
        queryWrapper.orderByDesc(Target::getCreateTime);
        IPage<Target> page = targetMapper.selectPage(new Page<>(targetSearchVO.getCurrent(),
                targetSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询指标列表成功");
    }

    /**
     * 判断指标编码是否存在
     */
    @Override
    public Result targetCodeIsExist(String targetCode) {
        LambdaQueryWrapper<Target> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Target::getTargetCode, targetCode);
        int count = targetMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "指标编码不存在，可以创建");
        } else {
            return Result.fail(null, "指标编码存在，不可以创建");
        }
    }

    /**
     * 新增/修改指标
     */
    @Override
    public Result addOrUpdate(TargetVO targetVO) {
        int num;
        Target target = new Target();
        BeanUtils.copyProperties(targetVO, target);
        // id不存在为新增，否则为修改
        if (null == target.getId()) {
            target.setCreateTime(new Date());
            target.setTargetStatus("N");
            num = targetMapper.insert(target);
        } else {
            target.setUpdateTime(new Date());
            num = targetMapper.updateById(target);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询指标
     */
    @Override
    public Result queryById(Integer id) {
        // 主表信息
        Target target = targetMapper.selectById(id);
        return Result.success(target, "查询指标成功");
    }

    /**
     * 删除指标
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(Integer id) {
        int num = targetMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除指标成功");
        } else {
            return Result.fail(null, "删除指标失败");
        }
    }

    /**
     * 启用/停用指标
     */
    @Override
    public Result startOrStop(TargetVO targetVO) {
        Target target = targetMapper.selectById(targetVO.getId());
        target.setTargetStatus(targetVO.getTargetStatus());
        target.setUpdateTime(new Date());
        int num = targetMapper.updateById(target);
        if (num > 0) {
            // 刷新缓存
            DfTargetVO dfTargetVO = new DfTargetVO();
            dfTargetVO.setTargetCode(target.getTargetCode());
            dfTargetVO.setTargetStatus(target.getTargetStatus());
            log.info("调用dfservice刷新缓存接口，入参：{}", JSON.toJSONString(dfTargetVO));
            PostResponseVO postResponseVO = HttpUtils.doPost(reflustCatchUrl, JSON.toJSONString(dfTargetVO));
            log.info("调用dfservice刷新缓存接口，返回：{}", JSON.toJSONString(postResponseVO));
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询指标列表
     */
    @Override
    public Result targetList() {
        LambdaQueryWrapper<Target> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Target::getTargetStatus, "Y");
        queryWrapper.orderByDesc(Target::getCreateTime);
        List<Target> targetList = targetMapper.selectList(queryWrapper);
        return Result.success(targetList, "查询指标列表成功");
    }

    /**
     * 指标测试
     */
    @Override
    public Result targetTest(TargetVO targetVO) {
        Target target = targetMapper.selectById(targetVO.getId());
        // 指标测试
        DfServiceVO dfServiceVO = new DfServiceVO();
        dfServiceVO.setAppScope(target.getApplicableScope());
        dfServiceVO.setTargetCode(target.getTargetCode());
        dfServiceVO.setGender(targetVO.getGender());
        dfServiceVO.setBirthDay(targetVO.getBirthDay());
        dfServiceVO.setCountryCode(targetVO.getCountryCode());
        dfServiceVO.setCertNo(targetVO.getCertNo());
        dfServiceVO.setData(targetVO.getData());
        log.info("调用dfservice指标测试接口，入参：{}", JSON.toJSONString(dfServiceVO));
        PostResponseVO postResponseVO = HttpUtils.doPost(targetTestUrl, JSON.toJSONString(dfServiceVO));
        log.info("调用dfservice指标测试接口，返回：{}", JSON.toJSONString(postResponseVO));
        return Result.success(postResponseVO, "指标测试成功");
    }

}