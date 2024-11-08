package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleSearchVO;
import com.bj.dfmanager.vo.targetcheckrule.TargetCheckRuleVO;

public interface TargetCheckRuleService {

    /**
     * 查询指标校验规则列表
     */
    Result queryList(TargetCheckRuleSearchVO searchVO);

    /**
     * 判断规则编码是否存在
     */
    Result ruleCodeIsExist(String ruleCode);

    /**
     * 添加/修改指标校验规则
     */
    Result addOrUpdate(TargetCheckRuleVO targetCheckRuleVO);

    /**
     * 查询指标校验规则
     */
    Result queryById(Integer id);

    /**
     * 删除指标校验规则
     */
    Result delete(Integer id);

    /**
     * 启用/停用指标校验规则
     */
    Result startOrStop(TargetCheckRuleVO targetCheckRuleVO);

}
