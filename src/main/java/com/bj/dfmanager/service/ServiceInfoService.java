package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.service.ServiceInfoSearchVO;
import com.bj.dfmanager.vo.service.ServiceInfoVO;

public interface ServiceInfoService {

    /**
     * 查询服务目录列表
     */
    Result queryList(ServiceInfoSearchVO serviceInfoSearchVO);

    /**
     * 判断服务编码是存在
     */
    Result serviceCodeIsExist(String serviceCode);

    /**
     * 添加/修改服务目录
     */
    Result addOrUpdate(ServiceInfoVO serviceInfoVO);

    /**
     * 查询服务目录
     */
    Result queryById(Integer id);

    /**
     * 删除服务目录
     */
    Result delete(Integer id);

    /**
     * 启用/停用服务目录
     */
    Result startOrStop(ServiceInfoVO serviceInfoVO);

    /**
     * 查询启用状态下服务接口
     */
    Result queryEnableService(ServiceInfoSearchVO serviceInfoSearchVO);

    /**
     * 接口探测
     */
    Result serviceTest(Integer id);

}