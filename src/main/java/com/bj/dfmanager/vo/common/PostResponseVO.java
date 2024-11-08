package com.bj.dfmanager.vo.common;

import lombok.Data;

/**
 * POST请求返回
 */
@Data
public class PostResponseVO {

    // 响应时长（毫秒）
    private Long time;

    // 响应码
    private Integer code;

    // 响应结果
    private String result;

}