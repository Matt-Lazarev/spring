package com.lazarev.springcoreadvanced.cl;

import com.lazarev.springcoreadvanced.annotation.PostProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class PostProxyHandler {
    private final ConfigurableListableBeanFactory factory;

    @EventListener
    public void handlePostProxyAnnotation(ContextRefreshedEvent event) throws Exception{
        ApplicationContext context = event.getApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for(String beanName : beanDefinitionNames){
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            Class<?> originalClassName = Class.forName(beanDefinition.getBeanClassName());
            Method[] methods = originalClassName.getMethods();
            for(Method method : methods){
                if(method.isAnnotationPresent(PostProxy.class)){
                    Object bean = context.getBean(beanName);
                    Method proxyMethod = bean.getClass().getMethod(method.getName());
                    proxyMethod.invoke(bean);
                }
            }
        }
    }
}
