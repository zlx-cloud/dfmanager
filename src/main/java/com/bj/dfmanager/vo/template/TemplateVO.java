package com.bj.dfmanager.vo.template;

import lombok.Data;

/**
 * 模板 VO
 */
@Data
public class TemplateVO {

    private Integer id;

    // 标题
    private String title;

    // 描述
    private String description;

    // 代码
    private String code;

}