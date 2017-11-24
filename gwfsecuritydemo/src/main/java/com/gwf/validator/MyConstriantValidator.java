package com.gwf.validator;

import com.gwf.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by gaowenfeng on 2017/9/10.
 */
@Slf4j
public class MyConstriantValidator implements ConstraintValidator<MyConstraint,Object> {
    @Autowired
    private HelloService helloService;

    /**
     * 初始化
     * @param myConstraint
     */
    @Override
    public void initialize(MyConstraint myConstraint) {
        log.info("my validator init");
    }

    /**
     * 校验逻辑
     * @param o
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String value = helloService.sayHellp(o.toString());
        log.info(value);
        return false;
    }
}
