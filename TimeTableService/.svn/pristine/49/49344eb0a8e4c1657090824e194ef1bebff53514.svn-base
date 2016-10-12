package com.yg.timetableservice.rxjava;

import com.alibaba.fastjson.JSONObject;
import com.yg.timetableservice.struct.ReturnResult;
import com.yg.timetableservice.util.LogUtil;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Subscriber;


public class ReturnResultSubscriber extends Subscriber<String> {
    private DeferredResult<String> deferredResult;
    long requestBeginTimeStamp;
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable throwable) {
        ReturnResult errorResult = ReturnResult.getErrorResult(throwable.toString());
        deferredResult.setResult("**YGKJ" + JSONObject.toJSONString(errorResult) + "YGKJ##");
    }

    @Override
    public void onNext(String res) {
        if (res != null) {
            LogUtil.asyncDebug("request total cost:" + (System.currentTimeMillis() - requestBeginTimeStamp));
            deferredResult.setResult(res);
        } else {
            LogUtil.asyncDebug("request total cost:" + (System.currentTimeMillis() - requestBeginTimeStamp));
            deferredResult.setResult("**YGKJ" + JSONObject.toJSONString(ReturnResult.getNullResult()) + "YGKJ##");
        }
    }
    public ReturnResultSubscriber(DeferredResult<String> deferredResult, long requestBeginTimeStamp) {
        this.deferredResult = deferredResult;
        this.requestBeginTimeStamp = requestBeginTimeStamp;
    }

}
