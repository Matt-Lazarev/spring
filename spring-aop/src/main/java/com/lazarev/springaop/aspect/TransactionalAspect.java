package com.lazarev.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionalAspect {

    @Pointcut("@annotation(com.lazarev.springaop.annotation.Transactional)")
    public void transactionalMethods() {}

    @Around("transactionalMethods()")
    public Object executeInTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("--start transaction--");
        Object methodResult = joinPoint.proceed();
        System.out.println("--commit transaction--");

        return methodResult;
    }
}
