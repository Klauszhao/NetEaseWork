package com.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Commodity;
import com.netease.service.CommodityService;

@Controller
public class CommonAction {
    @Autowired
    private CommodityService commodityservice;
    /**
     * 
     * 游客数据方法，查询数据展示数据。
     *
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @RequestMapping(value = "/show")
    public ModelAndView show(Model model) {
        List<Commodity> commodlist=commodityservice.queryForAll();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("commodityMap",commodlist);
        modelAndView.setViewName("showlist");
        return modelAndView;
    }
    /**
     * 
     * 游客 商品数据详情方法，将商品更为详细的信息展示到页面上
     *
     * @param id
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @RequestMapping(value = "/showDetail")
    public ModelAndView commodityDetail(String id,Model model) {
        ModelAndView modelAndView=new ModelAndView();
        //判断可否转换为short类型
        Integer sid=0;
        try {
            sid = Integer.parseInt(id.trim());
        } catch (NumberFormatException e) {
            System.out.println("字符串数据转换为数字错误，错误信息："+e);
            modelAndView.setViewName("Common/Error");
            return modelAndView;
        }
        Commodity commodity=commodityservice.queryById(sid);
        
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
    @RequestMapping(value = "/TestShow")
    public String test(Model model) {
        return "Common/Error";
    }
    
}
