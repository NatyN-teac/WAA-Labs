package com.klass.lab01.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Pointcut("@annotation(com.klass.lab01.aspect.annotations.ExecutionTime)")
    public void executionTimeAnnotation(){

    }

    @Around("executionTimeAnnotation()")
    public Object calculateExecutionTime(ProceedingJoinPoint proceed) throws Throwable {
        long start = System.nanoTime();
        var result = proceed.proceed();
        long finish = System.nanoTime();
        System.out.println(proceed.getSignature().getName() + " takes " + TimeUnit.NANOSECONDS.toMillis((finish - start)));
        return result;
    }
}
