<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <form:form id="Student" name="Student" method="POST"
                action="commoditypublish" modelAttribute="Student"
                onsubmit="return false;" autocomplete="off" class="m-form m-form-ht">
<form:radiobutton path="firstName" value="M" label="Male" />
<form:radiobutton path="lastName" value="F" label="Female" />
 </form:form>
        <center>
            <img name="showimg" id="showimg" src="" height="300" width='200'
                style="display: none;" alt="预览图片" />
        </center>
    <div id="container">
        <a class="btn btn-default btn-lg " id="pickfiles" href="#" >
            <span>选择文件</span>
        </a>
        
    </div>
    <input id="urlText" type="text" value=""></input> 
</body>

<script type="text/javascript" src="https://cdn.staticfile.org/jquery/2.2.1/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/moxie.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/plupload.dev.js"></script>
<script type="text/javascript" src="https://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.min.js"></script>

<link rel="stylesheet" type="text/css" href="CSS/style_PublishGoods.css" />
<link rel="stylesheet" href="CSS/style.css" />

<script type="text/javascript" src="JS/qiniuTest.js"></script>
<script type="text/javascript" src="JS/PublishGoods.js"></script>
</html>