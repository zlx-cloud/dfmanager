package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.TargetCheckRuleService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleSearchVO;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 指标校验规则
 */
@RestController
@RequestMapping("/targetCheckRule")
@Slf4j
public class TargetCheckRuleController {

    @Resource
    private TargetCheckRuleService targetCheckRuleService;

    /**
     * 查询指标校验规则列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody TargetCheckRuleSearchVO searchVO) {
        log.info("查询指标校验规则列表，入参：{}", JSON.toJSONString(searchVO));
        Result result = targetCheckRuleService.queryList(searchVO);
        log.info("查询指标校验规则列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断规则编码是否存在
     */
    @PostMapping("/ruleCodeIsExist")
    @ResponseBody
    public Result ruleCodeIsExist(@RequestBody TargetCheckRuleVO targetCheckRuleVO) {
        log.info("判断规则编码是否存在，入参规则编码：{}", targetCheckRuleVO.getRuleCode());
        Result result = targetCheckRuleService.ruleCodeIsExist(targetCheckRuleVO.getRuleCode());
        log.info("判断规则编码是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改指标校验规则
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody TargetCheckRuleVO targetCheckRuleVO) {
        log.info("添加/修改指标校验规则，入参：{}", JSON.toJSONString(targetCheckRuleVO));
        Result result = targetCheckRuleService.addOrUpdate(targetCheckRuleVO);
        log.info("添加/修改指标校验规则，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询指标校验规则
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody TargetCheckRuleVO targetCheckRuleVO) {
        log.info("查询指标校验规则信息，入参规则ID：{}", targetCheckRuleVO.getId());
        Result result = targetCheckRuleService.queryById(targetCheckRuleVO.getId());
        log.info("查询指标校验规则信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除指标校验规则
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody TargetCheckRuleVO targetCheckRuleVO) {
        log.info("删除指标校验规则，入参规则ID：{}", targetCheckRuleVO.getId());
        Result result = targetCheckRuleService.delete(targetCheckRuleVO.getId());
        log.info("删除指标校验规则，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用/停用指标校验规则
     */
    @PostMapping("/startOrStop")
    @ResponseBody
    public Result startOrStop(@RequestBody TargetCheckRuleVO targetCheckRuleVO) {
        log.info("启用/停用指标校验规则，入参：{}", JSON.toJSONString(targetCheckRuleVO));
        Result result = targetCheckRuleService.startOrStop(targetCheckRuleVO);
        log.info("启用/停用指标校验规则，返回：{}", JSON.toJSONString(result));
        return result;
    }

}