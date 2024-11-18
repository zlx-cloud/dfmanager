package com.bj.dfmanager.vo.monitor;

import lombok.Data;

@Data
public class MonitorDiskVO {

    private String ip;

    private String total;

    private String used;

    private String free;

}