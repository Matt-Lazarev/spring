package com.lazarev.springcoreadvanced.bpp;

import com.lazarev.springcoreadvanced.annotation.InjectRandomInt;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.security.SecureRandom;

@Component
public class InjectRandomIntAnnotationBeanPostProcessor  implements BeanPostProcessor {
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Field[] declaredFields = beanClass.getDeclaredFields();
        for(Field field : declaredFields){
            if(field.isAnnotationPresent(InjectRandomInt.class)){
                InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
                int min = annotation.min();
                int max = annotation.max();
                int value = RANDOM.nextInt(min, max);

                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, value);
            }
        }
        return bean; //non-modified
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean; //can be modified
    }
}
