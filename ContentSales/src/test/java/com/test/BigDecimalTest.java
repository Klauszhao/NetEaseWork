package com.test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest {
    public static void main(String[] args) {
        TestTwo();
        //TestOne();
    }

    public static void TestOne() {
        BigDecimal big = new BigDecimal(100);
        System.out.println("输出数字：big=" + big.toString());
        System.out.println("加数字5.22之后结果：big=" + big.add(new BigDecimal(5.22)));
        System.out.println("加数字5.22之后结果：big=" + big.add(new BigDecimal(5.22)).toString());
    }

    /**
     * 指定小数点输出 TODO
     *
     * @author zhaonan
     * @since 2018年3月13日
     */
    public static void TestTwo() {

        BigDecimal big = new BigDecimal(100);
        String bigtostring=big.setScale(2, BigDecimal.ROUND_DOWN).toString(); // 直接截取多少位小数点数字，后面的都不要
        System.out.println("big.toString:" + bigtostring);

        big=new BigDecimal(100);
        big=big.add(new BigDecimal(100));
        System.out.println("big="+big.toString());  
    }
    /**
     * 利用 DecimalFormat对数据进行格式化
     * TODO
     *
     * @author zhaonan
     * @since 2018年3月13日
     */
    public static void TestThr(){
        DecimalFormat df = new DecimalFormat();  
        double data = 1234.56789; //格式化之前的数字  
      
        //1、定义要显示的数字的格式（这种方式会四舍五入）  
        String style = "0.0";  
        df.applyPattern(style);  
        System.out.println("1-->" + df.format(data));  //1234.6  
      
        //2、在格式后添加诸如单位等字符  
        style = "00000.000 kg";  
        df.applyPattern(style);  
        System.out.println("2-->" + df.format(data));  //01234.568 kg  
      
      
        //3、 模式中的"#"表示如果该位存在字符，则显示字符，如果不存在，则不显示。  
        style = "##000.000 kg";  
        df.applyPattern(style);  
        System.out.println("3-->" + df.format(data));  //1234.568 kg  
      
        //4、 模式中的"-"表示输出为负数，要放在最前面  
        style = "-000.000";  
        df.applyPattern(style);  
        System.out.println("4-->" + df.format(data)); //-1234.568  
      
      
        //5、 模式中的","在数字中添加逗号，方便读数字  
        style = "-0,000.0#";  
        df.applyPattern(style);  
        System.out.println("5-->" + df.format(data));  //5-->-1,234.57  
      
      
        //6、模式中的"E"表示输出为指数，"E"之前的字符串是底数的格式，  
        // "E"之后的是字符串是指数的格式  
        style = "0.00E000";  
        df.applyPattern(style);  
        System.out.println("6-->" + df.format(data));  //6-->1.23E003  
      
      
        //7、 模式中的"%"表示乘以100并显示为百分数，要放在最后。  
        style = "0.00%";  
        df.applyPattern(style);  
        System.out.println("7-->" + df.format(data));  //7-->123456.79%  
      
      
        //8、 模式中的"\u2030"表示乘以1000并显示为千分数，要放在最后。  
        style = "0.00\u2030";  
        //在构造函数中设置数字格式  
        DecimalFormat df1 = new DecimalFormat(style);  
        //df.applyPattern(style);  
        System.out.println("8-->" + df1.format(data));  //8-->1234567.89‰
    }
}
