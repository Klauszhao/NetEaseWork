package com.netease.service;

import java.util.List;

import com.bean.Goods;
import com.common.PageBean;

public interface PurchaseGoodsService {


	public String insert(Goods good);


	public void updateByPrimaryId(Goods good);

	public abstract boolean query(String deptName);

	/**
	 * 查询数据中的所有数据 主要根据字段is_delete和is_editor两个字段来对数据做筛选 将删除和没有被修改的记录展示出来
	 * 
	 * @return
	 */
	public List<Goods> queryForAll();

	/**
	 * 根据id来查询一条记录
	 * 
	 * @param id
	 * @return
	 */
	public Goods queryById(Integer id);

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
}
