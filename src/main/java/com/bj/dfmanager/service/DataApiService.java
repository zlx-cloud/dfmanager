package com.bj.dfmanager.service;

import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.dataapi.DataApiSearchVO;
import com.bj.dfmanager.vo.dataapi.DataApiVO;

public interface DataApiService {

    /**
     * 查询数据API列表
     */
    Result queryList(DataApiSearchVO dataApiSearchVO);

    /**
     * 判断资源对象编码是否存在
     */
    Result resObjCodeIsExist(String resObjCode);

    /**
     * 添加/修改数据API
     */
    Result addOrUpdate(DataApiVO dataApiVO);

    /**
     * 查询数据API信息
     */
    Result queryById(Integer id);

    /**
     * 删除数据API信息
     */
    Result delete(Integer id);

    /**
     * 查询数据API编码和名称
     */
    Result queryDataApi();

    /**
     * 根据资源对象编码查询数据API
     */
    Result queryDataApiByCode(String resObjCode);
}
