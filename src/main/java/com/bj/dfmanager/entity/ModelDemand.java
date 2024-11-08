package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 模型需求
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL_DEMAND")
public class ModelDemand {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 模型名称
    @TableField("MODEL_NAME")
    private String modelName;

    // 模型类型
    @TableField("MODEL_TYPE")
    private String modelType;

    // 模型需求
    @TableField("MODEL_DEMAND")
    private String modelDemand;

    // 使用范围
    @TableField("USE_SCOPE")
    private String useScope;

    // 需求单位
    @TableField("DEMAND_UNIT")
    private String demandUnit;

    // 开发人员
    @TableField("DEVELOPER")
    private String developer;

    // 需求状态
    @TableField("DEMAND_STATUS")
    private String demandStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}