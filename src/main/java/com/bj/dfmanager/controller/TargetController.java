package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.TargetService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.target.TargetSearchVO;
import com.bj.dfmanager.vo.target.TargetVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 指标
 */
@RestController
@RequestMapping("/target")
@Slf4j
public class TargetController {

    @Resource
    private TargetService targetService;

    /**
     * 查询指标列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody TargetSearchVO targetSearchVO) {
        log.info("查询指标列表，入参：{}", JSON.toJSONString(targetSearchVO));
        Result result = targetService.queryList(targetSearchVO);
        log.info("查询指标列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断指标编码是否存在
     */
    @PostMapping("/targetCodeIsExist")
    @ResponseBody
    public Result targetCodeIsExist(@RequestBody TargetVO targetVO) {
        log.info("判断指标编码是否存在，入参指标编码：{}", targetVO.getTargetCode());
        Result result = targetService.targetCodeIsExist(targetVO.getTargetCode());
        log.info("判断指标编码是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 新增/修改指标
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody TargetVO targetVO) {
        log.info("新增/修改指标，入参：{}", JSON.toJSONString(targetVO));
        Result result = targetService.addOrUpdate(targetVO);
        log.info("新增/修改指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询指标
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody TargetVO targetVO) {
        log.info("查询指标，入参指标ID：{}", targetVO.getId());
        Result result = targetService.queryById(targetVO.getId());
        log.info("查询指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除指标
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody TargetVO targetVO) {
        log.info("删除指标，入参指标ID：{}", targetVO.getId());
        Result result = targetService.delete(targetVO.getId());
        log.info("删除指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用指标
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody TargetVO targetVO) {
        log.info("启用/停用指标，入参：{}", JSON.toJSONString(targetVO));
        Result result = targetService.startOrStop(targetVO);
        log.info("启用/停用指标，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询指标列表
     */
    @PostMapping("/targetList")
    @ResponseBody
    public Result targetList() {
        Result result = targetService.targetList();
        log.info("查询指标列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 指标测试
     */
    @PostMapping("/targetTest")
    @ResponseBody
    public Result targetTest(@RequestBody TargetVO targetVO) {
        log.info("指标测试，入参：{}", JSON.toJSONString(targetVO));
        Result result = targetService.targetTest(targetVO);
        log.info("指标测试，返回：{}", JSON.toJSONString(result));
        return result;
    }

}