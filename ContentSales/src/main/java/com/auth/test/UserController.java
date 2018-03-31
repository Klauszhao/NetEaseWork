package com.auth.test;

import java.util.Arrays;
import java.util.HashSet;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.action.AuthInterceptor;
import com.bean.User;
import com.common.Auth;

@Controller  
@RequestMapping(value="/Auth")  
public class UserController {
    
    @RequestMapping(value="/logintest")  
    @ResponseBody  
    public boolean login(HttpSession session,User user){  
        boolean result=false;  
        //模拟从数据库查出存在这样的用户  
        Long userId=user.getId().longValue();  
        if(userId!=null&&userId>0){  
            session.setAttribute(AuthInterceptor.SESSION_USERID, userId);  
            session.setAttribute(AuthInterceptor.SESSION_AUTHS, new HashSet<String>(Arrays.asList("user_list", "user_query", "user_save")));  
            result=true;  
        }  
        return result;  
    }  
    
    @RequestMapping(value="/test")  
    @ResponseBody  
    public boolean loginTest(HttpSession session,String name){  
        boolean result=false;  
        //模拟从数据库查出存在这样的用户  
        System.out.println("test方法，name="+name);
        if(name!=null&&name.length()>2){  
            session.setAttribute(AuthInterceptor.SESSION_USERID, name);  
            session.setAttribute(AuthInterceptor.SESSION_AUTHS, new HashSet<String>(Arrays.asList("user_list", "user_query", "user_save")));  
            result=true;  
        }  
        return result;  
    }  
    
    
    @Auth("user_queryXXXX")  
    @RequestMapping(value="/querytest")  
    @ResponseBody  
    public String query(){  
        System.out.println("query");  
        return getClass().toString();  
    }  
    @Auth("user_list")  
    @RequestMapping(value="/listtest")  
    @ResponseBody  
    public String list(){  
        System.out.println("list");  
        return getClass().toString();  
    }  
    @Auth("user_save")  
    @RequestMapping(value="/addtest")  
    public String add(User user){  
        System.out.println("add:"+user);  
        return "success";  
    }  
}  