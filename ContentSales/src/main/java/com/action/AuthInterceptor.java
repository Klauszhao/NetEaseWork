package com.action;


import org.aspectj.weaver.patterns.AnyAnnotationTypePattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.common.Auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Set;

public class AuthInterceptor implements HandlerInterceptor {
    
    public static final String SESSION_USERID = "kUSERID";

    public static final String SESSION_AUTHS = "kAUTHS";

    /**
     * 业务处理器处理之前被调用，被拦截返回false，反之能正常到Controller层 
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean flag = true;
        System.out.println("进入拦截器");
        if (handler instanceof HandlerMethod) {
            Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
            System.out.println("SESSION_USERID为空Auth");
            if (auth != null) {// 有权限控制的就要检查
                if (request.getSession().getAttribute(SESSION_USERID) == null) {// 没登录看游客的首页
                    System.out.println("SESSION_USERID为空11");
                    response.setStatus(HttpStatus.FORBIDDEN.value());
                    response.setContentType("text/html; charset=UTF-8");
                    response.sendRedirect("/ContentSales/show"); 
/*                    PrintWriter out = response.getWriter();
                    out.write("{\"type\":\"nosignin\",\"msg\":\"请您先登录!\"}");
                    out.flush();
                    out.close();*/
                    flag = false;
                } else {
                    // 登录了检查,方法上只是@Auth,表示只要求登录就能通过.@Auth("authority")这类型,验证用户权限
                    System.out.println("SESSION_USERID=null");
                    if (!"".equals(auth.value())) {
                        Set<String> auths = (Set<String>) request.getSession().getAttribute(SESSION_AUTHS);
                        System.out.println("auth.value()="+auth.value());
                        if (!auths.contains(auth.value())) {         // 提示用户没权限访问，访问错误
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType("text/html; charset=UTF-8");
                            response.sendRedirect("/ContentSales/"+getAuthErrorJsp(auths));  
                            /*                            
                            PrintWriter out = response.getWriter();
                            out.write("{\"type\":\"noauth\",\"msg\":\"您没有" + auth.name() + "权限!\"}");
                            out.flush();
                            out.close();
                            */
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    /**
     * 
     * 从权限控制中获取相应的访问错误页面
     *
     * @param auths
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月31日
     */
    public String getAuthErrorJsp(Set<String> auths){
        String authError="";
        for(String err:auths){
            if(err.contains("AuthError")){
                authError=err;
            }
        }
        System.out.println("authError="+authError);
        return authError;
    }
}
