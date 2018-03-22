<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>商品发布</title>
<link rel="stylesheet" href="CSS/style.css" />
<script>
function Submit(){ 
	//document.commodityform.submit();
	document.getElementById("commodityform").submit();
}
</script>
</head>
<body>
	<div class="n-head">
		<div class="g-doc f-cb">
			<div class="user">
				卖家你好，<span class="name">seller</span>！ <a href="/ContentSales/login">[退出]</a>
			</div>
			<ul class="nav">
				<li><a href="/ContentSales/queryForSaler">首页</a></li>
				<li><a href="/ContentSales/publish">发布</a></li>
			</ul>
		</div>
	</div>
	<div class="g-doc">
		<div class="m-tab m-tab-fw m-tab-simple f-cb">
			<h2>内容发布</h2>
		</div>
		<div class="n-public">
			<form:form id="commodityform" name="commodityform" method="POST"
				action="commoditypublish" modelAttribute="commodity"
				onsubmit="return false;" autocomplete="off" class="m-form m-form-ht">
				<div class="fmitem">
					<label class="fmlab">标题：</label>
					<div class="fmipt">
						<form:input class="u-ipt ipt" name="title" path="title"
							placeholder="2-80字符" />
					</div>
				</div>
				<div class="fmitem">
					<label class="fmlab">摘要：</label>
					<div class="fmipt">
						<form:input class="u-ipt ipt" path="summary" name="summary"
							placeholder="2-140字符" />
					</div>
				</div>
				<div class="fmitem">
					<label class="fmlab">图片：</label>
					<div class="fmipt">
						<form:input class="u-ipt ipt" name="url" path="url"
							placeholder="图片地址" />
					</div>
				</div>
				<div class="fmitem">
					<label class="fmlab">正文：</label>
					<div class="fmipt">
						<form:textarea class="u-ipt" name="content" path="content"
							rows="10" placeholder="2-1000个字符"></form:textarea>
					</div>
				</div>
				<div class="fmitem">
					<label class="fmlab">价格：</label>
					<div class="fmipt">
						<form:input class="u-ipt price" path="price" name="price" />
						元
					</div>
				</div>
				<div class="fmitem fmitem-nolab fmitem-btn">
					<div class="fmipt">
						<button id="btone" type="submit"
							class="u-btn u-btn-primary u-btn-lg" onClick="Submit()">发
							布</button>
					</div>
				</div>
			</form:form>
			<span class="imgpre"><img
				src="http://nec.netease.com/img/l/1.jpg" alt="" id="imgpre"></span>
		</div>
	</div>
	<div class="n-foot">
		<p>
			版权所有：<a href="https://www.kaola.com">网易考拉</a>
		</p>
	</div>
	<!-- <script type="text/javascript" src="JS/global.js"></script> 
<script type="text/javascript" src="JS/public.js"></script>  -->
</body>
</html>