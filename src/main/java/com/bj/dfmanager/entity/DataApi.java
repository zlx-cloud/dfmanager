package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 数据API
 */
@Data
@TableName(value = "DFMANAGER.T_DATA_API")
public class DataApi {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 资源对象编码
    @TableField("RES_OBJ_CODE")
    private String resObjCode;

    // 资源对象名称
    @TableField("RES_OBJ_NAME")
    private String resObjName;

    // 获取方法
    @TableField("RES_OBJ_URL")
    private String resObjUrl;

    // 描述
    @TableField("RES_OBJ_DESC")
    private String resObjDesc;

    // API
    @TableField("RES_OBJ_API")
    private String resObjApi;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}