package com.lazarev.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CalculatorAspect {

    @Pointcut("execution(* com.lazarev.springaop.service.Calculator.*(..))")
    public void allCalculatorMethods(){}

    @Before("allCalculatorMethods()")
    public void logBefore(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        System.out.println("Before invoking '" + method.getName() + "' of class '" + clazz + "'");
    }

    @After("allCalculatorMethods()")
    public void logAfter(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        System.out.println("After invoking '" + method.getName() + "' of class '" + clazz + "'");
    }
}
