package com.bj.dfmanager.vo.target;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 指标查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TargetSearchVO extends PageVO {

    // 指标编码
    private String targetCode;

    // 指标名称
    private String targetName;

    // 同步/异步
    private String async;

    // 适用范围
    private String applicableScope;

    // 指标状态
    private String targetStatus;

    // 请求方法
    private String requestMethod;

}