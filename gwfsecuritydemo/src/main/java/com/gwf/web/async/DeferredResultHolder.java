package com.gwf.web.async;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/10/10.
 */
@Component
@Data
public class DeferredResultHolder {

    //key:订单号  value:订单的处理结果
    private Map<String,DeferredResult<String>> map = new HashMap<>();
}
