<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<head>

<title>发布商品</title>

<meta name="viewport" content="width=device-width,initial-scale=1">

<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="keywords" content="HTML,CSS,XML,JavaScript">
<meta charset="utf-8" />

<link rel="stylesheet" type="text/css"
	href="<%=basePath %>CSS/style_PublishGoods.css" />
<link rel="stylesheet" href="<%=basePath %>CSS/style.css" />
<script type="text/javascript" src="<%=basePath %>JS/PublishGoods.js"></script>
<script type="text/javascript" src="<%=basePath %>JS/global.js"></script>
<script type="text/javascript" src="<%=basePath %>JS/pageLogin.js"></script>

</head>

<body>
	<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
	<div class="n-head">
		<div class="g-doc f-cb">
			<div class="user">
				请<a href="/ContentSales/login">[登录]</a>
			</div>
			<ul class="nav">
				<li><a href="/ContentSales/show">首页</a></li>
			</ul>
		</div>
	</div>
	<div class="contact">
		<center>
			<img name="showimg" id="showimg" src="" height="300" width='200'
				style="display: none;" alt="预览图片" />
		</center>
		<form name="form1" method="post" action="PublishCommodity_InsertData">

			<ul>
				<li><label>上传图片：</label> <input name="upfile" type="file"
					id="upfile" size="40"
					onchange="viewmypic(showimg,this.form.upfile);"
					style="font-size: 20px" /></li>

				<li><label>标题：</label> <input id="title" type="text"
					name="title" placeholder="请输入标题" onblur="checkna()" required /><span
					class="tips" id="divname">长度1~15个字符</span></li>

				<li><label>摘要：</label> <textarea id="summary" name="summary"
						path="address" rows="5" cols="30" style="height: 70px"
						placeholder="请输入摘要" required></textarea> <span class="tips"
					id="summary">长度1~120个字符</span></li>

				<li><label>正文：</label> <textarea id="content" name="content"
						path="address" rows="6" cols="30"
						style="height: 70px, width=350px" placeholder="请输入正文" required></textarea>
					<span class="tips" id="content">长度500个字符内</span></li>

				<li><label>价格：</label> <input id="price" type="text"
					name="price" placeholder="请输入价格" onblur="CheckNumLegal()" required /><span
					class="tips" id="divnum"></span></li>

				<li><b class="btn"> <input type="submit" name="submit"
						value="提交" style='font-size: 20px; width: 84px; height: 36px' />&nbsp;

						<input type="reset" value="取消"
						style='font-size: 20px; width: 84px; height: 36px' />

				</b></li>

			</ul>

		</form>

	</div>

</body>
<div class="n-foot">
	<p>
		版权所有：<a href="https://www.kaola.com">网易考拉</a>
	</p>
</div>
</html>
