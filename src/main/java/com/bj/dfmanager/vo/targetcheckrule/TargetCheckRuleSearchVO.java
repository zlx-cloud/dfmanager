package com.bj.dfmanager.vo.targetcheckrule;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规则查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TargetCheckRuleSearchVO extends PageVO {

    // 规则编码
    private String ruleCode;

    // 规则名称
    private String ruleName;

    // 规则状态
    private String ruleStatus;

}