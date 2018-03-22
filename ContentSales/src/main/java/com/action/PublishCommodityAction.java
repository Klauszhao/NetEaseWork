package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Commodity;
import com.bean.Student;
import com.netease.service.CommodityService;

@Controller()
public class PublishCommodityAction {
    private Commodity commodity;

    @Autowired
    private CommodityService commodityservice;

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String publish(ModelMap model) {
        System.out.println("----------publish");
        model.addAttribute("commodity", new Commodity());
        return "publish";
    }

    @RequestMapping(value = "/commoditypublish", method = RequestMethod.POST)
    public String commodityPublish(@ModelAttribute("SpringWeb") Commodity commodity, ModelMap model) {
        System.out.println("标题：" + commodity.getTitle());
        System.out.println("正文：" + commodity.getContent());
        System.out.println("正文：" + commodity.getPrice());
        commodityservice.insert(commodity);
        return "success";
    }

    @RequestMapping(value = "/ShowData", method = RequestMethod.POST)
    public String showData() {
        if (commodity == null) {
            System.out.println("空值");
            return "error";
        }
        System.out.println("标题：" + commodity.getTitle());
        System.out.println("摘要：" + commodity.getSummary());
        System.out.println("正文：" + commodity.getContent());
        System.out.println("价格：" + commodity.getPrice());
        return "success";
    }
    @RequestMapping(value = "/show")
    public ModelAndView show(Model model) {
        List<Commodity> commodlist=commodityservice.queryForAll();
        System.out.println("返回数据大小："+commodlist.size());
        for(Commodity comdity:commodlist){
        	System.out.println("标题："+comdity.getTitle());
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("commodityMap",commodlist);
        modelAndView.setViewName("showlist");
        return modelAndView;
    }
    
    @RequestMapping(value = "/showDetail")
    public ModelAndView commodityDetail(String id,Model model) {
        System.out.println("showCommodityDetail的id="+id);
        //判断可否转换为short类型
        Integer sid=0;
        sid=Integer.parseInt(id);
        
        Commodity commodity=commodityservice.queryById(sid);
        
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("id", commodity.getId());
        modelAndView.addObject("title",commodity.getTitle());
        modelAndView.addObject("summary",commodity.getSummary());
        modelAndView.addObject("price",commodity.getPrice());
        System.out.println("价格："+commodity.getParentid());
        
        modelAndView.addObject("url",commodity.getUrl());
        modelAndView.addObject("content",commodity.getContent());
        modelAndView.addObject("buyNum",commodity.getParentid());
        
        modelAndView.setViewName("showDetail");
        return modelAndView;
    }
    
    @RequestMapping(value = "/editCommodity")
    public ModelAndView editCommodity(String id,Model model) {
        System.out.println("editCommodity的id="+id);
        //判断可否转换为short类型
        Integer sid=0;
        sid=Integer.parseInt(id);
        
        Commodity commodity=commodityservice.queryById(sid);
        
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("id",commodity.getId());
        modelAndView.addObject("title",commodity.getTitle());
        modelAndView.addObject("summary",commodity.getSummary());
        modelAndView.addObject("price",commodity.getPrice());
        System.out.println("价格："+commodity.getParentid());
        
        modelAndView.addObject("url",commodity.getUrl());
        modelAndView.addObject("content",commodity.getContent());
        modelAndView.addObject("buyNum",commodity.getParentid());
        
        modelAndView.setViewName("editCommodity");
        return modelAndView;
    }
    
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public ModelAndView updateCommodity(@ModelAttribute("SpringWeb") Commodity commodity, Model model){
        System.out.println("标题：" + commodity.getTitle());
        System.out.println("正文：" + commodity.getContent());
        System.out.println("价格：" + commodity.getPrice());
        Short isEdit=new Short((short) 1);
		commodity.setIsEditor(isEdit);
        commodityservice.updateByPrimaryId(commodity);
    	System.out.println("更新操作完成，id="+commodity.getId());
    	
    	
    	// 更新成功后就到显示界面
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("id",commodity.getId());
        modelAndView.addObject("title",commodity.getTitle());
        modelAndView.addObject("summary",commodity.getSummary());
        modelAndView.addObject("price",commodity.getPrice());
        System.out.println("价格："+commodity.getParentid());
        
        modelAndView.addObject("url",commodity.getUrl());
        modelAndView.addObject("content",commodity.getContent());
        modelAndView.addObject("buyNum",commodity.getParentid());
        modelAndView.setViewName("showDetail");
        return modelAndView;
    }
}
