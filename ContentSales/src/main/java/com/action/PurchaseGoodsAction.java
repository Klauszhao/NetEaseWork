package com.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bean.CartOnline;
import com.bean.Commodity;
import com.bean.GoodsForOnline;
import com.bean.PurchaseRecord;
import com.bean.ShoppingRecord;
import com.common.Auth;
import com.common.CommonDate;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netease.service.CommodityService;
import com.netease.service.PurchaseRecordService;
import com.netease.service.ShoppingRecordService;

import net.sf.json.JSONObject;

@Controller
public class PurchaseGoodsAction {

    @Autowired
    private PurchaseRecordService purchaseRecordService;

    @Autowired
    private CommodityService commodityservice;

    @Autowired
    private ShoppingRecordService shoppingRecordService;

    // 从Action返回json数据给调用的Ajax。
    private String check_return;
    @Auth("accountShow")  
    @RequestMapping(value = "/accountShow", method = RequestMethod.GET)
    // 财务页面展示数据
    public ModelAndView accoutShow() {
        List<PurchaseRecord> purchaseRecordList = purchaseRecordService.queryForAll();
        System.out.println("查询数目："+purchaseRecordList.size());
        
        List<GoodsForOnline> goodsForOnlines = new ArrayList<>();
        BigDecimal totalPrice = purchaseRecordToGoodsForOnline(purchaseRecordList, goodsForOnlines);

        System.out.println("总价：" + totalPrice.toString());
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ccountMap", goodsForOnlines);
        modelAndView.addObject("totalPrice", totalPrice.setScale(2, BigDecimal.ROUND_DOWN).toString());
        modelAndView.setViewName("Buyer/AccountShow");
        return modelAndView;
    }
    @Auth("cartShow") 
    @RequestMapping(value = "/cartShow", method = RequestMethod.GET)
    public ModelAndView cartGoods() {

        List<ShoppingRecord> shoppingRecordList = shoppingRecordService.queryForAll();
        List<CartOnline> cartOnlineList = new ArrayList<>();
        TranCartOnline(shoppingRecordList, cartOnlineList);
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cartOnlineMap", cartOnlineList);
        modelAndView.setViewName("Buyer/CartShow");
        return modelAndView;
    }

    /**
     * 
     *
     * @param purchaseRecord
     * @param model
     * @return
     * @author zhaonan
     * @since 2018年3月16日
     */
    @Auth("CommodityDetailForBuy") 
    @RequestMapping(value = "/CommodityDetailForBuy", method = RequestMethod.GET)
    public ModelAndView commodityForBuy(String id, Model model) { // PurchaseRecord purchaseRecord 思考，这里是否可以传一个对象
        Integer sid = Integer.parseInt(id);
        Commodity commodity = commodityservice.queryById(sid);
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        GoodsForOnline goodsForOnline = new GoodsForOnline();
        TrangoodForOnLine(goodsForOnline, commodity);
        modelAndView.addObject("goodsForOnline", goodsForOnline);
        modelAndView.setViewName("Buyer/CommodityDetailForBuy");
        return modelAndView;
    }

