package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.DataApi;
import com.bj.dfmanager.mapper.DataApiMapper;
import com.bj.dfmanager.service.DataApiService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.dataapi.DataApiSearchVO;
import com.bj.dfmanager.vo.dataapi.DataApiVO;
import com.bj.dfmanager.vo.dataapi.ResObjVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DataApiServiceImpl implements DataApiService {

    @Resource
    private DataApiMapper dataApiMapper;

    /**
     * 查询数据API列表
     */
    @Override
    public Result queryList(DataApiSearchVO dataApiSearchVO) {
        LambdaQueryWrapper<DataApi> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(dataApiSearchVO.getResObjCode()),
                DataApi::getResObjCode, dataApiSearchVO.getResObjCode());
        queryWrapper.like(StringUtils.isNotEmpty(dataApiSearchVO.getResObjName()),
                DataApi::getResObjName, dataApiSearchVO.getResObjName());
        queryWrapper.like(StringUtils.isNotEmpty(dataApiSearchVO.getResObjUrl()),
                DataApi::getResObjUrl, dataApiSearchVO.getResObjUrl());
        queryWrapper.orderByDesc(DataApi::getCreateTime);
        IPage<DataApi> page = dataApiMapper.selectPage(new Page<>(dataApiSearchVO.getCurrent(),
                dataApiSearchVO.getSize()), queryWrapper);
        return Result.success(page, "查询数据API列表成功");
    }

    /**
     * 判断资源对象编码是否存在
     */
    @Override
    public Result resObjCodeIsExist(String resObjCode) {
        LambdaQueryWrapper<DataApi> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DataApi::getResObjCode, resObjCode);
        int count = dataApiMapper.selectCount(queryWrapper).intValue();
        if (count == 0) {
            return Result.success(null, "资源对象编码不存在，可以添加");
        } else {
            return Result.fail(null, "资源对象编码存在，不可以添加");
        }
    }

    /**
     * 添加/修改数据API
     */
    @Override
    public Result addOrUpdate(DataApiVO dataApiVO) {
        int num;
        DataApi dataApi = new DataApi();
        BeanUtils.copyProperties(dataApiVO, dataApi);
        if (null == dataApi.getId()) {
            dataApi.setCreateTime(new Date());
            num = dataApiMapper.insert(dataApi);
        } else {
            dataApi.setUpdateTime(new Date());
            num = dataApiMapper.updateById(dataApi);
        }
        if (num > 0) {
            return Result.success(null, "操作成功");
        } else {
            return Result.fail(null, "操作失败");
        }
    }

    /**
     * 查询数据API信息
     */
    @Override
    public Result queryById(Integer id) {
        DataApi dataApi = dataApiMapper.selectById(id);
        return Result.success(dataApi, "查询数据API信息成功");
    }

    /**
     * 删除数据API信息
     */
    @Override
    public Result delete(Integer id) {
        int num = dataApiMapper.deleteById(id);
        if (num > 0) {
            return Result.success(null, "删除数据API信息成功");
        } else {
            return Result.fail(null, "删除数据API信息失败");
        }
    }

    /**
     * 查询数据API编码和名称
     */
    @Override
    public Result queryDataApi() {
        List<ResObjVO> list = dataApiMapper.queryResObj();
        return Result.success(list, "查询数据API编码和名称成功");
    }

    /**
     * 根据资源对象编码查询数据API
     */
    @Override
    public Result queryDataApiByCode(String resObjCode) {
        LambdaQueryWrapper<DataApi> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DataApi::getResObjCode, resObjCode);
        DataApi dataApi = dataApiMapper.selectOne(queryWrapper);
        return Result.success(dataApi, "根据资源对象编码查询数据API成功");
    }

}