package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 模型需求日志
 */
@Data
@TableName(value = "DFMANAGER.T_MODEL_DEMAND_LOG")
public class ModelDemandLog {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("OPERATE_USER")
    private int operateUser;

    @TableField("OPERATE_TIME")
    private String operateTime;

    @TableField("MODEL_DEMAND_ID")
    private int modelDemandId;

    @TableField("OPERATE_ACTION")
    private String operateAction;

    @TableField(exist = false)
    private String modelName;

    @TableField(exist = false)
    private String userName;

}
