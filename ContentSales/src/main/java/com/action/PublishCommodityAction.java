package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.CartOnline;
import com.bean.Commodity;
import com.bean.Online;
import com.bean.OnlineForSale;
import com.bean.PurchaseRecord;
import com.bean.Student;
import com.common.Auth;
import com.common.JSONSring;
import com.netease.service.CommodityService;
import com.netease.service.PurchaseRecordService;

@Controller()
public class PublishCommodityAction {

    @Autowired
    private CommodityService commodityservice;
    @Autowired
    private PurchaseRecordService purchaseRecordService;
    
    private String ajaxResult;

    /**
     * 
     * 卖家 主页
     *
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("QueryForSaler") 
    @RequestMapping(value = "/QueryForSaler", method = RequestMethod.GET)
    public ModelAndView queryForSaler() {
        // 查询未被购买的商品
        List<Commodity> commodityList=commodityservice.queryForNotBuy();
        
        // 查询已经被购买的商品
        List<Commodity> commodityBuyMap=commodityservice.CommodityBought();
        
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commodityMap", commodityBuyMap);
        modelAndView.addObject("commodityNotSaleMap",commodityList);
        modelAndView.setViewName("Seller/CommodityList");
        return modelAndView;
    }
    
    /**
     * 
     * 跳转到发布界面
     *
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("PublishCommodity") 
    @RequestMapping(value = "/PublishCommodity", method = RequestMethod.GET)
    public String publishCommodity(ModelMap model) {
        model.addAttribute("commodity", new Commodity());
        return "Seller/PublishCommodity";
    }

    /**
     * 
     * 发布新商品，提交到数据库时事件
     *
     * @param commodity
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("SubmitCommodity") 
    @RequestMapping(value = "/SubmitCommodity", method = RequestMethod.POST)
    public ModelAndView insertCommodity(@ModelAttribute("SpringWeb") Commodity commodity, ModelMap model) {
        System.out.println("标题：" + commodity.getTitle());
        System.out.println("正文：" + commodity.getContent());
        System.out.println("正文：" + commodity.getPrice());
        System.out.println("url：" + commodity.getUrl());
        commodityservice.insert(commodity);
        System.out.println("商品id="+commodity.getId());
        // 插入后就跳转到商品详情页
        
        // 更新成功后就到显示界面
        Online online=new Online();
        CommodityTranOnline(online,commodity);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("online",online);
        modelAndView.setViewName("Seller/CommodityDelAndEdit"); //CommodityDetailForSale
        return modelAndView;
    }
    /**
     * 
     * 出售的所有商品
     *
     * @param id
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月29日
     */
    @Auth("commodityDetail") 
    @RequestMapping(value = "/commodityDetail")
    public ModelAndView commodityDetail(String id,Model model) {
        //判断可否转换为short类型
        Short sid=0;
        sid=Short.parseShort(id);
        OnlineForSale onlineForSale =purchaseRecordService.SumNumById(sid);
        Online online=new Online();
        TranOnline(online,onlineForSale);
        
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("online",online);
        modelAndView.setViewName("Seller/CommodityDetailForSale");  
        return modelAndView;
    }

    
    /**
     * 
     * 还未出售的商品信息
     * 数据详情页方法，根据id查询数据展示在界面上
     * 查询 出售数量
     * 
     * @param id
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("editAndDelCommodity")
    @RequestMapping(value = "/editAndDelCommodity")
    public ModelAndView editAndDelCommodity(String id,Model model) {
        //判断可否转换为short类型
        Short sid=0;
        sid=Short.parseShort(id);
        System.out.println("输出id="+sid);
/*        OnlineForSale onlineForSale =purchaseRecordService.SumNumById(sid);
        Online online=new Online();
        TranOnline(online,onlineForSale);*/
        
        Commodity commodity =commodityservice.queryById(sid.intValue());
        
