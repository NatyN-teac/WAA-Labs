package com.klass.lab01.aspect;

import com.klass.lab01.domain.Exception;
import com.klass.lab01.repository.ExceptionRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
@AllArgsConstructor
public class ExceptionAspect {
    @Autowired
    private final ExceptionRepository exceptionRepository;

//    @AfterThrowing(pointcut = "execution(com.klass.lab01.controllers.userController.*)",throwing = "exception")
@AfterThrowing(pointcut = "execution(* com.klass.lab01.services.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint,Exception exception) {
        String transactionId = TransactionSynchronizationManager.getCurrentTransactionName();
        var today = new Date();
        String principle = "Jonnatan Hasrovick";
        String operation = joinPoint.getSignature().toShortString();
        String exceptionType = exception.getClass().getName();
        Exception exceptionEntity = new Exception();
        exceptionEntity.setTransactionId(transactionId);
        exceptionEntity.setExceptionType(exceptionType);
        exceptionEntity.setDate(today);
        exceptionEntity.setTime(String.valueOf(today.getTime()));
        exceptionEntity.setPrinciple(principle);
        exceptionEntity.setOperation(operation);
        exceptionRepository.save(exceptionEntity);
    }
}
