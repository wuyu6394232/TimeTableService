package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.struct.HISResult;
import com.yg.timetableservice.struct.PositionTurn;
import com.yg.timetableservice.struct.ReturnResult;
import com.yg.timetableservice.util.LogUtil;
import com.yg.timetableservice.util.TimeUtil;
import rx.functions.Func1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GetBatchResultMapper implements Func1<Map<String, JSONArray>, ReturnResult> {
    @Override
    public ReturnResult call(Map<String, JSONArray> allturnsMap) {
        Map<String, List<HISResult>> resultData = new HashMap<>();
        for (String key : allturnsMap.keySet()) {
            List<HISResult> turns = new ArrayList<>();
            JSONArray allturns = allturnsMap.get(key);
            for (Object o : allturns) {
                String arriveTime = ((PositionTurn)o).turn.arriveTime;
                TimeUtil timeUtil = new TimeUtil(arriveTime);
                String dateStr = timeUtil.getHour() + ":" + timeUtil.getMinute();
                HISResult hisResult = new HISResult();
                hisResult.maxArrivalTime = hisResult.minArrivalTime = hisResult.bestArrivalTime = dateStr;
                hisResult.accuracy = ((PositionTurn)o).turn.accuracy;
                int type = ((PositionTurn)o).turn.dataSource;
                switch (type) {
                    case 0:
                        hisResult.dataSource = "history";
                        break;
                    case 1:
                        hisResult.dataSource = "forge";
                        break;
                    case 2:
                        hisResult.dataSource = "history";
                    default:
                        hisResult.dataSource = "forge";
                }
                if (timeUtil.ifNextDay()) {
                    hisResult.nextDayFlag = 1;
                } else {
                    hisResult.nextDayFlag = ((PositionTurn)o).nextDayFlag;
                }
                hisResult.nextStopOrder = ((PositionTurn)o).nextStopOrder;
                turns.add(hisResult);
            }
            String[] data = key.split("#");
            String newKey = data[1] + "," + data[2] + "," + data[3];
            resultData.put(newKey, turns);
        }
        ReturnResult returnResult = new ReturnResult();
        ReturnResult.HttpReturnResult httpReturnResult = new ReturnResult.HttpReturnResult();
        httpReturnResult.data = resultData;
        returnResult.jsonr = httpReturnResult;
        LogUtil.asyncDebug(new Object[]{"result", JSONObject.toJSONString(returnResult)});
        return returnResult;
    }
}
