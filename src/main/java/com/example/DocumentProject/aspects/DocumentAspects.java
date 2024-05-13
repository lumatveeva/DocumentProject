package com.example.DocumentProject.aspects;
import com.example.DocumentProject.exceptions.ExecutionRestMethodException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component

public class DocumentAspects {
    private static final Logger log = LoggerFactory.getLogger(DocumentAspects.class);

    @Pointcut("@annotation(com.example.DocumentProject.annotations.LoggingAspect)")
    public void isAnnotatedMethod(){}



    @Around("isAnnotatedMethod()")
    public void aroundAnnotatedMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object resultMethod = proceedingJoinPoint.getSignature();
        log.info("Начало метода: " + resultMethod);
        try {
            proceedingJoinPoint.proceed();
            log.info("Успешное выполнение метода: " + resultMethod);
        } catch (Throwable e) {
            log.error("Оштбка выполнения метода: " + resultMethod);
            throw new ExecutionRestMethodException("Ошибка при выполнении метода ", e);
        }
    }
}
