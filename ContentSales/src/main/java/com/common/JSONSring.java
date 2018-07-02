package com.common;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class JSONSring {

    /**
     * 
     * ajax 返回值，正确的返回值
     *
     * @return
     * @author zhaonan
     * @since 2018年3月28日
     */
    public static String successString() {
        // 验证用户名和密码的时候如果没有发现此用户，可以返回一个error json错误给ajax，这样就可以使其跳转到ajax的error方法里。
        Map<String, String> map = new HashMap<String, String>();
        // 上面的map不是json当然是不可以用于返回的，于是呢我们要把它搞成json字符串，你也可以自己去拼json字符串。
        map.put("check_type", "success");
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }

    /**
     * 
     * AJAX返回值，错误的返回值
     *
     * @return
     * @author zhaonan
     * @since 2018年3月28日
     */
    public static String errorString() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("check_type", "error");
        JSONObject json = JSONObject.fromObject(map);
        return json.toString();
    }
}
