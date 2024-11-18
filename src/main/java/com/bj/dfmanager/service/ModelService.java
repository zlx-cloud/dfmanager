package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.model.ModelSearchVO;
import com.bj.dfmanager.vo.model.ModelVO;
import com.bj.dfmanager.vo.common.Result;

public interface ModelService {

    /**
     * 查询模型列表
     */
    Result queryList(ModelSearchVO modelSearchVO);

    /**
     * 新增/修改模型
     */
    Result addOrUpdate(ModelVO modelVO);

    /**
     * 查询模型
     */
    Result queryById(Integer id);

    /**
     * 删除模型
     */
    Result delete(Integer id);

    /**
     * 启用/停用模型
     */
    Result startOrStop(ModelVO modelVO);

    /**
     * 模型结果查询
     */
    Result queryResultList(ModelSearchVO modelSearchVO);

    /**
     * 模型结果详情
     */
    Result queryResultDetail(ModelSearchVO modelSearchVO);

    /**
     * 查询等级分类列表
     */
    Result queryGradeList();

}