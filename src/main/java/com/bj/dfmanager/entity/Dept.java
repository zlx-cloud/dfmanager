package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName(value = "DFMANAGER.T_DEPT")
public class Dept {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("PID")
    private Integer pid;

    @TableField("NAME")
    private String name;

    @TableField(exist = false)
    private List<Dept> children;

}
