package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void pointCutMethod() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer() {
    }

    // 두 개의 Point Cut을 동시에 적용
    // 결국, com.example.aop.controller아래의 Package에서 @Timer annotation을 사용한 메소드에 대해서 적용
    @Around("pointCutMethod() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 실제 메소드를 호출
            Object result = joinPoint.proceed();
            stopWatch.stop();
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            System.out.printf("Total Execution Time for Method name: %s is [%f]seconds\n",
                    method.getName(), stopWatch.getTotalTimeSeconds());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
