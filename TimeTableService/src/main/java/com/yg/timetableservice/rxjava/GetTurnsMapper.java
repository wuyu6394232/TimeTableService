package com.yg.timetableservice.rxjava;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.handler.DataHandler;
import com.yg.timetableservice.struct.PositionTurn;
import com.yg.timetableservice.struct.Turn;
import com.yg.timetableservice.util.LogUtil;
import com.yg.timetableservice.util.TimeUtil;
import rx.functions.Func1;

import java.util.Calendar;
import java.util.Date;

public class GetTurnsMapper implements Func1<Object, JSONArray> {
    private long timestamp;
    private int page;
    private String cityId;
    private String lineNo;
    private int direction;
    @Override
    public JSONArray call(Object o) {
        if (!(o instanceof JSONArray)) {
            LogUtil.asyncDebug("value not json!");
            return new JSONArray();
        }

        JSONArray allTurns = (JSONArray) o;
        LogUtil.asyncDebug(new Object[]{"ocs all turns:", allTurns});
        JSONArray vaildTurns = DataHandler.getTurns(allTurns, page, timestamp, cityId, lineNo, direction);
        LogUtil.asyncDebug(new Object[]{"get turns output:", vaildTurns});
        return vaildTurns;
    }

    public GetTurnsMapper(long timestamp, int page, String cityId, String lineNo, int direction) {
        this.timestamp = timestamp;
        this.page = page;
        this.cityId = cityId;
        this.lineNo = lineNo;
        this.direction = direction;
    }


}
