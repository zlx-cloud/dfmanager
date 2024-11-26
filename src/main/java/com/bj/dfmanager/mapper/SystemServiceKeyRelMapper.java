package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.SystemServiceKeyRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemServiceKeyRelMapper extends BaseMapper<SystemServiceKeyRel> {

    /**
     * 用户系统绑定服务
     */
    void userSystemBindService(@Param("id") Integer id,
                               @Param("serviceId") Integer serviceId,
                               @Param("list") List<String> serviceList);

}
