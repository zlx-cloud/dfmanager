package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "DFMANAGER.F_TASK_KEY_VALUE")
public class TaskKeyValue {

    @TableField("TYPE")
    private String type;

    @TableField("KEY")
    private String key;

    @TableField("VALUE1")
    private String value1;

    @TableField("VALUE2")
    private String value2;

    @TableField("VALUE3")
    private String value3;

    @TableField("VALUE4")
    private String value4;

    @TableField("VALUE5")
    private String value5;

}