    @RequestMapping(value = "/buyCommodity", method = RequestMethod.POST)
    @ResponseBody
    public String buyCommodity(@ModelAttribute GoodsForOnline goodsForOnline, Model model) {
        System.out.println("buyCommodity,id=" + goodsForOnline.getCommodityId() + "  num=" + goodsForOnline.getNum());
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        TranPurchaseRecord(purchaseRecord, goodsForOnline);
        String falg = purchaseRecordService.insert(purchaseRecord);

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

    @RequestMapping(value = "/cartSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String cartCommoditySubmit(@RequestBody String cartOnlineStr, Model model) {

        System.out.println("cartOnlineList=" + cartOnlineStr);
        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, CartOnline.class);
        List<CartOnline> cartOnlineList = new ArrayList<>();
        try {
            cartOnlineList = objectMapper.readValue(cartOnlineStr, javaType);
        } catch (IOException e) {
            System.out.println("解析出错，e=" + e);
        }
        
        List<PurchaseRecord> purchaseRecordList = new ArrayList<>();
        TranPurchaseRecord(purchaseRecordList, cartOnlineList);
        
        List<ShoppingRecord> shoppingRecordList=new ArrayList<>();
        TranShoppingRecord(shoppingRecordList,cartOnlineList);
        
        /*
         * 两个步骤，这两件事应该是一个事物控制 1、将购物车表中的数据全部更新为已经购买。 2、在购买表中增加购物记录。
         * 
         */
        
        String falg =purchaseRecordService.cartToPurchase(purchaseRecordList, shoppingRecordList);
        
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

    @RequestMapping(value = "/joinCart", method = RequestMethod.POST)
    @ResponseBody
    public String joinCart(@ModelAttribute("SpringWeb") GoodsForOnline goodsForOnline, Model model) {
        System.out.println("joinCart.id=" + goodsForOnline.getCommodityId());
        ShoppingRecord shoppingRecord = new ShoppingRecord();
        TranShoppingRecord(shoppingRecord, goodsForOnline);
        String falg = shoppingRecordService.insert(shoppingRecord);

        // 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
        Map<String, String> map = new HashMap<String, String>();
        map.put("check_type", "success");

        // 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
        JSONObject json;

        if (falg == "success") {
            json = JSONObject.fromObject(map);
            check_return = json.toString(); // 正确就将json 字符化
        } else {
            map.put("check_type", "error");
            json = JSONObject.fromObject(map);
            check_return = json.toString();
        }
        return check_return;
    }
    @Auth("queryForNotBuy") 
    @RequestMapping(value = "/queryForNotBuy", method = RequestMethod.GET)
    public ModelAndView queryForNotBuy() {
        List<Commodity> commodityList=commodityservice.queryForNotBuy();
        System.out.println("---商品数量："+commodityList.size());
        for(Commodity com:commodityList){
            System.out.println("title="+com.getTitle());
        }
        
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commodityMap", commodityList);
        modelAndView.setViewName("Buyer/CommodityNotBuy");
        return modelAndView;
    }
    @Auth("queryForShow") 
    @RequestMapping(value = "/queryForShow", method = RequestMethod.GET)
    public ModelAndView queryForBuyer() {
        // 查询未被购买的商品
        List<Commodity> commodityList=commodityservice.queryForNotBuy();
        
        // 查询已经被购买的商品
        List<Commodity> commodityBuyMap=commodityservice.CommodityBought();
        
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commodityMap", commodityList);
        modelAndView.addObject("commodityBuyMap",commodityBuyMap);
        modelAndView.setViewName("Buyer/CommodityShow");
        return modelAndView;
    }
    @Auth("buyAuthError") 
    @RequestMapping(value = "/buyAuthError", method = RequestMethod.GET)
    public ModelAndView buyAuthError() {
        // 将数据展示到页面上
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Buyer/BuyAuthError");
        return modelAndView;
    }
    
    /**
     * 将查询数据进行装换，转换为页面财务数据展示 
     *
     * @param purchaseRecordList
     * @param goodsForOnlines
     * @author zhaonan
     * @since 2018年3月13日
     */
    public static BigDecimal purchaseRecordToGoodsForOnline(List<PurchaseRecord> purchaseRecordList,
            List<GoodsForOnline> goodsForOnlines) {
        GoodsForOnline good;
        BigDecimal totalPrice = new BigDecimal(0);
        for (PurchaseRecord pr : purchaseRecordList) {
            good = new GoodsForOnline();
            good.setCommodityId(pr.getCommodityId());
            good.setUrl(pr.getCommodity().getUrl());
            good.setNum(pr.getCommodityNum());
            good.setPrice(pr.getCommodity().getPrice());
            good.setTitle(pr.getCommodity().getTitle());
            good.setCreatetime(CommonDate.TranDate(pr.getCreateTime()));

            totalPrice = totalPrice.add(good.getPrice());
            goodsForOnlines.add(good);
        }
        return totalPrice;
    }

    /**
     * 将类型转换成页面数据 
     *
     * @param gForOnline
     * @param commodity
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月16日
     */
    public void TrangoodForOnLine(GoodsForOnline gForOnline, Commodity commodity) {
        if (commodity == null) {
            System.out.println("commodity为null");
            return;
        }
        gForOnline.setCommodityId(commodity.getId());
        gForOnline.setPrice(commodity.getPrice());
        gForOnline.setTitle(commodity.getTitle());
        gForOnline.setSummary(commodity.getSummary());
        gForOnline.setContent(commodity.getContent());
        gForOnline.setUrl(commodity.getUrl());
        gForOnline.setNum(1);
    }

    public void TranPurchaseRecord(PurchaseRecord purchaseRecord, GoodsForOnline gForOnline) {
        if (purchaseRecord == null || gForOnline == null) {
            return;
        }
        purchaseRecord.setCommodityId((short) gForOnline.getCommodityId());
        purchaseRecord.setCommodityNum((short) gForOnline.getNum());
        purchaseRecord.setUserId((short) 1); // 就一个用户，就固定写
        purchaseRecord.setCreateTime(new Date());
    }

    /**
     * 
     * 将CartOnline对象转换成PurchaseRecord对象
     *
     * @param purchaseRecordList
     * @param cartOnlineList
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月20日
     */
    public void TranPurchaseRecord(List<PurchaseRecord> purchaseRecordList, List<CartOnline> cartOnlineList) {
        for (CartOnline caOnline : cartOnlineList) {
            PurchaseRecord purchaseRecord = new PurchaseRecord();
            purchaseRecord.setUserId((short) 1);
            purchaseRecord.setCommodityId((short) caOnline.getCommodityId().intValue());
            purchaseRecord.setCommodityNum((short) caOnline.getNum().intValue());
            purchaseRecord.setCreateTime(new Date());
            purchaseRecordList.add(purchaseRecord);
        }
    }

    public void TranShoppingRecord(ShoppingRecord shoppingRecord, GoodsForOnline gForOnline) {
        if (shoppingRecord == null || gForOnline == null) {
            return;
        }
        shoppingRecord.setCommodityId((short) gForOnline.getCommodityId());
        shoppingRecord.setNum((short) gForOnline.getNum());
        shoppingRecord.setIsSettlement((short) 0);
        shoppingRecord.setCreatetime(new Date());
    }

    /**
     * 
     * 将ShoppingRecord对象转换成CartOnline对象
     *
     * @param shoppingRecordList
     * @param cartOnlineList
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月20日
     */
    public void TranCartOnline(List<ShoppingRecord> shoppingRecordList, List<CartOnline> cartOnlineList) {
        CartOnline cartOnline;
        for (ShoppingRecord shRecord : shoppingRecordList) {
            cartOnline = new CartOnline();
            cartOnline.setId(new Integer(shRecord.getId()));
            cartOnline.setNum(new Integer(shRecord.getNum()));
            cartOnline.setCommodityId(new Integer(shRecord.getCommodityId()));
            cartOnline.setPrice(shRecord.getCommodity().getPrice());
            cartOnline.setTitle(shRecord.getCommodity().getTitle());
            cartOnline.setUrl(shRecord.getCommodity().getUrl());
            cartOnlineList.add(cartOnline);
        }
    }
    /**
     * 
     * 将CartOnline数据转换为ShoppingRecord
     *
     * @param shoppingRecordList
     * @param cartOnlineList
     * @author zhaonan<zhaonan1@corp.netease.com>
     * @since 2018年3月20日
     */
    public void TranShoppingRecord(List<ShoppingRecord> shoppingRecordList, List<CartOnline> cartOnlineList) {
        ShoppingRecord sRecord;
        for(CartOnline cOnline:cartOnlineList){
            sRecord=new ShoppingRecord();
            sRecord.setId((short) cOnline.getId().intValue());
            sRecord.setCommodityId((short) cOnline.getCommodityId().intValue());
            sRecord.setIsSettlement((short) 1);
            shoppingRecordList.add(sRecord);
        }
    }
 
}
