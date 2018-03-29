package com.bean;

public class OnlineForSale{

    private Integer SellerNum;

    private Commodity commodity;
    
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getSellerNum() {
        return SellerNum;
    }

    public void setSellerNum(Integer sellerNum) {
        SellerNum = sellerNum;
    }
}
