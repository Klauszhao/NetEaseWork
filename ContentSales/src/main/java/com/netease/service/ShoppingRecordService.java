package com.netease.service;

import java.util.List;

import com.bean.CartOnline;
import com.bean.PurchaseRecord;
import com.bean.ShoppingRecord;
import com.common.PageBean;

public interface ShoppingRecordService {

    public boolean delete(ShoppingRecord shoppingRecord);

    public String insert(ShoppingRecord shoppingRecord);

    public String update(ShoppingRecord shoppingRecord);

    public void updateByPrimaryId(ShoppingRecord shoppingRecord);

    public abstract boolean query(String deptName);

    /**
     * 查询数据中的所有数据 主要根据字段is_delete和is_editor两个字段来对数据做筛选 将删除和没有被修改的记录展示出来
     * 
     * @return
     */
    public List<ShoppingRecord> queryForAll();

    /**
     * 根据id来查询一条记录
     * 
     * @param id
     * @return
     */
    public ShoppingRecord queryById(Integer id);

    /**
     * 分页查询
     * 
     * @param pageSize 每页显示多少记录
     * @param currentPage 当前页
     * @return 封装了分页信息的bean
     */
    public PageBean queryForPage(String hql, int pageSize, int page);
    
}
