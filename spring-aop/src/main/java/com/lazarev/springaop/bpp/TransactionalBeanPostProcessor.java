package com.lazarev.springaop.bpp;

import com.lazarev.springaop.annotation.Transactional;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
//@Component
public class TransactionalBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> beans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getDeclaredMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Transactional.class)){
                beans.put(beanName, beanClass);
                break;
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!beans.containsKey(beanName)){
            return bean;
        }

        Class<?> beanClass = beans.get(beanName);
        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        boolean needTransaction = beanClass.getMethod(method.getName())
                                                 .isAnnotationPresent(Transactional.class);
                        if(needTransaction){
                            System.out.println("--start transaction--");
                            Object result = method.invoke(bean, args);
                            System.out.println("--commit transaction--");
                            return result;
                        }
                        return method.invoke(bean, args);
                    }
                }
        );
    }
}
