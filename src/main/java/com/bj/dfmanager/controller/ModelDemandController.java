package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.ModelDemandService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.modeldemand.ModelDemandSearchVO;
import com.bj.dfmanager.vo.modeldemand.ModelDemandVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模型需求
 */
@RestController
@RequestMapping("/modelDemand")
@Slf4j
public class ModelDemandController {

    @Resource
    private ModelDemandService modelDemandService;

    //--------------------------------模型申请--------------------------------
    /**
     * 查询模型申请列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody ModelDemandSearchVO modelDemandSearchVO) {
        log.info("查询模型需求列表，入参：{}", JSON.toJSONString(modelDemandSearchVO));
        Result result = modelDemandService.queryList(modelDemandSearchVO);
        log.info("查询模型需求列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 新建模型申请
     */
    @PostMapping("/apply")
    @ResponseBody
    public Result apply(@RequestBody ModelDemandVO modelDemandVO) {
        log.info("模型申请，入参：{}", JSON.toJSONString(modelDemandVO));
        Result result = modelDemandService.apply(modelDemandVO);
        log.info("模型申请，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询模型信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody ModelDemandVO modelDemandVO) {
        log.info("查询模型需求，入参模型需求ID：{}", modelDemandVO.getId());
        Result result = modelDemandService.queryById(modelDemandVO.getId());
        log.info("查询模型需求，返回：{}", JSON.toJSONString(result));
        return result;
    }

    //--------------------------------模型审核--------------------------------
    /**
     * 模型需求阶段变更
     * 通过传2  撤回传0  审核通过传3  退回传0
     */
    @PostMapping("/updateStage")
    @ResponseBody
    public Result updateStage(@RequestBody ModelDemandVO modelDemandVO) {
        log.info("模型需求阶段变更，入参：{}", JSON.toJSONString(modelDemandVO));
        Result result = modelDemandService.updateStage(modelDemandVO);
        log.info("模型需求阶段变更，返回：{}", JSON.toJSONString(result));
        return result;
    }

    //--------------------------------模型任务--------------------------------
    /**
     * 查看分配给自己的模型需求
     */
    @PostMapping("/queryMyList")
    @ResponseBody
    public Result queryMyList(@RequestBody ModelDemandSearchVO modelDemandSearchVO) {
        log.info("查看分配给自己的模型需求，入参：{}", JSON.toJSONString(modelDemandSearchVO));
        Result result = modelDemandService.queryMyList(modelDemandSearchVO);
        log.info("查看分配给自己的模型需求，返回：{}", JSON.toJSONString(result));
        return result;
    }

    //--------------------------------公开模型申请--------------------------------
    @PostMapping("/queryPublicList")
    @ResponseBody
    public Result queryPublicList(@RequestBody ModelDemandSearchVO modelDemandSearchVO) {
        log.info("查询公开模型申请列表，入参：{}", JSON.toJSONString(modelDemandSearchVO));
        Result result = modelDemandService.queryPublicList(modelDemandSearchVO);
        log.info("查询公开模型申请列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    //--------------------------------模型审核编辑--------------------------------
    @PostMapping("/update")
    @ResponseBody
    public Result update(@RequestBody ModelDemandVO modelDemandVO) {
        log.info("模型审核编辑，入参：{}", JSON.toJSONString(modelDemandVO));
        Result result = modelDemandService.update(modelDemandVO);
        log.info("模型审核编辑，返回：{}", JSON.toJSONString(result));
        return result;
    }

    //--------------------------------模型需求日志--------------------------------
    @PostMapping("/queryModelDemandLog")
    @ResponseBody
    public Result queryModelDemandLog(@RequestBody ModelDemandSearchVO modelDemandSearchVO) {
        log.info("查询模型需求日志，入参：{}", JSON.toJSONString(modelDemandSearchVO));
        Result result = modelDemandService.queryModelDemandLog(modelDemandSearchVO);
        log.info("查询模型需求日志，返回：{}", JSON.toJSONString(result));
        return result;
    }

}
