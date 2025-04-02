package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.ModelService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.model.ModelSearchVO;
import com.bj.dfmanager.vo.model.ModelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模型
 */
@RestController
@RequestMapping("/model")
@Slf4j
public class ModelController {

    @Resource
    private ModelService modelService;

    /**
     * 查询模型列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询模型列表，入参：{}", JSON.toJSONString(modelSearchVO));
        Result result = modelService.queryList(modelSearchVO);
        log.info("查询模型列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询等级分类列表
     */
    @PostMapping("/queryGradeList")
    @ResponseBody
    public Result queryGradeList() {
        Result result = modelService.queryGradeList();
        log.info("查询等级分类列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 新增/修改模型
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result add(@RequestBody ModelVO modelVO) {
        log.info("新增/修改模型，入参：{}", JSON.toJSONString(modelVO));
        Result result = modelService.addOrUpdate(modelVO);
        log.info("新增/修改模型，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询模型
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody ModelVO modelVO) {
        log.info("查询模型，入参模型ID：{}", modelVO.getId());
        Result result = modelService.queryById(modelVO.getId());
        log.info("查询模型，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除模型
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody ModelVO modelVO) {
        log.info("删除模型，入参模型ID：{}", modelVO.getId());
        Result result = modelService.delete(modelVO.getId());
        log.info("删除模型，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用模型
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody ModelVO modelVO) {
        log.info("启用/停用模型，入参：{}", JSON.toJSONString(modelVO));
        Result result = modelService.startOrStop(modelVO);
        log.info("启用/停用模型，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 模型结果查询
     */
    @PostMapping("/queryResultList")
    @ResponseBody
    public Result queryResultList(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("模型结果查询，入参：{}", JSON.toJSONString(modelSearchVO));
        Result result = modelService.queryResultList(modelSearchVO);
        log.info("模型结果查询，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 模型结果详情
     */
    @PostMapping("/queryResultDetail")
    @ResponseBody
    public Result queryResultDetail(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询模型结果详情，入参：{}", JSON.toJSONString(modelSearchVO));
        Result result = modelService.queryResultDetail(modelSearchVO);
        log.info("查询模型结果详情，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 公开模型
     */
    @PostMapping("/queryPublicList")
    @ResponseBody
    public Result queryPublicList(@RequestBody ModelSearchVO modelSearchVO) {
        log.info("查询公开模型列表，入参：{}", JSON.toJSONString(modelSearchVO));
        Result result = modelService.queryPublicList(modelSearchVO);
        log.info("查询公开模型列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

}
