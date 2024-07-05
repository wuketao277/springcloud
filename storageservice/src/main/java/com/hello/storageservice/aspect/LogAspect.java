package com.hello.storageservice.aspect;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @Pointcut("execution(* com.hello.storageservice.controller.*.*(..))")
    public void controllerPointcut() {
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        dealWithTraceId();
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

    /**
     * 处理traceId
     */
    private void dealWithTraceId() {
        // 首先判断线程环境中是否已经包含“traceId”，如果不存在才进行添加。
        if (!ThreadContext.containsKey("traceId")) {
            // 优先从请求head中获取，如果head中没有，就自动生成一个.
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String traceId = attributes.getRequest().getHeader("TraceId");
            ThreadContext.put("traceId", Strings.isNullOrEmpty(traceId) ? "traceId-" + UUID.randomUUID().toString() : traceId);
        }
    }
}
