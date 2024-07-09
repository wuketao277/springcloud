package com.hello.orderservice.filter;

import com.google.common.base.Strings;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * TraceId过滤器，给线程上下文添加TraceId
 */
@Component
public class TraceIdFilter implements Filter {
    // 请求头/响应头中traceId的key值
    private final String Head_TraceId_Key = "TraceId";
    // 现场上下文中traceId的key值
    private final String Thread_TraceId_Key = "traceId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        dealWithTraceId(servletRequest, servletResponse);
        // 继续后续调用
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 处理traceId
     */
    private void dealWithTraceId(ServletRequest servletRequest, ServletResponse servletResponse) {
        long id = Thread.currentThread().getId();
        // 优先从请求head中获取，如果head中没有，就自动生成一个存入到线程上下文中.
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String traceId = httpServletRequest.getHeader(Head_TraceId_Key);
        if (Strings.isNullOrEmpty(traceId)) {
            traceId = Thread_TraceId_Key + "-" + UUID.randomUUID().toString();
        }
        // 线程上下文中设置traceId
        ThreadContext.put(Thread_TraceId_Key, traceId);
        // 返回结果中设置traceId，方便调用方查看
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader(Head_TraceId_Key, traceId);
    }
}
