package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 服务目录
 */
@Data
@TableName(value = "DFMANAGER.T_SERVICE_INFO")
public class ServiceInfo {

    // ID
    @TableId(type = IdType.AUTO)
    private Integer id;

    // 服务目录菜单ID
    @TableField("SERVICE_MENU_ID")
    private Integer serviceMenuId;

    // 服务编码
    @TableField("SERVICE_CODE")
    private String serviceCode;

    // 服务名称
    @TableField("SERVICE_NAME")
    private String serviceName;

    // 服务URL
    @TableField("SERVICE_URL")
    private String serviceUrl;

    // 服务描述
    @TableField("SERVICE_DESC")
    private String serviceDesc;

    // 服务API
    @TableField("SERVICE_API")
    private String serviceApi;

    // 服务状态
    @TableField("SERVICE_STATUS")
    private String serviceStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}