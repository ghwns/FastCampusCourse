package org.delivery.api.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // HTTP 서블릿으로 형변환해서 넘겨주면 여기서 읽어도 리퀘스트, 리스폰스를 읽지 않은 것처럼
        var req = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);
        // ->이후의 req, res는 CCW 객체로 읽힘

        filterChain.doFilter(req, res);

        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);

            headerValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info("\n>>>>> uri : {} ,\n method : {} ,\n header : {} ,\n body : {}\n", uri, method, headerValues, requestBody);

        var responseHeaderValues = new StringBuilder();

        res.getHeaderNames().forEach(headerKey ->{
            var headerValue = res.getHeader(headerKey);

            responseHeaderValues.append("[").append(headerKey).append(" : ").append(headerValue).append("] ");
        });

        var responseBody = new String(res.getContentAsByteArray());

        log.info("\n<<<<< uri : {} ,\n method : {} ,\n header : {} ,\n body : {}\n", uri, method, responseHeaderValues, responseBody);

        res.copyBodyToResponse();
    }
}
