package com.bj.dfmanager.vo.user;

import lombok.Data;

import java.util.List;

/**
 * 用户 VO
 */
@Data
public class UserVO {

    private Integer userId;

    // 用户名/登录名
    private String userName;

    // 登录密码
    private String password;

    // 真实姓名
    private String realName;

    // 警号
    private String policeNo;

    // 身份证号
    private String idCard;

    // 性别
    private String gender;

    // 用户状态
    private String userStatus;

    // 角色
    private List<Integer> roleList;

    // 部门
    private String department;

    // 联系方式
    private String contact;

}
