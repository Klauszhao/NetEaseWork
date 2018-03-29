<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/style.css"/>
<title>登录</title>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div><div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
                    请<a href="login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/">首页</a></li>
        </ul>
    </div>
</div>
<s:form class="m-form m-form-ht n-login" id="loginForm" onsubmit="return false;" autocomplete="off" action="Login">
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
         <!--    <input class="u-ipt" name="userName" autofocus/> -->
           <s:textfield class="u-ipt" name="username" label="username"/>  
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
       <!--      <input class="u-ipt" type="password" name="password"/> -->
            
           <s:textfield class="u-ipt" name="password" label="password"/> 
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <s:submit type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block"/>
        </div>
    </div>
</s:form>
<div class="n-foot">
    <p>版权所有：<a href="https://www.kaola.com">网易考拉</a></p>
</div>
<script type="text/javascript" src="JS/md5.js"></script>
<script type="text/javascript" src="JS/global.js"></script>
<script type="text/javascript" src="JS/pageLogin.js"></script>
</body>
</html>