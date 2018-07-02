package com.netease.service;

import java.util.List;

import com.bean.Commodity;
import com.common.PageBean;

public interface CommodityService {

    public int deleteById(Commodity commodity);
	public boolean delete(Commodity commodity);
	
	public String insert(Commodity commodity);
	
	public String update(Commodity commodity);
	public void updateByPrimaryId(Commodity commodity);
	
	public abstract boolean query(String deptName);
	
	/**
	 * 查询数据中的所有数据
	 * 主要根据字段is_delete和is_editor两个字段来对数据做筛选
	 * 将删除和没有被修改的记录展示出来
	 * @return
	 */
	public List<Commodity> queryForAll();
	/**
	 * 
	 * 查询出没有被购买的商品
	 *
	 * @return
	 * @author zhaonan
	 * @since 2018年3月20日
	 */
	public List<Commodity> queryForNotBuy();
	/**
	 * 
	 * 查询出已经被购买的商品
	 *
	 * @return
	 * @author zhaonan
	 * @since 2018年3月21日
	 */
	public List<Commodity> CommodityBought();
	/**
	 * 根据id来查询一条记录
	 * @param id
	 * @return
	 */
	public Commodity queryById(Integer id);
	
	/**   
	 * 分页查询     
	 * @param pageSize  每页显示多少记录   
	 * @param currentPage 当前页   
	 * @return 封装了分页信息的bean   
	 */    
	public PageBean queryForPage(String hql,int pageSize,int page); 
}
