package com.yg.timetableservice.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.util.EntityUtils;
import rx.Observable;

import java.io.IOException;


public class HttpUtil {
    private static CloseableHttpAsyncClient httpclient = HttpAsyncClientBuilder.create()
            .setMaxConnTotal(1000).setMaxConnPerRoute(1000).build();

    static {
        httpclient.start();
    }
    public static void stop() {
        try {
            httpclient.close();
        } catch (IOException e) {
        }
    }
    public static Observable<String> acyncGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        return Observable.create(subscriber -> {
            long httpReqTimeStamp = System.currentTimeMillis();
            httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
                @Override
                public void completed(final HttpResponse response) {
                    LogUtil.asyncDebug("http response time:" + (System.currentTimeMillis() - httpReqTimeStamp));
                    HttpEntity entity = response.getEntity();
                    try {
                        if (entity != null) {
                            String result = EntityUtils.toString(entity);
                            LogUtil.asyncDebug("old http request:" + url + "\nresult:" + result);
                            subscriber.onNext(result);
                        } else {
                            subscriber.onNext(null);
                        }
                    } catch (Exception e) {
                        subscriber.onNext(null);
                    } finally {
                        try {
                            EntityUtils.consume(entity);
                        } catch (IOException e) {
                            LogUtil.error("call timepredict exception:\n" + ExceptionPrinter.getStackInfo(e));
                        }
                    }
                }

                @Override
                public void failed(final Exception ex) {
                    subscriber.onNext(null);
                }

                @Override
                public void cancelled() {
                    subscriber.onNext(null);
                }
            });
        });
    }
}
