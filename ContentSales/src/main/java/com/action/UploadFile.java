package com.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.Commodity;
import com.bean.Student;
import com.netease.service.CommodityService;
import com.netease.service.FileUpUtil;

import net.sf.json.JSONObject;

@Controller
public class UploadFile{
	/**
	 * 
	 */
	private Commodity commodity;
	private CommodityService commodityservice;
	
	// 从Action返回json数据给调用的Ajax。
    private String check_return;

	@Autowired
	private FileUpUtil FileUpUtil;

	/**
	 * 
	 * 通过后台生成相应的UpToken
	 *
	 * @param model
	 * @return
	 * @author zhaonan
	 * @since 2018年3月22日
	 */
    @RequestMapping(value = "/Uptoken", method = RequestMethod.POST)
    @ResponseBody
    public String getUptoken(String name,ModelMap model) {
        System.out.println("uptoken方法:"+name);
        // 获取七牛的uptoken
        String upToken=FileUpUtil.getUpToken();

        Map<String, String> map = new HashMap<String, String>();

        JSONObject json;
        map.put("upToken", upToken);
        json = JSONObject.fromObject(map);
        
        /*
        if (falg == "success") {
            map.put("check_type", "success");
            map.put("upToken", upToken);
            json = JSONObject.fromObject(map);
            check_return = json.toString(); // 正确就将json 字符化
        } else {
            map.put("check_type", "error");
            json = JSONObject.fromObject(map);
            check_return = json.toString();
        }
        */
        check_return=json.toString();
        System.out.println("check_return="+check_return);
        return check_return;
    }
    @RequestMapping(value = "/UpLoad")
    public String UpLoad(ModelMap model) {
        model.addAttribute("Student",  new Student());
        return "qiniuUpload";
    }
	
	
	public void ShowData() {
		System.out.println("标题："+commodity.getTitle());

		System.out.println("摘要："+commodity.getSummary());

		System.out.println("正文："+commodity.getContent());

		System.out.println("价格："+commodity.getPrice());
	}
	
	
	
	public Commodity getModel() {
		return commodity;
	}
	public void setSession(Map<String, Object> arg0) {

	}
	public CommodityService getCommodityservice() {
		return commodityservice;
	}
	public void setCommodityservice(CommodityService commodityservice) {
		this.commodityservice = commodityservice;
	}
}
