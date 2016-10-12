package com.yg.timetableservice.struct;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * HSTimeTable result
 */
public class HISResult {
    @JSONField(name = "best_arrival_time")
    public String bestArrivalTime;
    @JSONField(name = "min_arrival_time")
    public String minArrivalTime;
    @JSONField(name = "max_arrival_time")
    public String maxArrivalTime;
    @JSONField(name = "is_history")
    public int isHistory = 1;
    @JSONField(name = "next_day_flag")
    public int nextDayFlag;
    @JSONField(name = "accuracy")
    public double accuracy;
    @JSONField(name = "data_source")
    public String dataSource;
    @JSONField(name = "next_stop_order")
    public int nextStopOrder;
    public HISResult(String bestArrivalTime, String minArrivalTime, String maxArrivalTime,
                      int nextDayFlag, double accuracy, int nextStopOrder) {
        this.bestArrivalTime = bestArrivalTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.nextDayFlag = nextDayFlag;
        this.accuracy = accuracy;
        this.nextStopOrder = nextStopOrder;
    }
    public HISResult(){}
}
