package com.jincong.springboot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
/**
 * 过滤器
 *
 * @author  j_cong
 * @date    2020/01/21
 * @version V1.0
 */
@Component
public class TimeFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("过滤器[init]初始化过滤器：{}", filterConfig.getFilterName());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        LOGGER.info("过滤器[doFilter] start to doFiler, url={}", gerUrlFrom(servletRequest));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("doFilter conusumer time");
        // 真正的处理逻辑
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        stopWatch.prettyPrint();
        LOGGER.info("过滤器[doFilter] end to doFilter");

    }

    @Override
    public void destroy() {

        LOGGER.info("过滤器[destroy]销毁过滤器");
    }

    private String gerUrlFrom(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest)request).getRequestURI().toString();
        }
        return "";
    }
}
