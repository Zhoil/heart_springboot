package com.example.mind.Aspect;

import com.example.mind.annotation.LogAnnotation;
import jdk.jshell.MethodSnippet;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect // 标注为切面
@Component // 将切面注册为Spring Bean
public class LoggingAspect {
    // 定义切点，匹配所有service包下的类的所有方法
    @Pointcut("@annotation(com.example.mind.annotation.LogAnnotation)")
    public void logPointCut() {}

    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        if(annotation != null) {
            String value = annotation.value();
            System.out.println("【系统日志】当前操作: " + value + ", 调用了：" + methodName + "方法, 返回值是: " + joinPoint.proceed());
        }
        return joinPoint.proceed();
    }
}
