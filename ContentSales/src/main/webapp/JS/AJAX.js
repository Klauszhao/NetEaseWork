function createAjaxObj() {
	var req;
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else {
		req = new ActiveXObject("Msxml2.XMLHTTP"); // ie
	}
	return req;
}

function sendAjaxReq() {
	var req = createAjaxObj();
	req.open("get", "myajax.do?method=test1&a=" + Math.random());
	req.setRequestHeader("accept", "application/json");
	req.onreadystatechange = function() {
		alert(req.responseText);
		// eval("var result="+req.responseText);
		// document.getElementById("div1").innerHTML=result[0].uname;
	}
	req.send(null);
}
function buySubmit(id,num) {
	var boolean;
	$.ajax({
		cache : false,
		async : false,
		type : "POST",
		dataType : "json",
		data : {
			"commodityTd" : id,
			"num" : num
		},
		url : "/ContentSales/buyCommodity",
		success : function(data) {
			if ("ok" == data.check_type) {
				boolean= true;
			} else {
				boolean= false;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			boolean=false;
		}
	});
	return boolean;
};