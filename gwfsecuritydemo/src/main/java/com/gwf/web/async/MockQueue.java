package com.gwf.web.async;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/10/10.
 * 虚拟队列
 */
@Data
@Slf4j
@Component
public class MockQueue {
    private String placeOrder;   //下单消息
    private String completeOrder;  //订单完成的消息

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(()->{
            log.info("接单下单请求");
            try {
                Thread.sleep(1000);  //模拟应用2处理下单的过程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;  //给completeOrder设值代表下单请求处理完毕
            log.info("下单请求处理完毕:{}",placeOrder);
        }).start();

    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
