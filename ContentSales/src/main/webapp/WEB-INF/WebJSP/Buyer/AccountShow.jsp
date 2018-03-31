<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>java</title>
<link rel="stylesheet" href="CSS/style.css"/>
</head><body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
                    买家你好，<span class="name">buyer</span>！<a href="/ContentSales/quit">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/ContentSales/queryForShow">首页</a></li>
            <li><a href="/ContentSales/accountShow">账务</a></li>
            <li><a href="/ContentSales/cartShow">购物车</a></li>
        </ul>
    </div>
</div><div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col/><col class="num"/><col/><col class="price"/><col/></colgroup>
        <thead>
            <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买数量</th><th>购买价格</th></tr>
        </thead>
        <tbody>
         <c:forEach items="${ccountMap}" var="accountRecord">
            <tr>
                <td><a href="/ContentSales/CommodityDetailForBuy?id=${accountRecord.commodityId }"><img src="${accountRecord.url}" alt=""></a></td>
                <td><h4><a href="/ContentSales/CommodityDetailForBuy?id=${accountRecord.commodityId }">${accountRecord.title }</a></h4></td>
                <td><span class="v-time">${accountRecord.createtime }</span></td>
                <td><span class="v-num">${accountRecord.num }</span></td>
                <td><span class="v-unit">¥</span><span class="value">${accountRecord.price}</span></td>
            </tr>
                
          </c:forEach>
          
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4"><div class="total">总计：</div></td>
                <td><span class="v-unit">¥</span><span class="value">${totalPrice}</span></td>
            </tr>
        </tfoot>
    </table>
</div>
<div class="n-foot">
    版权所有：<a href="https://www.kaola.com">网易考拉</a>
</div></body>
</html>