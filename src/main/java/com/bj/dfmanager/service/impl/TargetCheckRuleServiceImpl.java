package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.TargetCheckRule;
import com.bj.dfmanager.mapper.TargetCheckRuleMapper;
import com.bj.dfmanager.service.TargetCheckRuleService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleSearchVO;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class TargetCheckRuleServiceImpl implements TargetCheckRuleService {

    @Resource
    private TargetCheckRuleMapper targetCheckRuleMapper;

    /**
     * 查询指标校验规则列表
     */
    @Override
    public Result queryList(TargetCheckRuleSearchVO searchVO) {
        LambdaQueryWrapper<TargetCheckRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(searchVO.getRuleCode()),
                TargetCheckRule::getRuleCode, searchVO.getRuleCode());
        queryWrapper.like(StringUtils.isNotEmpty(searchVO.getRuleName()),
                TargetCheckRule::getRuleName, searchVO.getRuleName());
        queryWrapper.eq(StringUtils.isNotEmpty(searchVO.getRuleStatus()),
                TargetCheckRule::getRuleStatus, searchVO.getRuleStatus());
        queryWrapper.orderByDesc(TargetCheckRule::getCreateTime);
        IPage<TargetCheckRule> page = targetCheckRuleMapper.selectPage(new Page<>(searchVO.getCurrent(),
                searchVO.getSize()), queryWrapper);
        return Result.success(page, "查询指标校验规则列表成功");
    }

    /**
     * 判断规则编码是否存在
     */
    @Override
    public Result ruleCodeIsExist(String ruleCode) {
        LambdaQueryWrapper<TargetCheckRule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TargetCheckRule::getRuleCode, ruleCode);
        int count = targetCheckRuleMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "规则编码不存在，可以添加");
        } else {
            return Result.fail(null, "规则编码存在，不可以添加");
        }
    }

    /**
     * 添加/修改指标校验规则
     */
    @Override
    public Result addOrUpdate(TargetCheckRuleVO targetCheckRuleVO) {
        int num;
        TargetCheckRule targetCheckRule = new TargetCheckRule();
        BeanUtils.copyProperties(targetCheckRuleVO, targetCheckRule);
        if (null == targetCheckRule.getId()) {
            targetCheckRule.setRuleStatus("N");
            targetCheckRule.setCreateTime(new Date());
            num = targetCheckRuleMapper.insert(targetCheckRule);
        } else {
            targetCheckRule.setUpdateTime(new Date());
            num = targetCheckRuleMapper.updateById(targetCheckRule);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询指标校验规则
     */
    @Override
    public Result queryById(Integer id) {
        TargetCheckRule targetCheckRule = targetCheckRuleMapper.selectById(id);
        return Result.success(targetCheckRule, "查询指标校验规则信息成功");
    }

    /**
     * 删除指标校验规则
     */
    @Override
    public Result delete(Integer id) {
        int num = targetCheckRuleMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除指标校验规则成功");
        } else {
            return Result.fail(null, "删除指标校验规则失败");
        }
    }

    /**
     * 启用/停用指标校验规则
     */
    @Override
    public Result startOrStop(TargetCheckRuleVO targetCheckRuleVO) {
        TargetCheckRule targetCheckRule = new TargetCheckRule();
        BeanUtils.copyProperties(targetCheckRuleVO, targetCheckRule);
        targetCheckRule.setUpdateTime(new Date());
        int num = targetCheckRuleMapper.updateById(targetCheckRule);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

}