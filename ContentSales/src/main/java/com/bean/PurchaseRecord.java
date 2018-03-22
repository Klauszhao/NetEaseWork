package com.bean;

import java.util.Date;
import java.util.List;

public class PurchaseRecord {
    private Short id;

    private Short userId;

    private Short commodityId;

    private Short commodityNum;

    private Commodity commodity;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	private Date createTime;

    private Date updateTime;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    public Short getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Short commodityId) {
        this.commodityId = commodityId;
    }

    public Short getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Short commodityNum) {
        this.commodityNum = commodityNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}