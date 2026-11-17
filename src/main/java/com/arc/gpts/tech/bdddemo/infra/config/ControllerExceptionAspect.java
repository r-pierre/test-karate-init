package com.arc.gpts.tech.bdddemo.infra.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
@Slf4j
@Aspect
@Component
public class ControllerExceptionAspect {

    private static final Duration SLOW_REPOSITORY_THRESHOLD = Duration.ofSeconds(10);

    @AfterThrowing(
            pointcut = "within(com.arc.gpts.tech.bdddemo..*Controller) && execution(public * *(..))",
            throwing = "ex")
    public void ControllerOnError(JoinPoint joinPoint, Throwable ex) throws Throwable {
        logError("A controller call has failed: ", joinPoint, ex);
        throw ex;
    }

    @Around("execution(* com.arc.gpts.tech.bdddemo..repository..*+.*(..))")
    public Object logSlowRepositoryCalls(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            return joinPoint.proceed();
        }  catch (Throwable ex) {
            logError("A repository call fails: ", joinPoint, ex);
            throw ex;
        } finally {
            Duration elapsed = Duration.ofNanos(System.nanoTime() - start);
            logOnlySlowCalls(joinPoint, elapsed);
        }
    }

    private void logOnlySlowCalls(ProceedingJoinPoint joinPoint, Duration elapsed) {
        if (elapsed.compareTo(SLOW_REPOSITORY_THRESHOLD) > 0) {
            log.warn("Slow repository call: {} took {} ms with args {}",
                    joinPoint.getSignature().toShortString(),
                    elapsed.toMillis(),
                    Arrays.toString(joinPoint.getArgs()));
        }
    }

    private static void logError(String message, JoinPoint joinPoint, Throwable ex) {
        log.error("{} failed with args {}: {} - {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs()),
                ex.getClass().getSimpleName(),
                ex.toString());
    }


}
