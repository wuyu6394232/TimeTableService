package com.yg.timetableservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class TestController {
    static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    @ResponseBody
    @RequestMapping("/greeting")
    public DeferredResult<String> greeting(@RequestParam(value="name", defaultValue="World") final String name) {
        final DeferredResult<String> deferredResult = new DeferredResult<String>();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    deferredResult.setResult(name);
                } catch (InterruptedException e) {

                }
            }
        }.start();
        return deferredResult;
    }
    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        logger.info("hello");
        return "123";
    }
}
