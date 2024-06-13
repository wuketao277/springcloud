package com.hello.orderservice.aspect;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wuketao
 * @date 2024/6/12
 * @Description
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.hello.orderservice.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        if (!ThreadContext.containsKey("traceId")) {
            ThreadContext.put("traceId", UUID.randomUUID().toString());
        }
        // 获得类名称
        String className = pjp.getTarget().getClass().getSimpleName();
        // 获得方法名称
        String methodName = pjp.getSignature().getName();
        // 获取入参
        Object[] args = pjp.getArgs();
        StringBuilder sBuilder = new StringBuilder();
        for (Object object : args) {
            sBuilder.append(JSONObject.toJSONString(object));
        }
        log.info("方法名：{} 入参：{}", className + "." + methodName, sBuilder.toString());
        // 执行目标方法，并获得返回值。
        Object retVal = pjp.proceed();
        // 打印方法调用结果
        log.info("方法名：{} 返回值：{}", className + "." + methodName, JSONObject.toJSONString(retVal));
        return retVal;
    }
}
