package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 模型计算结果
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL_RESULT")
public class ModelResult {

    @TableField("ID")
    private String id;

    @TableField("MODEL_ID")
    private Integer modelId;

    @TableField("DATA_ID")
    private String dataId;

    @TableField("WARN_FLAG")
    private String warnFlag;

    @TableField("PASSPORTNO")
    private String passportno;

    @TableField("FLTNO")
    private String fltno;

    @TableField("FLTNODATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date fltnodate;

    @TableField(exist = false)
    private String modelName;

    @TableField(exist = false)
    private String modelDesc;

    @TableField("CREATE_TIME")
    private Date createTime;

}