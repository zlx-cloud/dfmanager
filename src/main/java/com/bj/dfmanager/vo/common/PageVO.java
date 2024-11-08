package com.bj.dfmanager.vo.common;

import lombok.Data;

/**
 * 分页
 */
@Data
public class PageVO {

    // 当前页
    private int current;

    // 页大小
    private int size;

}