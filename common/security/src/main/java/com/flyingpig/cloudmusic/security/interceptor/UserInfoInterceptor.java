package com.flyingpig.cloudmusic.security.interceptor;

import com.alibaba.csp.sentinel.util.StringUtil;
import com.flyingpig.cloudmusic.security.util.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.获取请求头中的用户信息
        String userId = request.getHeader("userId");
        String role = request.getHeader("userRole");

        log.info("用户 {} 访问接口: {} {}", userId, request.getMethod(), request.getRequestURI());

        // 2.判断是否为空
        if (StringUtil.isNotBlank(userId)) {
            // 不为空，保存到ThreadLocal
            UserContext.setUser(new LocalUserInfo(Long.valueOf(userId), role));
        }
        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserContext.removeUser();
    }
}