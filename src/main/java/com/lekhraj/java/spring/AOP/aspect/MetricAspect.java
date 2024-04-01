package com.lekhraj.java.spring.AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 1. Aspect
@Aspect
@Component
public class MetricAspect {

    //2. pointcut@Around("execution(* com.lekhraj.java.spring.AOP.*.*(..))")
    @Around("@annotation(com.lekhraj.java.spring.AOP.annotation.MyAopMetric)")
    //4. logPerformance method/code - Advise
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(" --- METHOD  ---"+ joinPoint.getSignature().getName() + "--- START---");
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 3. jointPoint - encountered method.
        long endTime = System.currentTimeMillis();
        System.out.println(" time took :" + (endTime - startTime) + " milliseconds");
        System.out.println(" --- METHOD  ---"+ joinPoint.getSignature().getName() + "--- END---");
        return result;
    }
}

