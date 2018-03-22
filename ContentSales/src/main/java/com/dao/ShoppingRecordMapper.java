package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bean.ShoppingRecord;

public interface ShoppingRecordMapper {
    int deleteByPrimaryKey(Short id);

    int insert(ShoppingRecord record);

    int insertSelective(ShoppingRecord record);

    ShoppingRecord selectByPrimaryKey(Short id);
    
    List<ShoppingRecord> selectForAll(@Param("isSettlement")short isSettlement);

    int updateByPrimaryKeySelective(ShoppingRecord record);

    int updateByPrimaryKey(ShoppingRecord record);
    
    int updateBatchByIdForCartToPur(@Param("list") List<ShoppingRecord> shoppingRecordList);
}