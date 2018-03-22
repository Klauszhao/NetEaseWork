package com.netease.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.Commodity;
import com.bean.PurchaseRecord;
import com.bean.ShoppingRecord;
import com.common.PageBean;
import com.dao.PurchaseRecordMapper;
import com.dao.ShoppingRecordMapper;
import com.netease.service.PurchaseRecordService;
@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService{

	@Autowired
	private PurchaseRecordMapper purchaseRecordMap;

    @Autowired
    private ShoppingRecordMapper shoppingRecordMap;
    
	@Override
	public boolean query(String deptName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PurchaseRecord> queryForAll() {
		
		return this.purchaseRecordMap.selectForAll();
	}

	@Override
	public PageBean queryForPage(String hql, int pageSize, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(PurchaseRecord purchaseRecord) {
	    int falg=purchaseRecordMap.insert(purchaseRecord);
	    if(falg==1){
	        return "success";
	    }
		return "error";
	}

	@Override
	public void updateByPrimaryId(PurchaseRecord purchaseRecord) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PurchaseRecord queryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cartToPurchase(List<PurchaseRecord> purchaseRecordList,List<ShoppingRecord> shoppingRecordList) {
        // 1、更新购物车数据
        int matchNum=shoppingRecordMap.updateBatchByIdForCartToPur(shoppingRecordList);
        if(matchNum!=shoppingRecordList.size()){
            throw new RuntimeException("调用shoppingRecordMap.updateBatchByIdForCartToPur更新数据失败，更新数目不对。");
        }
        // 2、增加数据
        matchNum=purchaseRecordMap.insertBatch(purchaseRecordList);
        if(matchNum!=purchaseRecordList.size()){
            throw new RuntimeException("调用purchaseRecordMap.insertBatch插入数据失败，插入数据数目不对。");
        }
        return "success";
    }

}
