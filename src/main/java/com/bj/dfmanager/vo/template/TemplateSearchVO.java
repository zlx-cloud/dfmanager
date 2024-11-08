package com.bj.dfmanager.vo.template;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 模板查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateSearchVO extends PageVO {

    // 标题
    private String title;

}