package com.bj.dfmanager.vo.model;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模型查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModelSearchVO extends PageVO {

    // 模型名称
    private String modelName;

    // 模型状态
    private String modelStatus;

    private String warnFlag;

    private String id;

    // 证件号
    private String certNo;

    // 航班号
    private String fltno;

    // 航班起始时间
    private String fltDateStart;

    // 航班截止时间
    private String fltDateEnd;

}