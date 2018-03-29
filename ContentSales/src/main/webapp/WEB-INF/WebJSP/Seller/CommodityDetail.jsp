<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8"/>
<title>java</title>
<link rel="stylesheet" href="CSS/style.css"/>
</head><body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="g-doc f-cb">
         <div class="user">
                       卖家你好，<span class="name">seller</span>！<a href="/ContentSales/login">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/ContentSales/QueryForSaler">首页</a></li>
            <li><a href="/ContentSales/PublishCommodity">发布</a></li>
        </ul>
    </div>
</div><div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${commodity.url}" alt="" ></div>
        <div class="cnt">
            <h2>${commodity.title}</h2>
            <p class="summary">${commodity.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${commodity.price}</span>
            </div>
            <div class="num">出售数量：<span class="totalNum" id="allNum">${commodity.buyNum}</span></div>
            <div class="oprt f-cb">
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${content}
    </div>
</div>
<div class="n-foot">
    <p>版权所有：<a href="https://www.kaola.com">网易考拉</a></p>
</div><script type="text/javascript" src="JS/global.js"></script>
<script type="text/javascript" src="JS/pageShow.js"></script>
</body>
</html>