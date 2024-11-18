package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelDemand;
import com.bj.dfmanager.mapper.ModelDemandMapper;
import com.bj.dfmanager.service.ModelDemandService;
import com.bj.dfmanager.util.JwtTokenUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.modeldemand.ModelDemandSearchVO;
import com.bj.dfmanager.vo.modeldemand.ModelDemandVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class ModelDemandServiceImpl implements ModelDemandService {

    @Resource
    private ModelDemandMapper modelDemandMapper;

    /**
     * 查询模型需求列表
     */
    @Override
    public Result queryList(ModelDemandSearchVO modelDemandSearchVO) {
        LambdaQueryWrapper<ModelDemand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(modelDemandSearchVO.getModelName()),
                ModelDemand::getModelName, modelDemandSearchVO.getModelName());
        queryWrapper.eq(StringUtils.isNotEmpty(modelDemandSearchVO.getModelType()),
                ModelDemand::getModelType, modelDemandSearchVO.getModelType());
        queryWrapper.eq(StringUtils.isNotEmpty(modelDemandSearchVO.getUseScope()),
                ModelDemand::getUseScope, modelDemandSearchVO.getUseScope());
        queryWrapper.eq(StringUtils.isNotEmpty(modelDemandSearchVO.getDemandUnit()),
                ModelDemand::getDemandUnit, modelDemandSearchVO.getDemandUnit());
        queryWrapper.like(StringUtils.isNotEmpty(modelDemandSearchVO.getDeveloper()),
                ModelDemand::getDeveloper, modelDemandSearchVO.getDeveloper());
        queryWrapper.eq(StringUtils.isNotEmpty(modelDemandSearchVO.getDemandStatus()),
                ModelDemand::getDemandStatus, modelDemandSearchVO.getDemandStatus());
        queryWrapper.orderByDesc(ModelDemand::getCreateTime);
        IPage<ModelDemand> page = modelDemandMapper.selectPage(new Page<>(modelDemandSearchVO.getCurrent(),
                modelDemandSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询模型需求列表成功");
    }

    /**
     * 模型申请
     */
    @Override
    public Result apply(ModelDemandVO modelDemandVO) {
        ModelDemand modelDemand = new ModelDemand();
        BeanUtils.copyProperties(modelDemandVO, modelDemand);
        modelDemand.setCreateTime(new Date());
        modelDemand.setDemandStatus("0");
        int num = modelDemandMapper.insert(modelDemand);
        if (num > 0) {
            return Result.success(null, "模型申请成功");
        } else {
            return Result.fail(null, "模型申请失败");
        }
    }

    /**
     * 查询模型需求
     */
    @Override
    public Result queryById(Integer id) {
        ModelDemand modelDemand = modelDemandMapper.selectById(id);
        return Result.success(modelDemand, "查询模型需求成功");
    }

    /**
     * 模型分配
     */
    @Override
    public Result allocation(ModelDemandVO modelDemandVO) {
        ModelDemand modelDemand = new ModelDemand();
        BeanUtils.copyProperties(modelDemandVO, modelDemand);
        modelDemand.setUpdateTime(new Date());
        modelDemand.setDemandStatus("1");
        int num = modelDemandMapper.updateById(modelDemand);
        if (num > 0) {
            return Result.success(null, "模型分配成功");
        } else {
            return Result.fail(null, "模型分配失败");
        }
    }

    /**
     * 模型需求阶段变更
     */
    @Override
    public Result updateStage(ModelDemandVO modelDemandVO) {
        ModelDemand modelDemand = new ModelDemand();
        BeanUtils.copyProperties(modelDemandVO, modelDemand);
        modelDemand.setUpdateTime(new Date());
        int num = modelDemandMapper.updateById(modelDemand);
        if (num > 0) {
            return Result.success(null, "模型需求阶段变更成功");
        } else {
            return Result.fail(null, "模型需求阶段变更失败");
        }
    }

    /**
     * 查看分配给自己的模型需求
     */
    @Override
    public Result queryMyList(ModelDemandSearchVO modelDemandSearchVO) {
        LambdaQueryWrapper<ModelDemand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(modelDemandSearchVO.getModelName()),
                ModelDemand::getModelName, modelDemandSearchVO.getModelName())
                .eq(StringUtils.isNotEmpty(modelDemandSearchVO.getModelType()),
                        ModelDemand::getModelType, modelDemandSearchVO.getModelType())
                .eq(StringUtils.isNotEmpty(modelDemandSearchVO.getUseScope()),
                        ModelDemand::getUseScope, modelDemandSearchVO.getUseScope())
                .eq(StringUtils.isNotEmpty(modelDemandSearchVO.getDemandUnit()),
                        ModelDemand::getDemandUnit, modelDemandSearchVO.getDemandUnit())
                .eq(StringUtils.isNotEmpty(modelDemandSearchVO.getDeveloper()),
                        ModelDemand::getDeveloper, JwtTokenUtils.getCurrentUser().getUserId())
                .orderByDesc(ModelDemand::getCreateTime);
        IPage<ModelDemand> page = modelDemandMapper.selectPage(new Page<>(modelDemandSearchVO.getCurrent(),
                modelDemandSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查看分配给自己的模型需求列表成功");
    }

}