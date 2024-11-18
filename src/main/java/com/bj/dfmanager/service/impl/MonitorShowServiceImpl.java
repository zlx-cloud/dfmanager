package com.bj.dfmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bj.dfmanager.entity.Model;
import com.bj.dfmanager.entity.Target;
import com.bj.dfmanager.mapper.ModelMapper;
import com.bj.dfmanager.mapper.ModelResultMapper;
import com.bj.dfmanager.mapper.MonitorDiskMapper;
import com.bj.dfmanager.mapper.TargetMapper;
import com.bj.dfmanager.service.MonitorShowService;
import com.bj.dfmanager.vo.common.Result;
import com.bj.dfmanager.vo.monitor.MonitorDiskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MonitorShowServiceImpl implements MonitorShowService {

    @Resource
    private TargetMapper targetMapper;
    @Resource
    private ModelMapper modelMapper;
    @Resource
    private ModelResultMapper modelResultMapper;
    @Resource
    private MonitorDiskMapper monitorDiskMapper;

    @Override
    public Result startTargetCount() {
        int count = targetMapper.queryTargetCountByStatus("Y");
        return Result.success(count, "查询启用指标数量成功");
    }

    @Override
    public Result startTargetList() {
        LambdaQueryWrapper<Target> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Target::getTargetStatus, "Y");
        queryWrapper.orderByDesc(Target::getCreateTime);
        List<Target> list = targetMapper.selectList(queryWrapper);
        return Result.success(list, "查询启用指标列表成功");
    }

    @Override
    public Result stopTargetCount() {
        int count = targetMapper.queryTargetCountByStatus("N");
        return Result.success(count, "查询停用指标数量成功");
    }

    @Override
    public Result stopTargetList() {
        LambdaQueryWrapper<Target> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Target::getTargetStatus, "N");
        queryWrapper.orderByDesc(Target::getCreateTime);
        List<Target> list = targetMapper.selectList(queryWrapper);
        return Result.success(list, "查询停用指标列表成功");
    }

    @Override
    public Result todayServiceCount() {
        int count = modelResultMapper.todayServiceCount();
        return Result.success(count, "查询当日服务次数成功");
    }

    @Override
    public Result modelList() {
        LambdaQueryWrapper<Model> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Model::getCreateTime);
        List<Model> list = modelMapper.selectList(queryWrapper);
        return Result.success(list, "查询模型列表成功");
    }

    @Override
    public Result modelTodayServiceCount(String id) {
        int count = modelResultMapper.modelTodayServiceCount(id);
        return Result.success(count, "查询特定模型当日服务次数成功");
    }

    @Override
    public Result modelCountTrend(String id) {
        List<Map<String, Integer>> list = modelResultMapper.modelCountTrend(id);
        return Result.success(list, "查询服务次数趋势成功");
    }

    @Override
    public Result modelTimeTrend(String id) {
        // 结果
        HashMap result = new HashMap();

        // 图例
        List<String> legendData = new ArrayList<>();
        // 横坐标
        List<String> xAxisData = new ArrayList<>();
        // 模型id-名称键值对
        Map<String, String> modelMap = new HashMap();
        // 存放 小时+模型id-响应时长 键值对
        Map<String, BigDecimal> dataMap = new HashMap();
        // 存放图数据
        List<Map<String, Object>> serieMap = new ArrayList<>();

        // 查询业务数据
        List<Map<String, Object>> list = modelResultMapper.modelTimeTrend(id);

        // 遍历业务数据,组装需要的数据
        for (Map<String, Object> option : list) {
            // 获取小时
            xAxisData.add((String) option.get("HOUR"));

            // 存放模型id-名称
            modelMap.put((String) option.get("MODEL_ID"), (String) option.get("MODEL_NAME"));

            // 存放 小时+模型id-响应时长
            dataMap.put((String) option.get("KEY"), (BigDecimal) option.get("VALUE"));
        }

        // 小时
        List<String> hourList = xAxisData.stream().distinct().collect(Collectors.toList());

        // 遍历模型
        for (String modelId : modelMap.keySet()) {
            legendData.add(modelMap.get(modelId));

            Map modelHourMap = new HashMap();
            List<BigDecimal> modelTimeList = new ArrayList<>();
            // 拼接 小时+模型id key
            for (String hour : hourList) {
                String key = hour + modelId;
                Object value = dataMap.get(key);
                if (null != value) {
                    modelTimeList.add((BigDecimal) value);
                } else {
                    modelTimeList.add(new BigDecimal(0));
                }
            }
            modelHourMap.put(modelMap.get(modelId), modelTimeList);
            serieMap.add(modelHourMap);
        }

        result.put("legendData", legendData);
        result.put("xAxisData", hourList);
        result.put("seriesData", serieMap);

        return Result.success(result, "查询响应时长趋势成功");
    }

    @Override
    public Result modelResultRate() {
        // 查询正确数量
        int successCount = modelResultMapper.queryCountByErrorFlag("Y");
        // 查询错误数量
        int failCount = modelResultMapper.queryCountByErrorFlag("N");

        Map map = new HashMap();
        map.put("successCount", successCount);
        map.put("failCount", failCount);

        return Result.success(map, "监控接口的正确率和错误率成功");
    }

    @Override
    public Result diskMonitor() {
        List<Map<String, Object>> list = modelResultMapper.diskMonitor();
        return Result.success(list, "查询磁盘监控成功");
    }

    @Override
    public Result updateDiskMonitor(MonitorDiskVO vo) {
        monitorDiskMapper.updateDiskMonitor(vo.getIp(), vo.getTotal(), vo.getUsed(), vo.getFree());
        return null;
    }

}