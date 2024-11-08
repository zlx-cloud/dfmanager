package com.bj.dfmanager.vo.usersysteminfo;

import lombok.Data;

import java.util.List;

/**
 * 用户系统信息 VO
 */
@Data
public class UserSystemInfoVO {

    private Integer id;

    // 系统编码
    private String systemCode;

    // 系统名称
    private String systemName;

    // 登录名
    private String loginName;

    // 登录密码
    private String loginPwd;

    // 授权码
    private String authCode;

    // 联系人
    private String contactUser;

    // 电话
    private String telInfo;

    // 队列
    private String resultQueue;

    // 状态
    private String status;

    // 服务
    private List<Integer> serviceList;

    // 指标
    private List<Integer> targetList;

}