package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.struct.HISResult;
import com.yg.timetableservice.struct.ReturnResult;
import com.yg.timetableservice.util.HttpUtil;
import rx.Observable;
import rx.functions.Func1;

import java.util.List;
import java.util.Map;

/**
 * if there are not data in ocs, get old http result
 */
public class AddOldResultBatchFlatMapper implements Func1<ReturnResult, Observable<String>> {
    private String cityId;
    private String stationKeys;
    private int timestamp;
    public AddOldResultBatchFlatMapper(String cityId, String stationKeys, int timestamp) {
        this.cityId = cityId;
        this.stationKeys = stationKeys;
        this.timestamp = timestamp;
    }
    static private String url = "http://10.168.57.177/next_cars_batch?cityId=%s&station_keys=%s" +
            "&timestamp=%d&page=3&suppress_rt=1&suppress_forge=1&dont_crawl=1";
    private boolean checkIsNull(ReturnResult allturns) {
        if (allturns.jsonr.data instanceof Map) {
            Map<String, List<HISResult>> map = (Map)allturns.jsonr.data;
            for (List<HISResult> r : map.values()) {
                if (r.size() > 0)
                    return false;
            }
        }
        return true;
    }
    @Override
    public Observable<String> call(ReturnResult allturns) {
        if (checkIsNull(allturns)) {
            return Observable.just("**YGKJ" + JSONObject.toJSONString(allturns) + "YGKJ##");
        } else {
            String u = String.format(url, cityId, stationKeys, timestamp);
            return HttpUtil.acyncGet(u);
        }
    }
}
