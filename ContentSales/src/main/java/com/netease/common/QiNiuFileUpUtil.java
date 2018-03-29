package com.netease.common;


import com.qiniu.util.Auth;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;

public class QiNiuFileUpUtil {

	/** 基本配置-从七牛管理后台拿到 */
	// 设置好账号的ACCESS_KEY和SECRET_KEY
	public String ACCESS_KEY = "9esKLdHh3wMjlD5QHt8_FkVa2XF42lgs8KVCOPVj";
	public String SECRET_KEY = "pksDYci9-hs5D7FH6aF96_e_gNP9e_ul-GwH_Nl2";
	// 要上传的空间名--//对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）
	public String bucketname = "neteasework";
	public Auth auth = null;
	public Map<String, Object> data;
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public QiNiuFileUpUtil() {
		// 密钥配置
	    System.out.println("初始化上传变量");
		auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	}

	public static void main(String args[]) throws IOException {
		QiNiuFileUpUtil qiniuTest=new QiNiuFileUpUtil();
		System.out.println("qiniuTest.getUpToken()"+qiniuTest.getUpToken());
		
		
//		
//		// 上传文件的路径
//		String FilePath = "E:\\NetEaseWork\\sql结构.sql";
//		// 上传到七牛后保存的文件名 访问为：http://p3d53vnjf.bkt.clouddn.com/“key”
//		String key = "aabb/sql结构.sql"; // 此处可以设置额外的文件夹，用来对其进行扩展，防止出现重复文件，
//		// 本文建议利用文件名作为key，或者适当对key进行延伸
//		int start=FilePath.lastIndexOf("\\");
//		key=FilePath.substring(start+1);
//		//System.out.println("key:"+key);
//		new QiNiuFileUpUtil().upload(FilePath, key);
	}

	// 简单上传，使用默认策略，只需要设置上传的空间名就可以了
	public String getUpToken() {
		return auth.uploadToken(bucketname);
	}

	public Object getQiniuUptoken() throws Exception {  
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        String uptoken=auth.uploadToken(bucketname);
        System.out.println("uptoken:"+uptoken);
        data.put("uptoken", uptoken);  
        return data;
    }  
	
	/**
	 * 返回外链
	 * @param FilePath
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String upload(String FilePath, String key) throws IOException {
		// 正常一个外链的前一部分跟创建的空间有关，基本一个空间的外链头都是固定的
		String OutsideChain="http://p3d53vnjf.bkt.clouddn.com/";
		/* 指定保存到七牛的文件名--同名上传不会报错， 会覆盖掉 */
		/**
		 * {"hash":"FrQF5eX_kNsNKwgGNeJ4TbBA0Xzr","key":"aa1.jpg"} 正常返回
		 * key为七牛空间地址 http:/xxxx.com/aa1.jpg
		 */
		// 创建上传对象
		UploadManager uploadManager = new UploadManager(new Configuration());
		try {
			// 调用put方法上传
			Response res = uploadManager.put(FilePath, key, getUpToken());
			// 打印返回的信息
			System.out.println(res.bodyString());
			System.out.println(res.statusCode);  // 200为上传成功
		} catch (QiniuException e) {
			Response r = e.response;
			// 请求失败时打印的异常的信息
			System.out.println(r.toString());
			try {
				// 响应的文本信息
				System.out.println(r.bodyString());
			} catch (QiniuException e1) {
				// ignore
			}
		}
		return OutsideChain+key;
	}
}
