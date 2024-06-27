package com.hello.orderservice.Filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 可以通过Filter设置限流
 */
@Slf4j
@Component
public class LimitFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info(String.format("LimitFilter doFilter ThreadId:%s", Thread.currentThread().getId()));
        // 继续后续调用
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
