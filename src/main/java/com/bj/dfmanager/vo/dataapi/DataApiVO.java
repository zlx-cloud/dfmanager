package com.bj.dfmanager.vo.dataapi;

import lombok.Data;

/**
 * 数据API VO
 */
@Data
public class DataApiVO {

    private Integer id;

    // 资源对象编码
    private String resObjCode;

    // 资源对象名称
    private String resObjName;

    // 获取方法
    private String resObjUrl;

    // 描述
    private String resObjDesc;

    // API
    private String resObjApi;

}