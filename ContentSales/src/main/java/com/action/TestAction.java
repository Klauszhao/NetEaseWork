package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.CartOnline;
import com.bean.Student;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.testOne;

import net.sf.json.JSONObject;


@Controller
public class TestAction {

    private String check_return;
    @RequestMapping(value = "/hellTest")
    
    public String testOne(Model model){
        System.out.println("----------你好，此界面。");
        model.addAttribute("message", "测试"); 
        return "testForAjax";
    }
    
    
    @RequestMapping(value = "/TestForSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestBody String studentList, Model model) {
        System.out.println("字符串="+studentList);
         
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Student.class);  
        List<Student> studentlistaa =new ArrayList<>();
        try {
            studentlistaa = objectMapper.readValue(studentList, javaType);
        } catch (IOException e) {
           // logger.error("", e);
            System.out.println("e:"+e);
        }  
      for(Student stu:studentlistaa){
          System.out.println("name1=" + stu.getFirstName());
          System.out.println("name2="+stu.getLastName());
      }
      
        System.out.println("132132132132");  

        String falg = "";
        // 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
        Map<String, String> map = new HashMap<String, String>();
        // 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
        JSONObject json;

        if (falg == "success") {
            map.put("check_type", "success");
            json = JSONObject.fromObject(map);
            check_return = json.toString(); // 正确就将json 字符化
        } else {
            map.put("check_type", "error");
            json = JSONObject.fromObject(map);
            check_return = json.toString();
        }
        return check_return;
    }
    
    @RequestMapping(value = "/AjaxTestTwo", method = RequestMethod.POST)
    @ResponseBody
    public String testTwo(@RequestBody String studentList, Model model) {
        System.out.println("字符串="+studentList);
         
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Student.class);  
        List<Student> studentlistaa =new ArrayList<>();
        try {
            studentlistaa = objectMapper.readValue(studentList, javaType);
        } catch (IOException e) {
           // logger.error("", e);
            System.out.println("e:"+e);
        }  
      for(Student stu:studentlistaa){
          System.out.println("name1=" + stu.getFirstName());
          System.out.println("name2="+stu.getLastName());
      }
      
        System.out.println("132132132132");  

        String falg = "";
        // 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
        Map<String, String> map = new HashMap<String, String>();
        // 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
        JSONObject json;

        if (falg == "success") {
            map.put("check_type", "success");
            json = JSONObject.fromObject(map);
            check_return = json.toString(); // 正确就将json 字符化
        } else {
            map.put("check_type", "error");
            json = JSONObject.fromObject(map);
            check_return = json.toString();
        }
        return check_return;
    }

    public String getCheck_return() {
        return check_return;
    }

    public void setCheck_return(String check_return) {
        this.check_return = check_return;
    }
}
