package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Base64;

@Aspect
@Component
public class DecoderAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void pointCutMethod() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Decoder)")
    private void enableDecoder() {
    }

    // 두 개의 Point Cut을 동시에 적용
    // 결국, com.example.aop.controller아래의 Package에서 @Decoder annotation을 사용한 메소드에 대해서 적용
    @Before("pointCutMethod() && enableDecoder()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg: args) {
            if (arg instanceof User) {
                // 객체 형변환
                User user = User.class.cast(arg);
                try {
                    System.out.println("Before Decoding: " + user.getEmail());
                    String email = new String(Base64.getDecoder().decode(user.getEmail()), "UTF-8");
                    user.setEmail(email);
                    System.out.println("After Decoding: " + user.getEmail());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 두 개의 Point Cut을 동시에 적용
    // 결국, com.example.aop.controller아래의 Package에서 @Decoder annotation을 사용한 메소드에 대해서 적용
    @AfterReturning(value = "pointCutMethod() && enableDecoder()", returning = "returnObj")
    public void afterReturning(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof User) {
            User user = User.class.cast(returnObj);
            System.out.println("Before Encoding: " + user.getEmail());
            String base64Email = Base64.getEncoder().encodeToString(user.getEmail().getBytes());
            user.setEmail(base64Email);
            System.out.println("After Encoding: " + user.getEmail());
        }
    }
}
