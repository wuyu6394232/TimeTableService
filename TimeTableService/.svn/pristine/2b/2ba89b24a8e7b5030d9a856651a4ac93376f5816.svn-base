package com.yg.timetableservice.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.struct.PositionTurn;
import com.yg.timetableservice.struct.Turn;
import com.yg.timetableservice.util.LogUtil;
import com.yg.timetableservice.util.TimeUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class DataHandler {
    public static JSONArray getTurns(JSONArray allTurns, int page, long timestamp, String cityId, String lineNo, int direction) {
        JSONArray vaildTurns = new JSONArray();
        Calendar queryCalendar = Calendar.getInstance();
        queryCalendar.setTime(new Date(timestamp * 1000));
        TimeUtil queryTime = new TimeUtil(queryCalendar);
        int currentCount = 0;
        for (Object ob : allTurns) {
            if (ob instanceof JSONObject) {
                Turn turn = JSONObject.toJavaObject((JSONObject) ob, Turn.class);
                TimeUtil arriveTime = new TimeUtil(turn.arriveTime);
                if (arriveTime.compareTo(queryTime) == 1) {
                    PositionTurn positionTurn = new PositionTurn();
                    positionTurn.turn = turn;
                    positionTurn.nextDayFlag = 0;
                    positionTurn.cityId = cityId;
                    positionTurn.lineNo = lineNo;
                    positionTurn.direction = direction;
                    if (currentCount < page) {
                        vaildTurns.add(positionTurn);
                        currentCount ++;
                    }



                }
            }
        }
        //if not contains enough turns, find from next day

        for (int i = 0; i < page - currentCount && i < allTurns.size() - currentCount; i++) {
            JSONObject ob = allTurns.getJSONObject(i);
            PositionTurn positionTurn = new PositionTurn();
            positionTurn.turn = JSONObject.toJavaObject(ob, Turn.class);
            positionTurn.nextDayFlag = 1;
            positionTurn.cityId = cityId;
            positionTurn.lineNo = lineNo;
            positionTurn.direction = direction;
            vaildTurns.add(positionTurn);
        }
        LogUtil.asyncDebug(new Object[]{"get turns output:", vaildTurns});
        return vaildTurns;
    }

    public static JSONArray zipTurnAndPos(Map<String, ?> positionMap, JSONArray turns, long timestamp) {
        for (Object o : turns) {
            PositionTurn positionTurn = (PositionTurn)o;
            String turnId = positionTurn.getTurnId();
            JSONObject busPosition = (JSONObject)positionMap.get(turnId);
            boolean findPos = false;
            if (busPosition != null) {
                //find busPosition
                int i = 1;
                for (; i <= busPosition.keySet().size(); i++) {
                    String timeStr = busPosition.getString(String.valueOf(i));
                    TimeUtil arriveTime = new TimeUtil(timeStr);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date(timestamp * 1000));
                    TimeUtil queryTime = new TimeUtil(calendar);
                    if(arriveTime.compareTo(queryTime) == 1) {
                        findPos = true;
                        break;
                    }
                }
                if (!findPos || positionTurn.nextDayFlag == 1)
                    positionTurn.nextStopOrder = 1;
                else
                    positionTurn.nextStopOrder = i;
            }
        }
        LogUtil.asyncDebug(new Object[]{"get position:", turns});
        return turns;
    }

}
