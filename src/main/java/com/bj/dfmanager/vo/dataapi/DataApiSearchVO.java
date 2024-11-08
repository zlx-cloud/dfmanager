package com.bj.dfmanager.vo.dataapi;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 数据API查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DataApiSearchVO extends PageVO {

    // 资源对象编码
    private String resObjCode;

    // 资源对象名称
    private String resObjName;

    // 获取方法
    private String resObjUrl;

}