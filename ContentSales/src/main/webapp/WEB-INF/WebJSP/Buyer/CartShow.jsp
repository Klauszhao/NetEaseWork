<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>java</title>
<link rel="stylesheet" href="CSS/style.css" />

<script type="text/javascript" language="javascript"> 

	//后台servlet中已经向request中set了一个List集合对象,名为mulst 
</script>
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
			<h2>购物车内容</h2>
		</div>
		<table class="m-table m-table-row n-table g-b3" id="newTable">
			<colgroup>
				<col class="img" />
				<col />
				<col class="time" />
				<col />
				<col class="num" />
				<col />
				<col class="price" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th>内容图片</th>
					<th>内容名称</th>
					<th>购买数量</th>
					<th>价格</th>
				</tr>
			</thead>
				<c:forEach items="${cartOnlineMap}" var="CartOnline">
					<tr>	
					<td><a href="/ContentSales/CommodityDetailForBuy?id=${CartOnline.commodityId }"><img src="${CartOnline.url }" alt=""></a></td>
	                <td><h4><a href="/ContentSales/CommodityDetailForBuy?id=${CartOnline.commodityId }">${CartOnline.title }</a></h4></td>
	                <td>
	                        <span id="plusNum" class="lessNum">-</span> 
	                        <span id="allNum" class="totalNum" name="commodityNum"> ${CartOnline.num} </span> 
	                        <span id="addNum" class="moreNum">+ </span>
	                        <input type="hidden" name="commodityId" value="${CartOnline.commodityId}">
	                        <input type="hidden" name="shoppingRecorfId" value="${CartOnline.id}">
	                </td>
	                <td><span class="v-unit">¥</span><span class="value">${CartOnline.price}</span></td>
					</tr>		
				</c:forEach>
		</table>
		<div id="act-btn">
            <button class="u-btn u-btn-primary" id="back">退出</button>
            <button class="u-btn u-btn-primary" id="Account">购买</button>
        </div>
	</div>
	<div class="n-foot">
		<p>
			版权所有：<a href="https://www.kaola.com">网易考拉</a>
		</p>
	</div>
	<script type="text/javascript" src="JS/global.js"></script>
    <script type="text/javascript" src="JS/settleAccount.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
</body>
</html>