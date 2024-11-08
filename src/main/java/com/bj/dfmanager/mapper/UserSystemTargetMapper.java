package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.UserSystemTarget;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSystemTargetMapper extends BaseMapper<UserSystemTarget> {

    /**
     * 用户系统绑定指标
     */
    void userSystemBindTarget(@Param("id") Integer id, @Param("list") List<Integer> targetList);

}