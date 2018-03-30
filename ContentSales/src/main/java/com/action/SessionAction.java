package com.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;

@Controller
public class SessionAction {
    /**
     * user对象存入session
     * 
     * @param id
     * @param name
     * @param session
     * @return
     */
    @RequestMapping("/sessionTest")
    public ModelAndView localsessionAttributes(String password, String username, HttpSession session) {
        System.out.println(" name=" + username + "  password=" + password);
        session.setAttribute("currentUser", new User(username, password));
        ModelAndView mav = new ModelAndView("success");
        return mav;
    }

    /**
     * 获取session中的user对象
     * 
     * @param session
     * @return
     */
    @RequestMapping("/sessionAttributes")
    public ModelAndView sessionAttributesage(HttpSession session) {
        User u = (User) session.getAttribute("currentUser");
        System.out.println("---" + u.getUsername());
        ModelAndView mav = new ModelAndView("success");
        return mav;
    }

    @RequestMapping("/showSession")
    public String showSession(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("----------展示showSession操作----------");
        return "success";
    }
}
