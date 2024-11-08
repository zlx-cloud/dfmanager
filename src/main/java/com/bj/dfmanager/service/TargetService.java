package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.target.TargetSearchVO;
import com.bj.dfmanager.vo.target.TargetVO;

public interface TargetService {

    /**
     * 查询指标列表
     */
    Result queryList(TargetSearchVO targetSearchVO);

    /**
     * 判断指标编码是否存在
     */
    Result targetCodeIsExist(String targetCode);

    /**
     * 新增/修改指标
     */
    Result addOrUpdate(TargetVO targetVO);

    /**
     * 查询指标
     */
    Result queryById(Integer id);

    /**
     * 删除指标
     */
    Result delete(Integer id);

    /**
     * 启用/停用指标
     */
    Result startOrStop(TargetVO targetVO);

    /**
     * 查询指标列表
     */
    Result targetList();

    /**
     * 指标测试
     */
    Result targetTest(TargetVO targetVO);

}