package com.auth.test;


import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller  
@RequestMapping("/interceptor")  
public class MyInterceptorController {  
  @RequestMapping("/inter1")  
  private String interceptor1(){  
      System.out.println("=====inter1=====");  
      return "index";  
  }  
  @RequestMapping("/inter2")  
  private String interceptor2(){  
      System.out.println("======inter2=====");  
      return "index";  
  }  
}
