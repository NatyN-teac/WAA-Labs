package com.klass.lab01.aspect;

import com.klass.lab01.domain.Logger;
import com.klass.lab01.domain.User;
import com.klass.lab01.repository.LoggerRepository;
import com.klass.lab01.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Aspect
@Component
@AllArgsConstructor
public class LoggerAspect {
    @Autowired
    private final LoggerRepository loggerRepository;
    @Pointcut("within(com.klass.lab01.controllers..*)")
    public void logControllerMethod(){

    }

    @Before("logControllerMethod()")
    public void logRequest(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getDeclaringTypeName());
        String transactionId = TransactionSynchronizationManager.getCurrentTransactionName();
        var today = new Date();
        Logger logEntity = new Logger();
        logEntity.setTransationId(transactionId);
        logEntity.setDate(today);
        logEntity.setTime( new Time(today.getTime()));
        logEntity.setPrincipal("Jonnah");
        logEntity.setOperation(joinPoint.getSignature().toString());
        loggerRepository.save(logEntity);
    }
}
