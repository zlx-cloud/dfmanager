package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.*;
import com.bj.dfmanager.mapper.*;
import com.bj.dfmanager.service.ModelService;
import com.bj.dfmanager.util.JwtTokenUtils;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.model.ModelSearchVO;
import com.bj.dfmanager.vo.model.ModelTargetVO;
import com.bj.dfmanager.vo.model.ModelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ModelTargetMapper modelTargetMapper;
    @Resource
    private ModelResultMapper modelResultMapper;
    @Resource
    private TargetCheckRuleMapper targetCheckRuleMapper;
    @Resource
    private TaskKeyValueMapper taskKeyValueMapper;
    @Resource
    private ModelGradeMapper modelGradeMapper;

    /**
     * 查询模型列表
     */
    @Override
    public Result queryList(ModelSearchVO modelSearchVO) {
        IPage<Model> page = modelMapper.queryList(modelSearchVO.getModelName(),
                modelSearchVO.getModelStatus(), new Page<>(modelSearchVO.getCurrent(),
                        modelSearchVO.getSize()));
        return Result.success(page, "查询模型列表成功");
    }

    /**
     * 新增/修改模型
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdate(ModelVO modelVO) {
        try {
            int num;
            boolean flag = false;

            Model model = new Model();
            BeanUtils.copyProperties(modelVO, model);
            // id不存在为新增，否则为修改
            if (null == model.getId()) {
                model.setUserId(JwtTokenUtils.getCurrentUser().getUserId());
                model.setCreateTime(new Date());
                model.setModelStatus("N");
                num = modelMapper.insert(model);
            } else {
                flag = true;
                model.setUpdateTime(new Date());
                num = modelMapper.updateById(model);
            }

            if (num > 0) {
                if (flag) {
                    Map map = new HashMap<>();
                    map.put("MODEL_ID", model.getId());

                    // 修改前删除模型指标
                    modelTargetMapper.deleteByMap(map);

                    // 修改前删除模型等级分类
                    modelGradeMapper.deleteByMap(map);
                }

                // 插入模型指标
                if (CollectionUtils.isNotEmpty(modelVO.getModelTargetList())) {
                    List<ModelTarget> modelTargetList = new ArrayList<>();
                    for (ModelTargetVO modelTargetVO : modelVO.getModelTargetList()) {
                        ModelTarget modelTarget = new ModelTarget();
                        BeanUtils.copyProperties(modelTargetVO, modelTarget);
                        modelTarget.setModelId(model.getId());
                        modelTargetList.add(modelTarget);
                    }
                    modelTargetMapper.saveBatch(modelTargetList);
                }

                // 插入模型等级分类
                ModelGrade modelGrade = new ModelGrade();
                modelGrade.setModelId(model.getId());
                modelGrade.setGrade(modelVO.getGradeId());
                modelGradeMapper.insert(modelGrade);

                return Result.success(null, "操作成功");
            } else {
                return Result.fail(null, "操作失败");
            }
        } catch (BeansException e) {
            log.error("新增/修改模型发生异常：{}", e.getStackTrace());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error(null, "新增/修改模型发生异常");
        }
    }

    /**
     * 查询模型
     */
    @Override
    public Result queryById(Integer id) {
        // 主表信息
        Model model = modelMapper.selectModelById(id);

        Map map = new HashMap<>();
        map.put("MODEL_ID", id);

        // 模型指标信息
        List<ModelTarget> modelTargetList = modelTargetMapper.selectByMap(map);
        for (ModelTarget modelTarget : modelTargetList) {
            TargetCheckRule targetCheckRule = targetCheckRuleMapper.selectById(modelTarget.getRuleId());
            if (null != targetCheckRule) {
                modelTarget.setRuleName(targetCheckRule.getRuleName());
            }
        }
        model.setModelTargetList(modelTargetList);

        return Result.success(model, "查询模型成功");
    }

    /**
     * 删除模型
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result delete(Integer id) {
        try {
            int num = modelMapper.deleteById(id);
            if (num > 0) {
                Map map = new HashMap<>();
                map.put("MODEL_ID", id);

                // 删除模型指标
                modelTargetMapper.deleteByMap(map);

                // 删除模型等级分类
                modelGradeMapper.deleteByMap(map);

                return Result.success(null, "删除模型成功");
            } else {
                return Result.fail(null, "删除模型失败");
            }
        } catch (Exception e) {
            log.error("删除模型发生异常：{}", e.getStackTrace());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error(null, "删除模型发生异常");
        }
    }

    /**
     * 启用/停用模型
     */
    @Override
    public Result startOrStop(ModelVO modelVO) {
        Model model = new Model();
        BeanUtils.copyProperties(modelVO, model);
        model.setUpdateTime(new Date());
        int num = modelMapper.updateById(model);
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作成功");
        }
    }

    /**
     * 模型结果查询
     */
    @Override
    public Result queryResultList(ModelSearchVO modelSearchVO) {
        IPage<ModelResult> page = modelResultMapper.queryResultList(modelSearchVO.getModelName(),
                modelSearchVO.getWarnFlag(), modelSearchVO.getCertNo(), modelSearchVO.getFltno(),
                modelSearchVO.getFltDateStart(), modelSearchVO.getFltDateEnd(),
                new Page<>(modelSearchVO.getCurrent(), modelSearchVO.getSize()));
        return Result.success(page, "查询模型结果列表成功");
    }

    /**
     * 模型结果详情
     */
    @Override
    public Result queryResultDetail(ModelSearchVO modelSearchVO) {
        String resultDetail = modelResultMapper.queryResultDetail(modelSearchVO.getId());
        return Result.success(resultDetail, "查询模型结果详情成功");
    }

    /**
     * 查询等级分类列表
     */
    @Override
    public Result queryGradeList() {
        LambdaQueryWrapper<TaskKeyValue> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TaskKeyValue::getType, "DJFL");
        List<TaskKeyValue> list = taskKeyValueMapper.selectList(queryWrapper);
        return Result.success(list, "查询等级分类列表成功");
    }

}