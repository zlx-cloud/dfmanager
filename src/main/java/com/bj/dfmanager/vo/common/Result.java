package com.bj.dfmanager.vo.common;

import lombok.Builder;
import lombok.Data;

/**
 * 接口返回对象
 */
@Data
@Builder
public class Result {

    // 状态码
    private int code;

    // 提示信息
    private String message;

    // 数据
    private Object data;

    // 返回成功信息
    public static Result success(Object data, String message) {
        return Result.builder().data(data).code(200).message(message).build();
    }

    // 返回失败信息
    public static Result fail(Object data, String message) {
        return Result.builder().data(data).code(300).message(message).build();
    }

    // 返回异常信息
    public static Result error(Object data, String message) {
        return Result.builder().data(data).code(500).message(message).build();
    }

}