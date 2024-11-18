package com.bj.dfmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bj.dfmanager.entity.MonitorDisk;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface MonitorDiskMapper extends BaseMapper<MonitorDisk> {

    @Update("UPDATE DFMANAGER.T_MONITOR_DISK SET TOTAL = #{total},USED = #{used},FREE = #{free} WHERE IP = #{ip}")
    void updateDiskMonitor(@Param("ip") String ip,
                           @Param("total") String total,
                           @Param("used") String used,
                           @Param("free") String free);

}