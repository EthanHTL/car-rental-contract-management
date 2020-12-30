package com.example.carrentalcontract.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2020-12-30 17:16
 **/
@Component
public class ObjectContainer implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public ObjectContainer() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ObjectContainer.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getObject(String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getObject(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getObject(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
