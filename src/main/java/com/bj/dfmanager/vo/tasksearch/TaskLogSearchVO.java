package com.bj.dfmanager.vo.tasksearch;

import com.bj.dfmanager.vo.common.PageVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务日志查询对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskLogSearchVO extends PageVO {

    // 主键
    private String objectId;

    // 数据ID
    private String dataId;

    // 任务开始时间-起
    private String startTime;

    // 任务开始时间-止
    private String endTime;

    // 状态
    private String status;

    private String tdrw;

    private String jkmc;

    // 关键字
    private String keyWord;

}
