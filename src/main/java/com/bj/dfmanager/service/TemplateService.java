package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.template.TemplateSearchVO;
import com.bj.dfmanager.vo.template.TemplateVO;

public interface TemplateService {

    /**
     * 查询模板列表
     */
    Result queryList(TemplateSearchVO templateSearchVO);

    /**
     * 添加/修改模板
     */
    Result addOrUpdate(TemplateVO templateVO);

    /**
     * 查询模板信息
     */
    Result queryById(Integer id);

    /**
     * 删除模板信息
     */
    Result delete(Integer id);

}