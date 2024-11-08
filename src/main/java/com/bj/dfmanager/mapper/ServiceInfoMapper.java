package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.ServiceInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ServiceInfoMapper extends BaseMapper<ServiceInfo> {

    /**
     * 查询用户系统已拥有的服务
     */
    @Select("SELECT SI.* FROM T_SYSTEM_SERVICE_REL SSR LEFT JOIN T_SERVICE_INFO SI ON SSR.SERVICE_ID = SI.ID " +
            "WHERE SSR.SYSTEM_ID = #{id} AND SI.SERVICE_STATUS = 'Y' ORDER BY SI.CREATE_TIME DESC")
    List<ServiceInfo> userSystemHaveService(@Param("id") Integer id);

    /**
     * 查询用户系统未拥有的服务
     */
    @Select("SELECT SI.* FROM T_SERVICE_INFO SI WHERE SI.SERVICE_STATUS ='Y' AND SI.ID NOT IN " +
            "(SELECT SSR.SERVICE_ID FROM T_SYSTEM_SERVICE_REL SSR WHERE SSR.SYSTEM_ID = #{id}) ORDER BY SI.CREATE_TIME DESC")
    List<ServiceInfo> userSystemNotHaveService(@Param("id") Integer id);

}