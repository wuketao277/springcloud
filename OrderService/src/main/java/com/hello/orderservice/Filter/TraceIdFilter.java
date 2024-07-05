package com.hello.orderservice.Filter;

import com.google.common.base.Strings;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

/**
 * TraceId过滤器，给线程上下文添加TraceId
 */
@Component
public class TraceIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        dealWithTraceId(servletRequest);
        // 继续后续调用
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 处理traceId
     */
    private void dealWithTraceId(ServletRequest servletRequest) {
        // 首先判断线程环境中是否已经包含“traceId”，如果不存在才进行添加。
        if (!ThreadContext.containsKey("traceId")) {
            // 优先从请求head中获取，如果head中没有，就自动生成一个存入到线程上下文中.
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String traceId = httpServletRequest.getHeader("TraceId");
            ThreadContext.put("traceId", Strings.isNullOrEmpty(traceId) ? "traceId-" + UUID.randomUUID().toString() : traceId);
        }
    }
}
