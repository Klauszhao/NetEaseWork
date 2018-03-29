<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>java</title>
<link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
	<div class="n-head">
		<div class="g-doc f-cb">
			<div class="user">
				买家你好，<span class="name">buyer</span>！<a href="/ContentSales/login">[退出]</a>
			</div>
			<ul class="nav">
				<li><a href="/ContentSales/queryForShow">首页</a></li>             
				<li><a href="/ContentSales/accountShow">账务</a></li>
                <li><a href="/ContentSales/cartShow">购物车</a></li>
			</ul>
		</div>
	</div>
	<div class="g-doc">
		<div class="m-tab m-tab-fw m-tab-simple f-cb">
			<div class="tab">
				<ul>
					<li><a href="/ContentSales/queryForShow">所有内容</a></li>
					<li class="z-sel"><a href="/ContentSales/queryForNotBuy">未购买的内容</a></li>
				</ul>
			</div>
		</div>
		<div class="n-plist">
			<ul class="f-cb" id="plist">
				<c:forEach items="${commodityMap}" var="Commodity">
					<li id="p-${Commodity.id}"><a href="/ContentSales/CommodityDetailForBuy?id=${Commodity.id}" class="link">
							<div class="img">
								<img src="${Commodity.url}" alt="${Commodity.title}">
							</div>
							<h3>${Commodity.title}</h3>
							<div class="price">
								<span class="v-unit">¥</span><span class="v-value">${Commodity.price}</span>
							</div> 
					</a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="n-foot">
		<p>
			版权所有：<a href="https://www.kaola.com">网易考拉</a>
		</p>
	</div>
	<script type="text/javascript" src="JS/global.js"></script>
	<script type="text/javascript" src="JS/pageIndex.js"></script>
</body>
</html>