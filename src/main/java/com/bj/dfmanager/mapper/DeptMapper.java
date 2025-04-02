package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper extends BaseMapper<Dept> {

    @Select("SELECT * FROM DFMANAGER.T_DEPT")
    List<Dept> getAllDept();

}
