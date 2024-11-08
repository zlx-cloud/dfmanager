package com.bj.dfmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 用户
 */
@Data
@TableName(value = "DFMANAGER.T_USER")
public class User {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    // 用户名/登录名
    @TableField("USER_NAME")
    private String userName;

    // 登录密码
    @TableField("PASSWORD")
    private String password;

    // 凭证
    @TableField("TOKEN")
    private String token;

    // 真实姓名
    @TableField("REAL_NAME")
    private String realName;

    // 警号
    @TableField("POLICE_NO")
    private String policeNo;

    // 身份证号
    @TableField("ID_CARD")
    private String idCard;

    // 性别
    @TableField("GENDER")
    private String gender;

    // 用户状态
    @TableField("USER_STATUS")
    private String userStatus;

    // 创建时间
    @TableField("CREATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    // 修改时间
    @TableField("UPDATE_TIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

}