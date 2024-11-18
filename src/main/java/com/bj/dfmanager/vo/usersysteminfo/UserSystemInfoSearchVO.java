package com.bj.dfmanager.vo.usersysteminfo;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户系统信息查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSystemInfoSearchVO extends PageVO {

    // 系统编码
    private String systemCode;

    // 系统名称
    private String systemName;

    // 登录名
    private String loginName;

    // 授权码
    private String authCode;

    // 联系人
    private String contactUser;

    // 电话
    private String telInfo;

    // 状态
    private String status;

    // 身份证号
    private String certNo;

}