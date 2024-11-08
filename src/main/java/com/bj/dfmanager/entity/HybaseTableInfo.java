package com.bj.dfmanager.entity;

import lombok.Data;

@Data
public class HybaseTableInfo {

    // 表名
    private String tableName;

    private String tableComment;

    // 入库时间字段
    private String etlDate;

    // 业务日期字段
    private String businessDate;

}