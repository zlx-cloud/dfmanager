package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.ServiceCol;
import com.bj.dfmanager.entity.ServiceInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ServiceInfoMapper extends BaseMapper<ServiceInfo> {

    /**
     * 查询用户系统服务
     */
    @Select("SELECT SI.*,CASE WHEN SSR.SERVICE_ID IS NOT NULL THEN TRUE ELSE FALSE END AS CHECKED FROM T_SERVICE_INFO SI " +
            "LEFT JOIN (SELECT * FROM T_SYSTEM_SERVICE_REL WHERE SYSTEM_ID = #{id}) SSR ON SSR.SERVICE_ID = SI.ID " +
            "WHERE SI.SERVICE_STATUS = 'Y'")
    List<ServiceInfo> userSystemService(@Param("id") Integer id);

    /**
     * 查询用户系统的服务字段
     */
    @Select("SELECT SC.*,CASE WHEN SSKR.SERVICE_ID IS NOT NULL THEN TRUE ELSE FALSE END AS CHECKED FROM T_SERVICE_COL SC " +
            "LEFT JOIN (SELECT * FROM T_SYSTEM_SERVICE_KEY_REL WHERE SYSTEM_ID = #{id} AND SERVICE_ID = #{serviceId} ) SSKR " +
            "ON SC.SERVICE_ID = SSKR.SERVICE_ID AND SC.KEY_CODE = SSKR.KEY_NAME WHERE SC.SERVICE_ID = #{serviceId}")
    List<ServiceCol> userSystemServiceCol(@Param("id") Integer id, @Param("serviceId") Integer serviceId);

}
