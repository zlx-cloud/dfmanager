package com.bj.dfmanager.vo.user;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSearchVO extends PageVO {

    // 用户名
    private String userName;

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

    // 部门
    private String department;

}
