package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.TemplateService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.template.TemplateSearchVO;
import com.bj.dfmanager.vo.template.TemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 模板
 */
@RestController
@RequestMapping("/template")
@Slf4j
public class TemplateController {

    @Resource
    private TemplateService templateService;

    /**
     * 查询模板列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody TemplateSearchVO templateSearchVO) {
        log.info("查询模板列表，入参：{}", JSON.toJSONString(templateSearchVO));
        Result result = templateService.queryList(templateSearchVO);
        log.info("查询模板列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改模板
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody TemplateVO templateVO) {
        log.info("添加/修改模板，入参：{}", JSON.toJSONString(templateVO));
        Result result = templateService.addOrUpdate(templateVO);
        log.info("添加/修改模板，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询模板信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody TemplateVO templateVO) {
        log.info("查询模板信息，入参模板ID：{}", templateVO.getId());
        Result result = templateService.queryById(templateVO.getId());
        log.info("查询模板信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除模板信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody TemplateVO templateVO) {
        log.info("删除模板信息，入参模板ID：{}", templateVO.getId());
        Result result = templateService.delete(templateVO.getId());
        log.info("删除模板信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

}