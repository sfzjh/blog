package com.sfzjh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @Author  孙飞
 * @Date  2021年03月09日 13:40
 * @PackageName  com.sfzjh.interceptor
 * @Name  LoginInterceptor
 * @Version  1.0
 * @Description  TODO
 * Created with IntelliJ IDEA.
 */
public class LoginInterceptor implements HandlerInterceptor {
    String user = "user";
    /**
     * 判断用户是否登录
     * @author  孙飞
     * @date  2021年03月09日 13:42
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @return  boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if (request.getSession().getAttribute(user) == null){
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
