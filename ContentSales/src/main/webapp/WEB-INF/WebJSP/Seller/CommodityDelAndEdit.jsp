<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8"/>
<title>java</title>
<link rel="stylesheet" href="CSS/style.css"/>
<script type="text/javascript">
var $id = function(id){
    return "string" == typeof id ? document.getElementById(id) : id;
};
function Submit(){
	var id = $id('commodityId').value;
	var boolString=false;

	$.ajax({
        cache : false,
        async : false,
        type : "POST",
        dataType : "json",
        data : {
            "id" : id
        },
        url : "/ContentSales/deleteCommodity",
        success : function(data) {
            if ("success" == data.check_type) {
            	boolString= true;
            } else {
            	boolString= false;
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert("error");
        	boolString=false;
        }
    });
	// 根据ajax调用成功来调用不同的界面
	var loading = new Loading();
    var layer = new Layer();
    if (boolString) {
        loading.show();
        loading.result('删除成功',function(){location.href = '/ContentSales/QueryForSaler';});
    } else {
        loading.show();
        loading.result('删除失败');
    }
	
	
}
</script>
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
        <div class="img"><img src="${online.url}" alt="" ></div>
        <div class="cnt">
            <h2>${online.title}</h2>
            <p class="summary">${online.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${online.price}</span>
            </div>
            <div class="num">出售数量：<span class="totalNum" id="allNum">0</span></div>
            <div class="oprt f-cb">
                <a href="/ContentSales/editCommodity?id=${online.commodityId}" class="u-btn u-btn-primary">编辑</a>
                <button id="deleButton" class="u-btn u-btn-primary" onclick="Submit()">删除</button>
            </div>
            <input type="hidden" id="commodityId" value="${online.commodityId}">
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${online.content}
    </div>
</div>
<div class="n-foot">
    <p>版权所有：<a href="https://www.kaola.com">网易考拉</a></p>
</div><script type="text/javascript" src="JS/global.js"></script>
<script type="text/javascript" src="JS/pageShow.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
</body>
</html>