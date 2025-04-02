package com.bj.dfmanager.service.impl;

import com.bj.dfmanager.entity.Dept;
import com.bj.dfmanager.mapper.DeptMapper;
import com.bj.dfmanager.service.DeptService;
import com.bj.dfmanager.util.MenuUtils;
import com.bj.dfmanager.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Result deptTree() {
        List<Dept> deptList = deptMapper.getAllDept();
        List<Dept> deptTree = MenuUtils.getDeptChildren(deptList, -999);
        return Result.success(deptTree, "查询部门树成功");
    }

}
