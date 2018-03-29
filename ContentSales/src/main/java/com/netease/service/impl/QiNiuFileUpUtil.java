package com.netease.service.impl;

import org.springframework.stereotype.Service;

import com.netease.service.FileUpUtil;
import com.qiniu.util.Auth;

@Service
public class QiNiuFileUpUtil implements FileUpUtil {
    /** 基本配置-从七牛管理后台拿到
     * 设置好账号的ACCESS_KEY和SECRET_KEY
     */
    public String ACCESS_KEY = "9esKLdHh3wMjlD5QHt8_FkVa2XF42lgs8KVCOPVj";

    public String SECRET_KEY = "pksDYci9-hs5D7FH6aF96_e_gNP9e_ul-GwH_Nl2";

    // 要上传的空间名--//对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）
    public String bucketname = "neteasework";

    @Override
    public String getUpToken() {
        // 密钥配置
        System.out.println("初始化上传变量");
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        return auth.uploadToken(bucketname);
    }

}
