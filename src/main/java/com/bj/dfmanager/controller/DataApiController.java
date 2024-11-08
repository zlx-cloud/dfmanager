package com.bj.dfmanager.controller;

import com.alibaba.fastjson2.JSON;
import com.bj.dfmanager.service.DataApiService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.dataapi.DataApiSearchVO;
import com.bj.dfmanager.vo.dataapi.DataApiVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 数据API
 */
@RestController
@RequestMapping("/dataApi")
@Slf4j
public class DataApiController {

    @Resource
    private DataApiService dataApiService;

    /**
     * 查询数据API列表
     */
    @PostMapping("/queryList")
    @ResponseBody
    public Result queryList(@RequestBody DataApiSearchVO dataApiSearchVO) {
        log.info("查询数据API列表，入参：{}", JSON.toJSONString(dataApiSearchVO));
        Result result = dataApiService.queryList(dataApiSearchVO);
        log.info("查询数据API列表，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 判断资源对象编码是否存在
     */
    @PostMapping("/resObjCodeIsExist")
    @ResponseBody
    public Result resObjCodeIsExist(@RequestBody DataApiSearchVO dataApiSearchVO) {
        log.info("判断资源对象编码是否存在，入参资源对象编码：{}", dataApiSearchVO.getResObjCode());
        Result result = dataApiService.resObjCodeIsExist(dataApiSearchVO.getResObjCode());
        log.info("判断资源对象编码是否存在，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加/修改数据API
     */
    @PostMapping("/addOrUpdate")
    @ResponseBody
    public Result addOrUpdate(@RequestBody DataApiVO dataApiVO) {
        log.info("添加/修改数据API，入参：{}", JSON.toJSONString(dataApiVO));
        Result result = dataApiService.addOrUpdate(dataApiVO);
        log.info("添加/修改数据API，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询数据API信息
     */
    @PostMapping("/queryById")
    @ResponseBody
    public Result queryById(@RequestBody DataApiVO dataApiVO) {
        log.info("查询数据API信息，入参数据API ID：{}", dataApiVO.getId());
        Result result = dataApiService.queryById(dataApiVO.getId());
        log.info("查询数据API信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除数据API信息
     */
    @PostMapping("/delete")
    @ResponseBody
    public Result delete(@RequestBody DataApiVO dataApiVO) {
        log.info("删除数据API信息，入参数据API ID：{}", dataApiVO.getId());
        Result result = dataApiService.delete(dataApiVO.getId());
        log.info("删除数据API信息，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 查询数据API编码和名称
     */
    @PostMapping("/queryDataApi")
    @ResponseBody
    public Result queryDataApi() {
        Result result = dataApiService.queryDataApi();
        log.info("查询数据API编码和名称，返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 根据资源对象编码查询数据API
     */
    @PostMapping("/queryDataApiByCode")
    @ResponseBody
    public Result queryDataApiByCode(@RequestBody DataApiVO dataApiVO) {
        log.info("根据资源对象编码查询数据API，入参资源对象编码：{}", dataApiVO.getResObjCode());
        Result result = dataApiService.queryDataApiByCode(dataApiVO.getResObjCode());
        log.info("根据资源对象编码查询数据API，返回：{}", JSON.toJSONString(result));
        return result;
    }

}