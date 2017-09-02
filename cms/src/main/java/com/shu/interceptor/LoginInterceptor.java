package com.shu.interceptor;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by james on 2017/2/13.
 * 登录认证拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{
    private final Logger log = Logger.getLogger(LoginInterceptor.class);

    private Set<String> unauthenticatedUrls = new HashSet<>();

    public LoginInterceptor()  throws IOException {
        try (LineNumberReader reader = new LineNumberReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("unauthenticated-urls.txt")))) {
            for (String s = reader.readLine(); !StringUtils.isEmpty(s); s = reader.readLine()) {
                unauthenticatedUrls.add(s);
            }
        }
    }

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o)
            throws Exception {

        String requestUri = httpServletRequest.getRequestURI();
        String contextPath = httpServletRequest.getContextPath();
        String url = requestUri.substring(contextPath.length());
        log.info("===============================================");
        log.info("requestUri:" + requestUri);
        log.info("contextPath:"+ contextPath);
        log.info("url:" + url);

        if (unauthenticatedUrls.contains(url)) return true;

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("manager") != null) {
            return true;
        }

        if (session.getAttribute("enterprise") != null) {
            return true;
        }

        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login/login.jsp")
                .forward(httpServletRequest, httpServletResponse);

        return false;
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
            throws Exception {

    }

    /**
     * Handler执行完成之后调用这个方法
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e)
            throws Exception {

    }


}
