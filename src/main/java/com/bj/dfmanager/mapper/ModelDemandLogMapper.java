package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelDemandLog;
import org.apache.ibatis.annotations.Param;

public interface ModelDemandLogMapper extends BaseMapper<ModelDemandLog> {

    IPage<ModelDemandLog> queryModelDemandLog(@Param("modelName") String modelName,
                                              @Param("userName") String userName,
                                              Page<Object> objectPage);

}
