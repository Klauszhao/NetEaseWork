package com.netease.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.CartOnline;
import com.bean.PurchaseRecord;
import com.bean.ShoppingRecord;
import com.common.PageBean;
import com.dao.PurchaseRecordMapper;
import com.dao.ShoppingRecordMapper;
import com.netease.service.ShoppingRecordService;

@Service
public class ShoppingRecordServiceImpl implements ShoppingRecordService{

    @Autowired
    private ShoppingRecordMapper shoppingRecordMap;
    
    @Override
    public boolean delete(ShoppingRecord shoppingRecord) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String insert(ShoppingRecord shoppingRecord) {
        if(shoppingRecordMap.insert(shoppingRecord)==1){
            return "success";
        }
        return "error";
    }

    @Override
    public String update(ShoppingRecord shoppingRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void updateByPrimaryId(ShoppingRecord shoppingRecord) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean query(String deptName) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<ShoppingRecord> queryForAll() {
        List<ShoppingRecord> shoppingRecordList= shoppingRecordMap.selectForAll((short) 0);
        return shoppingRecordList;
    }

    @Override
    public ShoppingRecord queryById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PageBean queryForPage(String hql, int pageSize, int page) {
        // TODO Auto-generated method stub
        return null;
    }

}
