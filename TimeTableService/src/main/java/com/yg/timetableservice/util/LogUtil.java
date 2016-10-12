package com.yg.timetableservice.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class LogUtil {
    static private PublishSubject<Object[]> publishSubjectublishSubject = PublishSubject.create();
    static private Logger debugLogger = LogManager.getLogger("debug_log");
    static {
        publishSubjectublishSubject.onBackpressureBuffer().observeOn(Schedulers.computation()).
         subscribe(os -> {
                     StringBuilder builder = new StringBuilder();
                     for (Object o : os) {builder.append(o);builder.append(":");}
                     debugLogger.trace(builder.toString());
    },
                 debugLogger::error);
    }

    static public void asyncDebug(Object... objects) {
        publishSubjectublishSubject.onNext(objects);
    }
    static public void debug(Object object) {
        debugLogger.trace(object);
    }
    static public void error(Object object) {
        debugLogger.error(object);
    }

}
