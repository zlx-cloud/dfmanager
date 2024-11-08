package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.DataApi;
import com.bj.dfmanager.vo.dataapi.ResObjVO;

import java.util.List;

public interface DataApiMapper extends BaseMapper<DataApi> {

    List<ResObjVO> queryResObj();

}