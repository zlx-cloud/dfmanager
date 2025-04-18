package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bj.dfmanager.service.TaskLogService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.tasksearch.TaskLogSearchVO;
import com.trs.hybase.client.TRSConnection;
import com.trs.hybase.client.TRSException;
import com.trs.hybase.client.TRSRecord;
import com.trs.hybase.client.TRSResultSet;
import com.trs.hybase.client.params.ConnectParams;
import com.trs.hybase.client.params.SearchParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class TaskLogServiceImpl implements TaskLogService {

    @Value("${hybase.url}")
    private String trsUrl;
    @Value("${hybase.username}")
    private String trsUserName;
    @Value("${hybase.password}")
    private String trsPassword;
    @Value("${hybase.tasklogtable}")
    private String tasklogtable;

    private TRSConnection trsConn = null;

    private void getTrsConn() {
        if (trsConn == null)
            trsConn = new TRSConnection(trsUrl, trsUserName, trsPassword, new ConnectParams());
    }

    private void closeTrsConn() {
        if (trsConn != null) {
            trsConn.close();
            trsConn = null;
        }
    }

    /**
     * 查询任务日志列表
     */
    @Override
    public Result queryList(TaskLogSearchVO taskLogSearchVO) {
        // 检索参数对象
        SearchParams param = new SearchParams();
        // 按任务开始时间降序排序
        param.setSortMethod("-START_DT");
        // 设置检索结果返回字段列表
        param.setReadColumns("OBJECTID;DATA_TYPE;DATA_ID;START_DT;FIN_DT;STATUS;ERROR_MSG;PARAMS;URL;msg");

        // 检索条件
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotEmpty(taskLogSearchVO.getDataId())) {
            sb.append("DATA_ID:").append(taskLogSearchVO.getDataId()).append(" AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getStatus())) {
            sb.append("STATUS:").append(taskLogSearchVO.getStatus()).append(" AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getStartTime())) {
            sb.append("START_DT:[\"").append(taskLogSearchVO.getStartTime()).append("\" TO *} AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getEndTime())) {
            sb.append("START_DT:[* TO \"").append(taskLogSearchVO.getEndTime()).append("\"} AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getTdrw())) {
            sb.append("DATA_TYPE:").append(taskLogSearchVO.getTdrw()).append(" AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getKeyWord())) {
            sb.append("(PARAMS:").append(taskLogSearchVO.getKeyWord()).append(" OR ");
            sb.append("TASK_RESULT:").append(taskLogSearchVO.getKeyWord()).append(") AND ");
        }

        // 检索语句
        String searchWord = null;
        if (sb.length() > 0) {
            searchWord = sb.substring(0, sb.length() - 4);
        }

        log.info("查询任务日志列表,入参：" + searchWord);

        // 返回值
        Map<String, Object> data = new HashMap<>();
        try {
            getTrsConn();
            TRSResultSet resultSet = trsConn.executeSelect(tasklogtable, searchWord,
                    (taskLogSearchVO.getCurrent() - 1) * taskLogSearchVO.getSize(), taskLogSearchVO.getSize(), param);
            List<Map<String, Object>> records = new ArrayList<>();
            while (resultSet.moveNext()) {
                TRSRecord tr = resultSet.get();
                Map<String, Object> map = new HashMap<>();
                map.put("objectId", tr.getString("OBJECTID"));
                map.put("dataType", tr.getString("DATA_TYPE"));
                map.put("dataId", tr.getString("DATA_ID"));
                map.put("startDt", tr.getString("START_DT"));
                map.put("finDt", tr.getString("FIN_DT"));
                map.put("status", tr.getString("STATUS"));
                map.put("errorMsg", tr.getString("ERROR_MSG"));
                map.put("params", tr.getString("PARAMS"));
                if (StringUtils.isNotEmpty(tr.getString("START_DT")) && StringUtils.isNotEmpty(tr.getString("FIN_DT"))) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date start = sdf.parse(tr.getString("START_DT"));
                    Date end = sdf.parse(tr.getString("FIN_DT"));
                    long respTime = (end.getTime() - start.getTime()) / 1000;
                    map.put("respTime", respTime);
                }
                map.put("url", tr.getString("URL"));
                map.put("msg", tr.getString("msg"));
                records.add(map);
            }
            data.put("records", records);
            long hitsNum = resultSet.getNumFound();
            data.put("total", (int) hitsNum);
            data.put("size", taskLogSearchVO.getSize());
            data.put("current", taskLogSearchVO.getCurrent());
            data.put("pages", (int) hitsNum % taskLogSearchVO.getSize() == 0 ?
                    (int) hitsNum % taskLogSearchVO.getSize() : (int) hitsNum % taskLogSearchVO.getSize() + 1);
        } catch (TRSException | ParseException e) {
            e.printStackTrace();
        } finally {
            closeTrsConn();
        }
        return Result.success(data, "查询任务日志列表成功");
    }

    /**
     * 查询任务执行结果
     */
    @Override
    public Result queryTaskResult(String objectId) {
        // 检索参数对象
        SearchParams param = new SearchParams();
        // 设置检索结果返回字段列表
        param.setReadColumns("OBJECTID;TASK_RESULT");

        String taskResult = null;
        try {
            getTrsConn();
            TRSResultSet resultSet = trsConn.executeSelect(tasklogtable, "OBJECTID:" + objectId,
                    0, 5, param);
            while (resultSet.moveNext()) {
                TRSRecord tr = resultSet.get();
                taskResult = tr.getString("TASK_RESULT");
                break;
            }
        } catch (TRSException e) {
            e.printStackTrace();
        } finally {
            closeTrsConn();
        }
        return Result.success(taskResult, "查询任务执行结果成功");
    }

    @Override
    public Result queryMonitorWarnInfo(TaskLogSearchVO taskLogSearchVO) {
        // 检索参数对象
        SearchParams param = new SearchParams();
        // 按任务开始时间降序排序
        param.setSortMethod("-START_DT");
        // 设置检索结果返回字段列表
        param.setReadColumns("OBJECTID;DATA_TYPE;DATA_ID;START_DT;FIN_DT;STATUS;ERROR_MSG;PARAMS;URL;msg");

        // 检索条件
        StringBuffer sb = new StringBuffer();
        sb.append("ERROR_MSG:数据源* AND ");
        if (StringUtils.isNotEmpty(taskLogSearchVO.getDataId())) {
            sb.append("DATA_ID:").append(taskLogSearchVO.getDataId()).append(" AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getStatus())) {
            sb.append("STATUS:").append(taskLogSearchVO.getStatus()).append(" AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getStartTime())) {
            sb.append("START_DT:[\"").append(taskLogSearchVO.getStartTime()).append("\" TO *} AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getEndTime())) {
            sb.append("START_DT:[* TO \"").append(taskLogSearchVO.getEndTime()).append("\"} AND ");
        }
        if (StringUtils.isNotEmpty(taskLogSearchVO.getJkmc())) {
            sb.append("msg:").append(taskLogSearchVO.getJkmc()).append(" AND ");
        }

        // 检索语句
        String searchWord = null;
        if (sb.length() > 0) {
            searchWord = sb.substring(0, sb.length() - 4);
        }

        log.info("监测预警信息查询,入参：" + searchWord);

        // 返回值
        Map<String, Object> data = new HashMap<>();
        try {
            getTrsConn();
            TRSResultSet resultSet = trsConn.executeSelect(tasklogtable, searchWord,
                    (taskLogSearchVO.getCurrent() - 1) * taskLogSearchVO.getSize(), taskLogSearchVO.getSize(), param);
            List<Map<String, Object>> records = new ArrayList<>();
            while (resultSet.moveNext()) {
                TRSRecord tr = resultSet.get();
                Map<String, Object> map = new HashMap<>();
                map.put("objectId", tr.getString("OBJECTID"));
                map.put("dataType", tr.getString("DATA_TYPE"));
                map.put("dataId", tr.getString("DATA_ID"));
                map.put("startDt", tr.getString("START_DT"));
                map.put("finDt", tr.getString("FIN_DT"));
                map.put("status", tr.getString("STATUS"));
                map.put("errorMsg", tr.getString("ERROR_MSG"));
                map.put("params", tr.getString("PARAMS"));
                if (StringUtils.isNotEmpty(tr.getString("START_DT")) && StringUtils.isNotEmpty(tr.getString("FIN_DT"))) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                    Date start = sdf.parse(tr.getString("START_DT"));
                    Date end = sdf.parse(tr.getString("FIN_DT"));
                    long respTime = (end.getTime() - start.getTime()) / 1000;
                    map.put("respTime", respTime);
                }
                map.put("url", tr.getString("URL"));
                map.put("msg", tr.getString("msg"));
                records.add(map);
            }
            data.put("records", records);
            long hitsNum = resultSet.getNumFound();
            data.put("total", (int) hitsNum);
            data.put("size", taskLogSearchVO.getSize());
            data.put("current", taskLogSearchVO.getCurrent());
            data.put("pages", (int) hitsNum % taskLogSearchVO.getSize() == 0 ?
                    (int) hitsNum % taskLogSearchVO.getSize() : (int) hitsNum % taskLogSearchVO.getSize() + 1);
        } catch (TRSException | ParseException e) {
            e.printStackTrace();
        } finally {
            closeTrsConn();
        }
        return Result.success(data, "查询任务日志列表成功");
    }

}
