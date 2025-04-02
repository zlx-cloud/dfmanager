package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bj.dfmanager.entity.ModelDemand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ModelDemandMapper extends BaseMapper<ModelDemand> {

    IPage<ModelDemand> selectByPage(Page<Object> objectPage,
                                    @Param("modelName") String modelName,
                                    @Param("modelType") String modelType,
                                    @Param("useScope") String useScope,
                                    @Param("demandUnit") String demandUnit,
                                    @Param("demandStatus") String demandStatus,
                                    @Param("userId") String userId);

    ModelDemand queryId(@Param("id") Integer id);

    IPage<ModelDemand> queryMyList(Page<Object> objectPage,
                                   @Param("modelName") String modelName,
                                   @Param("modelType") String modelType,
                                   @Param("useScope") String useScope,
                                   @Param("demandUnit") String demandUnit,
                                   @Param("userId") Integer userId);

    IPage<ModelDemand> queryPublicList(Page<Object> objectPage,
                                       @Param("modelName") String modelName,
                                       @Param("modelType") String modelType,
                                       @Param("useScope") String useScope,
                                       @Param("demandUnit") String demandUnit,
                                       @Param("demandStatus") String demandStatus,
                                       @Param("userId") String userId);

    @Update("update T_MODEL_DEMAND set DEMAND_STATUS=#{demandStatus},ADVICE=#{advice},UPDATE_TIME=SYSDATE where ID=#{id}")
    int updateStage(@Param("id") Integer id,
                    @Param("demandStatus") String demandStatus,
                    @Param("advice") String advice);

}
