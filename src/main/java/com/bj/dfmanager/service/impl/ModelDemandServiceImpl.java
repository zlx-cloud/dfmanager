package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelDemand;
import com.bj.dfmanager.entity.ModelDemandLog;
import com.bj.dfmanager.mapper.ModelDemandLogMapper;
import com.bj.dfmanager.mapper.ModelDemandMapper;
import com.bj.dfmanager.mapper.RoleMapper;
import com.bj.dfmanager.service.ModelDemandLogService;
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
import java.util.List;

@Service
@Slf4j
public class ModelDemandServiceImpl implements ModelDemandService {

    @Resource
    private ModelDemandMapper modelDemandMapper;
    @Resource
    private ModelDemandLogService modelDemandLogService;
    @Resource
    private ModelDemandLogMapper modelDemandLogMapper;
    @Resource
    private RoleMapper roleMapper;

    /**
     * 查询模型需求列表
     */
    @Override
    public Result queryList(ModelDemandSearchVO vo) {
        String userId = "";
        Integer currUserId = JwtTokenUtils.getCurrentUser().getUserId();
        List<Integer> roleId = roleMapper.userHaveRoleId(currUserId);
        if (!roleId.contains(1)) {
            userId = currUserId + "";
        }
        IPage<ModelDemand> page = modelDemandMapper.selectByPage(new Page<>(vo.getCurrent(),
                        vo.getSize()), vo.getModelName(), vo.getModelType(), vo.getUseScope(),
                vo.getDemandUnit(), vo.getDemandStatus(), userId);
        return Result.success(page, "查询模型需求列表成功");
    }

    /**
     * 模型申请
     */
    @Override
    public Result apply(ModelDemandVO modelDemandVO) {
        int num;
        ModelDemand modelDemand = new ModelDemand();
        BeanUtils.copyProperties(modelDemandVO, modelDemand);
        if (null == modelDemand.getId()) {
            modelDemand.setApplyUser(JwtTokenUtils.getCurrentUser().getUserId() + "");
            modelDemand.setCreateTime(new Date());
            num = modelDemandMapper.insert(modelDemand);
            modelDemandLogService.addLog(modelDemand.getId(), "新建模型申请");
        } else {
            modelDemand.setUpdateTime(new Date());
            num = modelDemandMapper.updateById(modelDemand);
            modelDemandLogService.addLog(modelDemand.getId(), "修改模型申请");
        }

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
        ModelDemand modelDemand = modelDemandMapper.queryId(id);
        return Result.success(modelDemand, "查询模型需求成功");
    }

    /**
     * 模型需求阶段变更
     */
    @Override
    public Result updateStage(ModelDemandVO modelDemandVO) {
        int num = modelDemandMapper.updateStage(modelDemandVO.getId(),
                modelDemandVO.getDemandStatus(),modelDemandVO.getAdvice());
        if (num > 0) {
            modelDemandLogService.addLog(modelDemandVO.getId(), "模型需求阶段变更");
            return Result.success(null, "模型需求阶段变更成功");
        } else {
            return Result.fail(null, "模型需求阶段变更失败");
        }
    }

    /**
     * 查看分配给自己的模型需求
     */
    @Override
    public Result queryMyList(ModelDemandSearchVO vo) {
        IPage<ModelDemand> page = modelDemandMapper.queryMyList(new Page<>(vo.getCurrent(),
                        vo.getSize()), vo.getModelName(), vo.getModelType(), vo.getUseScope(),
                vo.getDemandUnit(), JwtTokenUtils.getCurrentUser().getUserId());
        return Result.success(page, "查看分配给自己的模型需求列表成功");
    }

    /**
     * 查询公开模型申请列表
     */
    @Override
    public Result queryPublicList(ModelDemandSearchVO vo) {
        String userId = "";
        Integer currUserId = JwtTokenUtils.getCurrentUser().getUserId();
        List<Integer> roleId = roleMapper.userHaveRoleId(currUserId);
        if (!roleId.contains(1)) {
            userId = currUserId + "";
        }
        IPage<ModelDemand> page = modelDemandMapper.queryPublicList(new Page<>(vo.getCurrent(),
                        vo.getSize()), vo.getModelName(), vo.getModelType(), vo.getUseScope(),
                vo.getDemandUnit(), vo.getDemandStatus(), userId);
        return Result.success(page, "查询模型需求列表成功");
    }

    /**
     * 模型审核编辑
     */
    @Override
    public Result update(ModelDemandVO modelDemandVO) {
        ModelDemand modelDemand = new ModelDemand();
        BeanUtils.copyProperties(modelDemandVO, modelDemand);
        modelDemand.setUpdateTime(new Date());
        int num = modelDemandMapper.updateById(modelDemand);
        if (num > 0) {
            modelDemandLogService.addLog(modelDemand.getId(), "模型审核编辑");
            return Result.success(null, "模型编辑成功");
        } else {
            return Result.fail(null, "模型编辑失败");
        }
    }

    @Override
    public Result queryModelDemandLog(ModelDemandSearchVO modelDemandSearchVO) {
        IPage<ModelDemandLog> page = modelDemandLogMapper.queryModelDemandLog(modelDemandSearchVO.getModelName(),
                modelDemandSearchVO.getUserName(), new Page<>(modelDemandSearchVO.getCurrent(),
                        modelDemandSearchVO.getSize()));
        return Result.success(page, "查询模型需求日志成功");
    }

}
