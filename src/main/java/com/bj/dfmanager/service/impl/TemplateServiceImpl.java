package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.Template;
import com.bj.dfmanager.mapper.TemplateMapper;
import com.bj.dfmanager.service.TemplateService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.template.TemplateSearchVO;
import com.bj.dfmanager.vo.template.TemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class TemplateServiceImpl implements TemplateService {

    @Resource
    private TemplateMapper templateMapper;

    /**
     * 查询模板列表
     */
    @Override
    public Result queryList(TemplateSearchVO templateSearchVO) {
        LambdaQueryWrapper<Template> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(templateSearchVO.getTitle()),
                Template::getTitle, templateSearchVO.getTitle());
        queryWrapper.orderByDesc(Template::getCreateTime);
        IPage<Template> page = templateMapper.selectPage(new Page<>(templateSearchVO.getCurrent(),
                templateSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询模板列表成功");
    }

    /**
     * 添加/修改模板
     */
    @Override
    public Result addOrUpdate(TemplateVO templateVO) {
        int num;
        Template template = new Template();
        BeanUtils.copyProperties(templateVO, template);
        if (null == template.getId()) {
            template.setCreateTime(new Date());
            num = templateMapper.insert(template);
        } else {
            template.setUpdateTime(new Date());
            num = templateMapper.updateById(template);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询模板信息
     */
    @Override
    public Result queryById(Integer id) {
        Template template = templateMapper.selectById(id);
        return Result.success(template, "查询模板信息成功");
    }

    /**
     * 删除模板信息
     */
    @Override
    public Result delete(Integer id) {
        int num = templateMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除模板成功");
        } else {
            return Result.fail(null, "删除模板失败");
        }
    }

}