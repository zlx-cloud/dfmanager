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
    //@TableField("DEVELOPER")
    //private String developer;

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

    // 建模背景/用途
    @TableField("BACKGROUND")
    private String background;

    // 模型需求描述
    @TableField("DEMAND_DESC")
    private String demandDesc;

    // 核查策略
    @TableField("CHECK_STRATEGY")
    private String checkStrategy;

    // 模型预期上线时间
    @TableField("ONLINE_TIME")
    private String onlineTime;

    // 模型预期下线时间
    @TableField("OFFLINE_TIME")
    private String offlineTime;

    // 申请人姓名
    @TableField("APPLY_USER")
    private String applyUser;

    @TableField(exist = false)
    private String applyUserName;

    // 申请人单位
    @TableField("APPLY_DEPARTMENT")
    private String applyDepartment;

    // 申请人联系方式
    @TableField("APPLY_CONTACT")
    private String applyContact;

    // 建模申请类型：Y-公开 N-不公开
    @TableField("APPLY_TYPE")
    private String applyType;

    // 保存状态：0-暂存 1-保存
    //@TableField("TEMP_STORE")
    //private String tempStore;

    // 退回意见
    @TableField("ADVICE")
    private String advice;

    @TableField(exist = false)
    private String demandUnitName;

    @TableField(exist = false)
    private String applyDepartmentName;

}
