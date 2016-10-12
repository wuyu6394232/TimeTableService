package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONArray;
import com.yg.timetableservice.handler.DataHandler;
import rx.functions.Func1;

import java.util.HashMap;
import java.util.Map;

/**
 * get batch turns
 */
public class GetBatchTurnsMapper implements Func1<Map<String, ?>, Map<String, JSONArray>> {
    private long timestamp;
    private int page;
    @Override
    public Map<String, JSONArray> call(Map<String, ?> batchTurns) {
        Map<String, JSONArray> result = new HashMap<>();
        for (String key : batchTurns.keySet()) {
            Object o = batchTurns.get(key);
            if (!(o instanceof JSONArray)) {
                result.put(key, new JSONArray());
                continue;
            }
            JSONArray allTurns = (JSONArray)o;
            String[] data = key.split("#");
            result.put(key, DataHandler.getTurns(allTurns, page, timestamp, data[0], data[1], Integer.parseInt(data[2])));
        }
        return result;
    }
    public GetBatchTurnsMapper(long timestamp, int page) {
        this.timestamp = timestamp;
        this.page = page;
    }
}
