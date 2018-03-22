<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"> 
function Submit(){
	var saveDataAry=[];
    var data1={"firstName":"test","lastName":"gz"};
    var data2={"firstName":"ququ","lastName":"gr"};
    saveDataAry.push(data1);
    saveDataAry.push(data2);
    $.ajax({
        type:"POST",
        url:"/ContentSales/TestForSubmit",
        dataType:"json",
        contentType:"application/json", // 指定这个协议很重要
        data:JSON.stringify(saveDataAry), //只有这一个参数，json格式，后台解析为实体，后台可以直接用   JSON.stringify(
        success:function(data){
        	console.log("-----success");
        }
    });
}
</script>



</head>
<body>
<h2>${message}</h2>

<button class="u-btn u-btn-primary" id="butt" onClick="Submit()">点击</button>

</body>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.js"></script>
</html>