package com.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bean.Commodity;

public interface CommodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insertGetId(Commodity record);
    
    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer id);

    List<Commodity> selectForShow(@Param("isDeleted")short isDeleted,@Param("isEditor")short isEditor);
    
    List<Commodity> selectForNotBuy();
    
    List<Commodity> CommodityBought();
    
    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);
}