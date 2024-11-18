package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户系统信息
 */
@Data
@TableName(value = "DFMANAGER.T_USE_SYSTEM_INFO")
public class UserSystemInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    // 系统编码
    @TableField("SYSTEM_CODE")
    private String systemCode;

    // 系统名称
    @TableField("SYSTEM_NAME")
    private String systemName;

    // 登录名
    @TableField("LOGIN_NAME")
    private String loginName;

    // 登录密码
    @TableField("LOGIN_PWD")
    private String loginPwd;

    // 授权码
    @TableField("AUTH_CODE")
    private String authCode;

    // 联系人
    @TableField("CONTACT_USER")
    private String contactUser;

    // 电话
    @TableField("TEL_INFO")
    private String telInfo;

    // 队列
    @TableField("RESULT_QUEUE")
    private String resultQueue;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    // 状态
    @TableField("STATUS")
    private String status;

    // 身份证号
    @TableField("CERT_NO")
    private String certNo;

}