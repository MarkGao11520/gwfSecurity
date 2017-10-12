package com.gwf.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Created by gaowenfeng on 2017/10/9.
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order1")
    public Callable<String> order1() throws InterruptedException {
        log.info("主线程开始");
        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");
                Thread.sleep(1000);
                log.info("副线程返回");
                return "success";
            }
        };  //单开一个线程，在Spring管理的线程里做业务逻辑的处理

        log.info("主线程返回");
        return result;
    }


    @RequestMapping("/order2")
    public DeferredResult<String> order2() throws InterruptedException {
        log.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);  //生成8位订单号

        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber,result);

        log.info("主线程返回");
        return result;
    }
}
