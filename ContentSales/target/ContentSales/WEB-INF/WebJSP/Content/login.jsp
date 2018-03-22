<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
<link rel="stylesheet" href="CSS/style.css"/>
<title>登录</title>

<script type="text/javascript" language="javascript">
 var boolstoreName=false;
 var boolpassWord=false;
 function check(){
	 var un = document.getElementById("username").value;
	 var pwd = document.getElementById("password").value;
	 if(un.length==0&&pwd.length==0){
	        alert("用户名和密码都为空");
	        return;
	    }
	 if(un.length==0){
	        alert("用户名为空");
	        return;
	    }
	 if(pwd.length==0){
	        alert("密码为空");
	        return;
	    }
	 var pwd_md5=hex_md5(pwd);
	 $.ajax({
         cache: false,
         async: false,
         type: "POST",
     dataType: "json",
         data: {  
			   "username": un,  
		        "password": pwd_md5  
		    },
         url: "Check",
     success: function(data) {
    	 
/*     	 alert("success方法:"+data.check_type);
    	 var backdata=JSON.parse(data); //传回的是json字符串，要先把它转换成js中的类对象，
    	 alert("success"); */
    	 //alert("success方法:"+backdata.check_type);
    	 
             if("ok"==data.check_type) {
                 //alert("用户名和密码不匹配！");
            	 //divpassword.innerHTML='<font  color="red" style="text-align:center">用户名和密码不匹配！</font>';
                 boolpassWord = true;   
             }else{
            	 //alert("11success方法:"+backdata.check_type);
            	 divpassword.innerHTML='<font  color="red" style="text-align:center">用户名和密码不匹配！</font>';
            	 boolpassWord=false;
             }
         },
     error : function(XMLHttpRequest,textStatus,errorThrown) {
    	 //alert("error方法:"+backdata.check_type);
    	 //alert("error方法");
    	 
    	 divpassword.innerHTML='<font  color="red" style="text-align:center">用户名和密码---不匹配！</font>';
    	 boolpassWord = false;   
         }
         
   }); 
   if(boolpassWord==false){
     return;
   }else{
	 document.getElementById("password").value=pwd_md5;
     document.loginForm.submit();
    }
 }
  </script>
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
<form:form method="POST" modelAttribute="user" action="loginAction"  class="m-form m-form-ht n-login" id="loginForm" name="loginForm" onsubmit="return false;" autocomplete="off" >
    <div class="fmitem">
        <label class="fmlab">用户名：</label>
        <div class="fmipt">
            <form:input class="u-ipt" type="username" name="username" id="username" path="username"/>
        </div>
    </div>
    <div class="fmitem">
        <label class="fmlab">密码：</label>
        <div class="fmipt">
            <form:input id="password" class="u-ipt" type="password" name="password" path="password"/>
            
            <span class="tips" id="divpassword"></span>
        </div>
    </div>
    <div class="fmitem fmitem-nolab fmitem-btn">
        <div class="fmipt">
            <button type="submit" class="u-btn u-btn-primary u-btn-lg u-btn-block" onClick="check()">登 录</button>
        </div>
    </div>
</form:form>
<div class="n-foot">
    <p>版权所有：<a href="https://www.kaola.com">网易考拉</a></p>
</div>
<script type="text/javascript" src="JS/md5.js"></script>
<script type="text/javascript" src="JS/global.js"></script>
<script type="text/javascript" src="JS/pageLogin.js"></script>
</body>
</html>