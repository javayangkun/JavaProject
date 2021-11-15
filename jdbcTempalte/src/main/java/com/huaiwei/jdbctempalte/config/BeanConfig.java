package com.huaiwei.jdbctempalte.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanConfig implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {
    private BeanFactory beanFactory;
    private String name;
    private String password; //2.set 属性值

    public BeanConfig(){
        System.out.println("1.bean被初始化");
    }



    @Override
    public void setBeanName(String name) {
        System.out.println("3.调用BeanNameAware");
        log.info("BeanNameAware: " + name);
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        //BeanFactory 就是springIOC容器
        BeanConfig bean = (BeanConfig) beanFactory.getBean(name);
        System.out.println("4.调用BeanFactoryAware");
        log.info(bean.toString());
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String applicationName = applicationContext.getApplicationName();
        log.info("applicationName:---" + applicationName);
        BeanConfig bean = (BeanConfig) applicationContext.getBean(name);
        log.info(bean.toString());
        System.out.println("5.调用ApplicationContextAware");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("被销毁了");
    }
}
