package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.ModelTarget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModelTargetMapper extends BaseMapper<ModelTarget> {

    /**
     * 批量插入
     */
    int saveBatch(@Param("list") List<ModelTarget> list);

}