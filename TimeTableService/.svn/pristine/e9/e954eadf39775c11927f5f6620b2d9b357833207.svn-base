package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.struct.ReturnResult;
import com.yg.timetableservice.util.HttpUtil;
import rx.Observable;
import rx.functions.Func1;

import java.util.ArrayList;

/**
 * get string result to return,if ocs do not have data,get data from old http
 */
public class AddOldResultFlatMapper implements Func1<ReturnResult, Observable<String>> {
    private String cityId;
    private String lineNo;
    private int direction;
    private int stopOrder;
    private int timestamp;
    static private String url = "http://10.168.57.177/next_cars?cityId=%s&lineNo=%s&direction=%d&order=%d&" +
            "timestamp=%d&page=3&suppress_rt=1&suppress_forge=1&dont_crawl=1";

    public AddOldResultFlatMapper(String cityId, String lineNo, int direction, int stopOrder, int timestamp) {
        this.cityId = cityId;
        this.lineNo = lineNo;
        this.direction = direction;
        this.stopOrder = stopOrder;
        this.timestamp = timestamp;
    }

    @Override
    public Observable<String> call(ReturnResult allturns) {
        if (((ArrayList) allturns.jsonr.data).size() > 0) {
            return Observable.just("**YGKJ" + JSONObject.toJSONString(allturns) + "YGKJ##");
        } else {
            String u = String.format(url, cityId, lineNo, direction, stopOrder, timestamp);
            return HttpUtil.acyncGet(u);
        }
    }
}