        Online online=new Online();
        CommodityTranOnline(online,commodity);
        
        
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("online",online);
        modelAndView.setViewName("Seller/CommodityDelAndEdit");
        return modelAndView;
    }
    
    /**
     * 
     * 数据详情页方法，根据id查询数据展示在界面上
     *
     * 
     * @param id
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("editCommodity") 
    @RequestMapping(value = "/editCommodity")
    public ModelAndView editCommodity(String id,Model model) {
        //判断可否转换为short类型
        Short sid=0;
        sid=Short.parseShort(id);
        
        Commodity onlineForSale =commodityservice.queryById(sid.intValue());
        
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("commodity",onlineForSale);
        modelAndView.setViewName("Seller/EditCommodity");
        return modelAndView;
    }
    
    /**
     * 
     * 修改之后提交数据方法
     *
     * @param commodity
     * @param model
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月23日
     */
    @Auth("UpdateSubmit")
    @RequestMapping(value = "/UpdateSubmit", method = RequestMethod.POST)
    public ModelAndView updateCommodity(@ModelAttribute("SpringWeb") Commodity commodity, Model model){
        System.out.println("标题：" + commodity.getTitle());
        System.out.println("正文：" + commodity.getContent());
        System.out.println("价格：" + commodity.getPrice());
        Short isEdit=new Short((short) 1);
		commodity.setIsEditor(isEdit);
        commodityservice.updateByPrimaryId(commodity);
    	System.out.println("更新操作完成，id="+commodity.getId());
    	
    	// 更新成功后就到显示界面
        Online online=new Online();
        CommodityTranOnline(online,commodity);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("online",online);
        modelAndView.setViewName("Seller/CommodityDelAndEdit");
        
        return modelAndView;
    }
    /**
     * 
     * 删除未被购买的商品，使用与ajax调用
     *
     * @param id
     * @param ajaxResult 
     * @return
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月28日
     */
    
    @RequestMapping(value = "/deleteCommodity", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCommodity(@RequestBody String id,ModelMap model) {

        // 判断可否转换为short类型
        int index=id.indexOf("=");
        
        Short sid = 0;
        sid = Short.parseShort(id.substring(index+1));
        System.out.println("deleteCommodity的id="+sid);
        
        // 查询该商品是否已经被出售
        List<PurchaseRecord> pRecordList=purchaseRecordService.queryByCommodityId(sid.intValue());
        if(pRecordList.size()>0){
            setAjaxResult(JSONSring.errorString());
            return ajaxResult;
        }
        
        Commodity commodity=new Commodity();
        commodity.setId(sid);
        int flag=commodityservice.deleteById(commodity);
        if (flag == 1) {
            setAjaxResult(JSONSring.successString());
        } else {
            setAjaxResult(JSONSring.errorString());
        }
        return ajaxResult;
    }
    
    @Auth("selAuthError") 
    @RequestMapping(value = "/selAuthError", method = RequestMethod.GET)
    public ModelAndView selAuthError() {
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Seller/SelAuthError");
        return modelAndView;
    }
    /**
     * 
     * OnlineForSale对象  转  cartOnline对象
     *
     * @param online
     * @param commodity
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月31日
     */
    public void TranCartOnline(CartOnline cartOnline, OnlineForSale onlineForSale){
        cartOnline.setCommodityId(new Integer(onlineForSale.getCommodity().getId()));
        cartOnline.setNum(new Integer(onlineForSale.getSellerNum()));
        cartOnline.setPrice(onlineForSale.getCommodity().getPrice());
        cartOnline.setTitle(onlineForSale.getCommodity().getTitle());
        cartOnline.setUrl(onlineForSale.getCommodity().getUrl());
    }
    /**
     * 
     * OnlineForSale对象  转  Online对象
     *
     * @param online
     * @param commodity
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月31日
     */
    public void TranOnline(Online online, OnlineForSale onlineForSale){
        online.setCommodityId(new Integer(onlineForSale.getCommodity().getId()));
        online.setNum(onlineForSale.getSellerNum());
        online.setPrice(onlineForSale.getCommodity().getPrice());
        online.setTitle(onlineForSale.getCommodity().getTitle());
        online.setUrl(onlineForSale.getCommodity().getUrl());
        online.setSummary(onlineForSale.getCommodity().getSummary());
        online.setContent(onlineForSale.getCommodity().getContent());
    }
    /**
     * 
     * Commodity对象  转  Online对象
     *
     * @param online
     * @param commodity
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月31日
     */
    public void CommodityTranOnline(Online online, Commodity commodity) {
        online.setCommodityId(commodity.getId().intValue());
        online.setPrice(commodity.getPrice());
        online.setTitle(commodity.getTitle());
        online.setContent(commodity.getContent());
        online.setSummary(commodity.getSummary());
        online.setUrl(commodity.getUrl());
        online.setNum(0);  // 未出售，自然
       
    }
    public String getAjaxResult() {
        return ajaxResult;
    }

    public void setAjaxResult(String ajaxResult) {
        this.ajaxResult = ajaxResult;
    }
    
}
