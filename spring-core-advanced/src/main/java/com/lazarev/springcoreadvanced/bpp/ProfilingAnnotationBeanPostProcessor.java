package com.lazarev.springcoreadvanced.bpp;

import com.lazarev.springcoreadvanced.annotation.Profiling;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ProfilingAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> profilingBeans = new ConcurrentHashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if(beanClass.isAnnotationPresent(Profiling.class)){
            profilingBeans.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(profilingBeans.containsKey(beanName)){
            Class<?> beanClass = profilingBeans.get(beanName);
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        long start = System.currentTimeMillis();
                        Object result = method.invoke(bean, args);
                        long end = System.currentTimeMillis();
                        System.out.println(method.getName() + ": " + (end-start));
                        return result;
                    });
        }
        return bean;
    }
}
