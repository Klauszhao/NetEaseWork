package com.bean;

import java.util.Date;

public class ShoppingRecord {
    private Short id;

    private Short commodityId;

    private Short userId;
    
    private Short num;

    private Short isSettlement;

    private Date createtime;
    
    private Commodity commodity;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Short commodityId) {
        this.commodityId = commodityId;
    }

    public Short getNum() {
        return num;
    }

    public void setNum(Short num) {
        this.num = num;
    }

    public Short getIsSettlement() {
        return isSettlement;
    }

    public void setIsSettlement(Short isSettlement) {
        this.isSettlement = isSettlement;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
    
    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }
}