package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieAction {
    /**
     * 读取所有cookie
     * 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。浏览器提交Cookie时只会提交name与value属性。maxAge属性只被浏览器用来判断Cookie是否过期
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/showCookies")
    public String showCookies(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("----------展示Cookie操作----------");
        Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
        if (null == cookies) {
            System.out.println("没有cookie=========");
        } else {
            for (Cookie cookie : cookies) {
                System.out.println("name:" + cookie.getName() + ",value:" + cookie.getValue());
            }
        }
        return "success";
    }

    /**
     * 添加cookie
     * 
     * @param response
     * @param name
     * @param value
     */
    @RequestMapping(value = "/addCookie")
    public void addCookie(HttpServletResponse response, String name, String value) {
        System.out.println("----------增加Cookie操作----------");
        System.out.println("name="+name+" value="+value);
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(30 * 60);// 设置为30min
        response.addCookie(cookie);
    }

    /**
     * 修改cookie
     * 
     * @param request
     * @param response
     * @param name
     * @param value 注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，例如name、path、domain等，都要与原Cookie完全一样。否则，
     *            浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
     */
    @RequestMapping(value = "/editCookie")
    public void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        System.out.println("----------修改Cookie操作----------");
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("没有cookie==============");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    System.out.println("原值为:" + cookie.getValue());
                    cookie.setValue(value);
                    cookie.setMaxAge(30 * 60);// 设置为30min
                    System.out.println("被修改的cookie名字为:" + cookie.getName() + ",新值为:" + cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    /**
     * 删除cookie
     * 
     * @param request
     * @param response
     * @param name
     */
    @RequestMapping(value = "/delCookie")
    public void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        System.out.println("----------删除Cookie操作----------");
        Cookie[] cookies = request.getCookies();
        if (null == cookies) {
            System.out.println("没有cookie==============");
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);// 立即销毁cookie
                    System.out.println("被删除的cookie名字为:" + cookie.getName()+" value="+cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }
    
    
    @RequestMapping(value = "/doget")
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //设置服务器端以UTF-8编码进行输出
        response.setCharacterEncoding("UTF-8");
        //设置浏览器以UTF-8编码进行接收,解决中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //获取浏览器访问访问服务器时传递过来的cookie数组
        Cookie[] cookies = request.getCookies();
        //如果用户是第一次访问，那么得到的cookies将是null
        if (cookies!=null) {
            out.write("您上次访问的时间是：");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("lastAccessTime")) {
                    Long lastAccessTime =Long.parseLong(cookie.getValue());
                    Date date = new Date(lastAccessTime);
                    out.write(date.toLocaleString());
                }
            }
        }else {
            out.write("这是您第一次访问本站！");
        }
        
        //用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");//创建一个cookie，cookie的名字是lastAccessTime
        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        response.addCookie(cookie);
    }
    
    @RequestMapping(value = "/doPost")
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        doGet(request, response);
    }
    
}
