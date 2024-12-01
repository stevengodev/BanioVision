package com.foliaco.bathrooms.infrastructure.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.foliaco.bathrooms.application.service.*.*(..))")
    public void logBeforeServiceMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Executing method: {}", methodName);
    }

    @AfterReturning(pointcut = "execution(* com.foliaco.bathrooms.application.service.*.*(..))", returning = "result")
    public void logAfterServiceMethod(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} executed successfully with result: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "execution(* com.foliaco.bathrooms.application.service.*.*(..))", throwing = "exception")
    public void logAfterThrowingServiceMethod(JoinPoint joinPoint, RuntimeException exception){
        String methodName = joinPoint.getSignature().getName();
        logger.error("Method {} threw an exception: {}", methodName, exception.getMessage(), exception);
    }


}
