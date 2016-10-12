package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.handler.DataHandler;
import com.yg.timetableservice.struct.PositionTurn;
import com.yg.timetableservice.util.LogUtil;
import com.yg.timetableservice.util.OCSUtil;
import com.yg.timetableservice.util.TimeUtil;
import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

import java.util.*;
public class GetPositionFlatMapper implements Func1<JSONArray, Observable<JSONArray>> {
    private class Zipper implements Func2<Map<String,?>, JSONArray, JSONArray> {
        @Override
        public JSONArray call(Map<String, ?> positionMap, JSONArray turns) {
            return DataHandler.zipTurnAndPos(positionMap, turns, timestamp);
        }
    }

    private OCSUtil ocsUtil;
    private long timestamp;
    public GetPositionFlatMapper(long timestamp, OCSUtil ocsUtil) {
        this.timestamp = timestamp;
        this.ocsUtil = ocsUtil;
    }
    @Override
    public Observable<JSONArray> call(JSONArray allturns) {
        List<String> keys = new ArrayList<>();
        for (Object o : allturns) {
            PositionTurn turn = (PositionTurn)o;
            keys.add(turn.getTurnId());
        }

        Observable<Map<String, ?>> ocsObservable = ocsUtil.asyncGetBulkData(keys).observeOn(Schedulers.computation());
        Observable<JSONArray> JSONArrayObservable = Observable.just(allturns);
        return Observable.zip(ocsObservable, JSONArrayObservable, new Zipper());
    }

}
