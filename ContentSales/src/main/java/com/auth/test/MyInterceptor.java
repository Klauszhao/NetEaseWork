package com.auth.test;


import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import org.springframework.web.servlet.HandlerInterceptor;  
import org.springframework.web.servlet.ModelAndView;  
  
public class MyInterceptor implements HandlerInterceptor{  
  
    /** 
     * 业务处理器处理之前被调用，被拦截返回false，反之能正常到Controller层 
     */  
    @Override  
    public boolean preHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler) throws Exception {  
        System.out.println("====preHandle====");  
        //如果返回false将不会到Controller层  
        return true;  
    }  
  
    /** 
     * 在业务处理完成请求后，在DispatcherServlet向客户端返回响应前被调用 
     */  
    @Override  
    public void postHandle(HttpServletRequest request,  
            HttpServletResponse response, Object handler,  
            ModelAndView modelAndView) throws Exception {  
        System.out.println("======postHandle=====");  
    }  
      
    /** 
     * 在DispatcherServlet完全处理完请求后被调用(可以在该方法进行一些资源的清理操作) 
     */  
    @Override  
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex)  
            throws Exception {  
        System.out.println("=====afterCompletion=======");  
    }  
}
      
