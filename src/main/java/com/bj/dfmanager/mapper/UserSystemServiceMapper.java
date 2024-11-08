package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.UserSystemService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserSystemServiceMapper extends BaseMapper<UserSystemService> {

    /**
     * 用户系统绑定服务
     */
    void userSystemBindService(@Param("id") Integer id, @Param("list") List<Integer> serviceList);

}