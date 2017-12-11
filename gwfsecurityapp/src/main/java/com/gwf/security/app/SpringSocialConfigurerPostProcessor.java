package com.gwf.security.app;

import com.gwf.security.core.social.GwfSpringSocialConfigurer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by gaowenfeng on 2017/12/10.
 */
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor{


    /**
     * 所有的bean初始化之前都要经过的方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 所有的bean初始化之后要经过的方法
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(StringUtils.equals(beanName,"gwfSocialConfigurer")){
            GwfSpringSocialConfigurer configurer = (GwfSpringSocialConfigurer) bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
