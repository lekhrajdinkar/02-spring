package com.lekhraj.java.spring.AOP.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 1. Aspect
@Aspect
@Component
public class MetricAspect {
    @Around("execution(* com.lekhraj.java.spring.AOP.*.*(..))") //2. pointcut
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable { // 4. logPerformance method/code - Advise
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed(); // 3. jointPoint - encountered method.
        long endTime = System.currentTimeMillis();
        System.out.println(" AOP == Method :: " + joinPoint.getSignature().getName() + " took " + (endTime - startTime) + " milliseconds");
        return result;
    }
}

