package com.netease.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Commodity;
import com.bean.ShoppingRecord;
import com.common.PageBean;
import com.dao.CommodityMapper;
import com.netease.service.CommodityService;

@Service("commodityService")
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMap;

    @Override
    public String insert(Commodity commodity) {

        /*
         * Date nowdate=new Date(); SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");//设置日期格式
         */
        commodity.setCreatetime(new Date());
        this.commodityMap.insert(commodity);
        return "success";
    }

    @Override
    public String update(Commodity commodity) {
        this.commodityMap.updateByPrimaryKey(commodity);
        return null;
    }

    @Override
    public boolean delete(Commodity commodity) {
        return false;
    }

    @Override
    public int deleteById(Commodity commodity) {
        int result=0;
        result=commodityMap.deleteByPrimaryKey(commodity.getId().intValue());
        return result;
    }

    @Override
    public boolean query(String deptName) {
        return false;
    }

    /**
     * 查询
     * 
     * @return
     */
    @Override
    public List<Commodity> queryForAll() {
        List<Commodity> listCommodity = this.commodityMap.selectForShow((short) 0, (short) 0);
        return listCommodity;
    }

    @Override
    public PageBean queryForPage(String hql, int pageSize, int page) {
        return null;
    }

    @Override
    public Commodity queryById(Integer id) {
        Commodity commodity = this.commodityMap.selectByPrimaryKey(id);
        return commodity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    /*
     * 对一个商品进行修改、更新操作，必须通过事务管理来控制， 此处的逻辑是：对一个商品更新，不过是增加一个一条记录，之前的记录不直接删除，保留更改。 不过造成的问题就是：
     * 所有跟此记录相关的所有物品都要被更新，比如：购买此物品的用户的交易记录，这个时候需要更改该记录 相比较如 直接修改记录，之前的记录就看不到，谁操作的就更加难以看到，此处主要看应用场景。
     * 
     * 参考淘宝，淘宝里很多宝贝 过几年后就下架了，单依旧在用户的购买记录中，并且用户点击该商品依旧可以看到一些。
     * 此操作更新后新的id 存进了commodity对象中
     * 
     * @see com.netease.service.CommodityService#updateByPrimaryId(com.bean. Commodity)
     */
    public void updateByPrimaryId(Commodity commodity) {
        // 插入数据，返回id

        System.out.println("before:commodity.id=" + commodity.getId());
        short beforeId = commodity.getId();
        int affetRow = this.commodityMap.insertGetId(commodity); // 返回的是影响的行数
        System.out.println("after:commodity.id=" + commodity.getId());
        if (commodity.getId() <= beforeId) {
            throw new RuntimeException(
                    "调用commodityMap.insertGetId插入数据库返回主键id有问题，返回主键id=" + beforeId + "，原id=" + commodity.getId());
        }
        // 将原纪录的parentid修改为新纪录的id
        Commodity commodityOld = new Commodity();
        commodityOld.setId(beforeId);
        commodityOld.setParentid(commodity.getId());
        commodityOld.setIsEditor((short) 1);
        int matchNum = this.commodityMap.updateByPrimaryKeySelective(commodityOld);
        if (matchNum != 1) {
            throw new RuntimeException("调用commodityMap.updateByPrimaryKeySelective更新数据失败");
        }
    }

    @Override
    public List<Commodity> queryForNotBuy() {
        List<Commodity> commodities = commodityMap.selectForNotBuy();
        return commodities;
    }

    @Override
    public List<Commodity> CommodityBought() {
        List<Commodity> commodities = commodityMap.CommodityBought();
        return commodities;
    }

}
