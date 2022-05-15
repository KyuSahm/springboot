package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void pointCutMethod() {

    }

    //@Before("pointCutMethod()")
    public void before(JoinPoint joinPoint) {
        System.out.println("#### Before calling method ####");
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Method name: " + method.getName());
        Object[] args = joinPoint.getArgs();
        for (Object obj: args) {
            System.out.printf("argument type: %s, value: %s\n", obj.getClass().getSimpleName(), obj);
        }
    }

    //@AfterReturning(value = "pointCutMethod()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("####After returning method ####");
        System.out.println("return object: " + returnObj);
    }
}
