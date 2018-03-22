package com.action;

import java.util.Map;

import org.springframework.stereotype.Controller;

import com.bean.Commodity;
import com.netease.service.CommodityService;

@Controller
public class UploadFile{
	/**
	 * 
	 */
	private Commodity commodity;
	private CommodityService commodityservice;

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
