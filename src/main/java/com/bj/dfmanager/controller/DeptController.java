package com.bj.dfmanager.controller;

import com.bj.dfmanager.service.DeptService;
import com.bj.dfmanager.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dept")
@Slf4j
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("/deptTree")
    @ResponseBody
    public Result deptTree() {
        Result result = deptService.deptTree();
        return result;
    }

}
