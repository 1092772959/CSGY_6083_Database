package com.backend.qa.config;

import com.backend.qa.service.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class AccessInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionManager sessionManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod)handler;

            String username = getUser(request, response);
            UserContext.setUser(username);

            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if(accessLimit == null) {
                return true;
            }
            boolean needLogin = accessLimit.needLogin();
            if(needLogin) {
                if(username == null) {
                    response.setContentType("application/json;charset=UTF-8");
                    OutputStream out = response.getOutputStream();
                    String str  = "session error";
                    out.write(str.getBytes("UTF-8"));
                    out.flush();
                    out.close();
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContext.removeUser();
    }

    private String getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter("token");
        String cookieToken = null;
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) {
                cookieToken = cookie.getValue();
            }
        }
        if(cookieToken == null && paramToken == null) {
            return null;
        }
        return sessionManager.get(cookieToken != null ? cookieToken:paramToken);
    }

}
