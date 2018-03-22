package com.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonDate {

    private static SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static void main(String[] args) {
        TranDate(new Date());
    }
    public static String TranDate(Date createtime){
        String daString=dateFormat.format(createtime);
        return daString;
    }
}
