package com.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Student;
import com.bean.User;

import net.sf.json.JSONObject;

//import net.sf.json.JSONObject;

@Controller
public class LoginAction {
	
	// 从Action返回json数据给调用的Ajax。
	private String check_return;
	/**
	 * 测试
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public String student_test(ModelMap model) {  //
		System.out.println("----------你好，此界面。");
		model.addAttribute("ftName",  new Student());
		return "student";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(@ModelAttribute("SpringWeb") User user,
			ModelMap model) {
		System.out.println("Login方法");
		System.out.println("姓名：" + user.getUsername() + "---密码："
				+ user.getPassword());
		User buy_user = new User();
		buy_user.setUsername("buyer");
		buy_user.setPassword("37254660e226ea65ce6f1efd54233424");

		User salle_user = new User();
		salle_user.setUsername("seller");
		salle_user.setPassword("981c57a5cfb0f868e064904b8745766f");

		if (user.getUsername().equals(buy_user.getUsername())
				&& user.getPassword().equals(buy_user.getPassword())) {
			// 买家的信息确认
			return "redirect:queryForShow";
		} else if (user.getUsername().equals(salle_user.getUsername())
				&& user.getPassword().equals(salle_user.getPassword())) {
			// 卖家的信息确认
		    System.out.println("到了卖家");
			return "redirect:queryForSaler";
		}
		return "error";
	}

	/**
	 * 此方法主要是验证用户名和密码存在数据库中， 如果存在，则 输出“OK” 否则 输出错误json，从而进入ajax的error方法中
	 * 
	 * @return
	 */
	@RequestMapping(value = "/CheckTwo", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, String> CheckTwo(@RequestBody User user) {
		System.out.println("CheckTwo方法");
		System.out.println("姓名：" + user.getUsername() + "---密码："
				+ user.getPassword());
		// 调用查询数据的代码
		User buy_user = new User();
		buy_user.setUsername("buyer");
		buy_user.setPassword("794aad24cbd58461011ed9094b7fa212");

		User salle_user = new User();
		buy_user.setUsername("seller");
		buy_user.setPassword("64c9ac2bb5fe46c3ac32844bb97be6bc");

		boolean falg = false;
		if (user.getUsername().equals(buy_user.getUsername())
				&& user.getPassword().equals(buy_user.getPassword())) {
			// 买家的信息确认
			falg = true;
		} else if (user.getUsername().equals(salle_user.getUsername())
				&& user.getPassword().equals(salle_user.getPassword())) {
			// 卖家的信息确认
			falg = true;
		}
		// 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
		Map<String, String> result = new HashMap<String, String>();
		result.put("check_type", "ok");
		
		// 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
		JSONObject json = JSONObject.fromObject(result);

		if (falg) {
			check_return = json.toString(); // 正确就将json 字符化
		} else {
			result.put("check_type", "false");
			json = JSONObject.fromObject(result);
			check_return = json.toString();
		}
		return result;
	}
	@RequestMapping(value = "/Check", method = { RequestMethod.POST })
	@ResponseBody
	public String Check(@ModelAttribute User user) {
		System.out.println("user="+user.getUsername()+"  pass="+user.getPassword());
		// 调用查询数据的代码
		User buy_user = new User();
		buy_user.setUsername("buyer");
		buy_user.setPassword("37254660e226ea65ce6f1efd54233424");

		User salle_user = new User();
		salle_user.setUsername("seller");
		salle_user.setPassword("981c57a5cfb0f868e064904b8745766f");

		boolean falg = false;
		System.out.println(buy_user.getUsername()+"--"+buy_user.getPassword());
		if(user.getUsername().equals(buy_user.getUsername())){
		    System.out.println("---姓名正确");
		}
	      if(user.getPassword().equals(buy_user.getPassword())){
	            System.out.println("---密码正确");
	        }
		
		if (user.getUsername().equals(buy_user.getUsername())
				&& user.getPassword().equals(buy_user.getPassword())) {
			// 买家的信息确认
			falg = true;
		} else if (user.getUsername().equals(salle_user.getUsername())
				&& user.getPassword().equals(salle_user.getPassword())) {
			// 卖家的信息确认
			falg = true;
		}
		// 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
		Map<String, String> map = new HashMap<String, String>();
		map.put("check_type", "ok");

		// 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
		JSONObject json = JSONObject.fromObject(map);

		if (falg) {
			check_return = json.toString(); // 正确就将json 字符化
		} else {
			map.put("check_type", "false");
			json = JSONObject.fromObject(map);
			check_return = json.toString();
		}
		System.out.println("json----:" + getCheck_return());
		return check_return;
	}

	public String getCheck_return() {
		return check_return;
	}

	public void setCheck_return(String check_return) {
		this.check_return = check_return;
	}
}
