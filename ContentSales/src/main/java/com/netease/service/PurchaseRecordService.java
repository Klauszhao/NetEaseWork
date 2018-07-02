package com.netease.service;

import java.util.List;

import com.bean.Commodity;
import com.bean.Goods;
import com.bean.OnlineForSale;
import com.bean.PurchaseRecord;
import com.bean.ShoppingRecord;
import com.common.PageBean;

public interface PurchaseRecordService {


	public String insert(PurchaseRecord purchaseRecord);


	public void updateByPrimaryId(PurchaseRecord purchaseRecord);

	public abstract boolean query(String deptName);

	/**
	 * 查询数据中的所有数据 主要根据字段is_delete和is_editor两个字段来对数据做筛选 将删除和没有被修改的记录展示出来
	 * 
	 * @return
	 */
	public List<PurchaseRecord> queryForAll();

	/**
	 * 根据id来查询一条记录
	 * 
	 * @param id，表主键id
	 * @return
	 */
	public PurchaseRecord queryById(Integer id);
	/**
	 * 
	 * 商品id查询
	 *
	 * @param id
	 * @return
	 * @author zhaonan
	 * @since 2018年3月28日
	 */
	public List<PurchaseRecord> queryByCommodityId(Integer id);
	/**
	 * 分页查询
	 * 
	 * @param pageSize
	 *            每页显示多少记录
	 * @param currentPage
	 *            当前页
	 * @return 封装了分页信息的bean
	 */
	public PageBean queryForPage(String hql, int pageSize, int page);
	/**
	 * 
	 * 根据商品id去查询该商品被购买的数量
	 *
	 * @param id
	 * @return
	 * @author zhaonan
	 * @since 2018年3月23日
	 */
	public OnlineForSale SumNumById(Short id);
	
	/**
     * 
     * 购物车中的商品被点击购买，先将购物车数据更新，再在购买表中增加记录
     *
     * @return
     * @author zhaonan
     * @since 2018年3月20日
     */
    public String cartToPurchase(List<PurchaseRecord> purchaseRecordList,List<ShoppingRecord> shoppingRecordList);
}
