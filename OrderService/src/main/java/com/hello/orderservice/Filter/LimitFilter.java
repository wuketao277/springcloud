package com.hello.orderservice.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * 可以通过Filter设置限流
 */
@Slf4j
@Component
public class LimitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(String.format("LimitFilter doFilter ThreadId:%s", Thread.currentThread().getId()));
        // 通过阻塞模式获取Redis中list里的令牌，如果获取不到就等待直到超时。获取到令牌后就继续调用。
        // 继续后续调用
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
