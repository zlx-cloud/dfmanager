package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DFMANAGER.T_MODEL_GRADE")
public class ModelGrade {

    @TableId(type = IdType.AUTO)
    private Integer modelId;

    @TableField("GRADE")
    private Integer grade;

}