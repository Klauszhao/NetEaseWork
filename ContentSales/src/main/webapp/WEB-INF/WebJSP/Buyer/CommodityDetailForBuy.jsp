<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品详情</title>
<link rel="stylesheet" href="CSS/style.css" />
</head>
<body>
	<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
	<div class="n-head">
		<div class="g-doc f-cb">
			<div class="user">
				买家：<span class="name">buyer</span>，你好！<a href="/ContentSales/quit">[退出]</a>
			</div>
			<ul class="nav">
				<li><a href="/ContentSales/queryForShow">首页</a></li>
				<li><a href="/ContentSales/accountShow">账务</a></li>
                <li><a href="/ContentSales/cartShow">购物车</a></li>
			</ul>
		</div>
	</div>
	<div class="g-doc">
		<div class="n-show f-cb" id="showContent">
				<div class="img">
					<img src="${goodsForOnline.url}" alt="">
				</div>
				<div class="cnt">
					<h2>${goodsForOnline.title}</h2>
					<p class="summary">${goodsForOnline.summary}</p>
					<div class="price">
						<span class="v-unit">¥</span><span class="v-value">${goodsForOnline.price}</span>
					</div>
					<div class="num">
						购买数量： 
						<span id="plusNum" class="lessNum"><a>-</a></span>
						<span id="allNum" class="totalNum" >${goodsForOnline.num}</span> 
						<span id="addNum" class="moreNum"><a>+</a></span>
					</div>
					<input type="hidden" id="commodityId" value="${goodsForOnline.commodityId}">
                    <div class="oprt f-cb">
						<button id="buyCommodity" class="u-btn u-btn-primary" >立即购买</button>
						<button id="addCart" class="u-btn u-btn-primary">加入购物车</button>
					</div>
				</div>
		</div>
		<div class="m-tab m-tab-fw m-tab-simple f-cb">
			<h2>详细信息</h2>
		</div>
		<div class="n-detail">${goodsForOnline.content}</div>
	</div>
	<div class="n-foot">
		<p>
			版权所有：<a href="https://www.kaola.com">网易考拉</a>
		</p>
	</div>
	<script type="text/javascript" src="JS/global.js"></script>
	<script type="text/javascript" src="JS/pageShow.js"></script>
	
	<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
</body>
</html>