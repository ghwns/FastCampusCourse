package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info(">>>>>>>>진입전<<<<<<<<");


//        var req = new HttpServletRequestWrapper((HttpServletRequest) servletRequest);
//        var res = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
//
//        var br = req.getReader(); //여기서 body를 읽어서 controller에서 body를 못 읽는 문제 발생
//
//        var list = br.lines().collect(Collectors.toList());
//
//        list.forEach(it ->{
//            log.info("{}", it);
//        });

        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        filterChain.doFilter(req, res);

        var reqJson = new String(req.getContentAsByteArray());
        log.info("req {}", reqJson);

        var resJson = new String(res.getContentAsByteArray());
        log.info("res {}", resJson);

        log.info(">>>>>>>>진입후<<<<<<<<");

        res.copyBodyToResponse(); //body에 response를 덮어씌울 수 있음
    }
}
