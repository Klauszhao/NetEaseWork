package com.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.bean.OnlineForSale;
import com.bean.PurchaseRecord;

public interface PurchaseRecordMapper {
    int deleteByPrimaryKey(Short id);

    int insert(PurchaseRecord record);

    int insertSelective(PurchaseRecord record);

    int insertBatch(@Param("listRecord") List<PurchaseRecord> purchaseRecordList);
    
    PurchaseRecord selectByPrimaryKey(Short id);

    List<PurchaseRecord> selectByCommodityId (Short id);
    
    int updateByPrimaryKeySelective(PurchaseRecord record);

    int updateByPrimaryKey(PurchaseRecord record);
    
    List<PurchaseRecord> selectForAll();
    
    OnlineForSale selectNumById(Short id);
}