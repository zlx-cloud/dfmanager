package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.modeldemand.ModelDemandSearchVO;
import com.bj.dfmanager.vo.modeldemand.ModelDemandVO;

public interface ModelDemandService {

    /**
     * 查询模型需求列表
     */
    Result queryList(ModelDemandSearchVO modelDemandSearchVO);

    /**
     * 模型申请
     */
    Result apply(ModelDemandVO modelDemandVO);

    /**
     * 查询模型需求
     */
    Result queryById(Integer id);

    /**
     * 模型分配
     */
    Result allocation(ModelDemandVO modelDemandVO);

    /**
     * 模型需求阶段变更
     */
    Result updateStage(ModelDemandVO modelDemandVO);

    /**
     * 查看分配给自己的模型需求
     */
    Result queryMyList(ModelDemandSearchVO modelDemandSearchVO);

}