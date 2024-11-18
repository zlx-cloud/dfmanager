package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 模型
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL")
public class Model {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 模型名称
    @TableField("MODEL_NAME")
    private String modelName;

    // 模型描述
    @TableField("MODEL_DESC")
    private String modelDesc;

    // 应用场景
    @TableField("APPLICATION_SCENARIOS")
    private String applicationScenarios;

    // 有效期
    @TableField("EFFECTIVE_DATE")
    private String effectiveDate;

    // 模型指标
    @TableField(exist = false)
    private List<ModelTarget> modelTargetList;

    // 预警规则
    @TableField("WARN_CONDITION")
    private String warnCondition;

    // 进入条件状态
    @TableField("IN_CONDITION_STATUS")
    private String inConditionStatus;

    // 进入条件
    @TableField("IN_CONDITION")
    private String inCondition;

    // 排他规则状态
    @TableField("EX_CONDITION_STATUS")
    private String exConditionStatus;

    // 排他规则
    @TableField("EX_CONDITION")
    private String exCondition;

    // 模型状态
    @TableField("MODEL_STATUS")
    private String modelStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    @TableField(exist = false)
    private String gradeId;

    @TableField(exist = false)
    private String gradeName;

    @TableField(exist = false)
    private String realName;

    @TableField("USER_ID")
    private Integer userId;

}