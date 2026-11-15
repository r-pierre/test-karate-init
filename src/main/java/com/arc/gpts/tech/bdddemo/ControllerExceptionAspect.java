package com.arc.gpts.tech.bdddemo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// Called whenever a public method of a *Controller class of the application throws.
// The exception keeps propagating afterwards, so GlobalExceptionHandler still builds the HTTP response.
// Limited to the application package so that Spring's own controllers (e.g. BasicErrorController) are not matched.
@Slf4j
@Aspect
@Component
public class ControllerExceptionAspect {

    @AfterThrowing(
            pointcut = "within(com.arc.gpts.tech.bdddemo..*Controller) && execution(public * *(..))",
            throwing = "ex")
    public void onException(JoinPoint joinPoint, Throwable ex) {
        log.error("{} failed with args {}: {} - {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()),
                ex.getClass().getSimpleName(),
                ex.toString());
    }

}
