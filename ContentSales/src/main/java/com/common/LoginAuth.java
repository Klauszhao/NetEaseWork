package com.common;

import java.util.HashSet;
import java.util.Set;
import com.google.common.collect.Sets;

public class LoginAuth {

    private HashSet<String> buySet=Sets.newHashSet();
    
    private HashSet<String> selSet=Sets.newHashSet();
    
    public LoginAuth(){
        buySet.add("accountShow");
        buySet.add("cartShow");
        buySet.add("CommodityDetailForBuy");
        buySet.add("queryForNotBuy");
        buySet.add("queryForShow");
        buySet.add("buyAuthError");
        
        selSet.add("UpdateSubmit");
        selSet.add("editAndDelCommodity");
        selSet.add("editCommodity");
        selSet.add("commodityDetail");
        selSet.add("SubmitCommodity");
        selSet.add("PublishCommodity");
        selSet.add("QueryForSaler");
        selSet.add("selAuthError");
    }
    

    public HashSet<String> getSelSet() {
        return selSet;
    }

    public void setSelSet(HashSet<String> selSet) {
        this.selSet = selSet;
    }

    public HashSet<String> getBuySet() {
        return buySet;
    }

    public void setBuySet(HashSet<String> buySet) {
        this.buySet = buySet;
    }
    
}
