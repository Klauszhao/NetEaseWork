function checkAndSubmit() {
	var bool_checkTitle = false;
	var bool_checkSum = false;
	var bool_CheckCon = false;
	var bool_CheckNumLegal = false;
	
	bool_checkTitle =checkTitle();
	bool_checkSum=checkSum();
	bool_CheckCon=checkCon();
	bool_CheckNumLegal = CheckNumLegal();
	if (bool_checkTitle && bool_checkSum && bool_CheckCon && bool_CheckNumLegal) {
		document.commodityform.submit();
	}
	return false;
}

function checkTitle() {
	na = commodityform.title.value;
	if (na.length < 2 || na.length > 80) {
		document.getElementById('divname').innerHTML = '<font class="tips_false">长度</font>';
		return false;
	} else {
		//document.getElementById('divname').innerHTML = '<font class="tips_true">输入正确</font>';
		return true;
	}
}

function checkSum() {
	na = commodityform.summary.value;
	if (na.length < 2 || na.length > 140) {
		document.getElementById('sumdivname').innerHTML = '<font class="tips_false">长度</font>';
		return false;
	} else {
		//document.getElementById('sumdivname').innerHTML = '<font class="tips_true">输入正确</font>';
		return true;
	}
}
function checkCon() {
	na = commodityform.content.value;
	if (na.length < 2 || na.length > 1000) {
		document.getElementById('condivname').innerHTML = '<font class="tips_false">长度</font>';
		return false;
	} else {
		//document.getElementById('condivname').innerHTML = '<font class="tips_true">输入正确</font>';
		return true;
	}
}

//判断数字是否合法
function CheckNumLegal() {
	var num = commodityform.price.value;
	var priceTips=document.getElementById('pricedivnum');
	if (num === "" || num == null) {
		priceTips.innerHTML = '<font class="tips_false">请输入数字</font>';
		return false;
	}
	if (isNaN(num) == true) {
		priceTips.innerHTML = '<font class="tips_false">请输入数字</font>';
		return false;
	}
	var point = num.toString().indexOf(".");
	if (point > -1) {
		var length = num.toString().split(".")[1].length;//数字小数点后有几位
		if (length > 2) {
			priceTips.innerHTML = '<font class="tips_false">数字保留两位小数点</font>';
			return false;
		} else {
		//	priceTips.innerHTML = '<font class="tips_true">输入正确</font>';
			return true;
		}
	}

//	divnum.innerHTML = '<font class="tips_true">输入正确</font>';
	return true;
}
//验证密码 

function checkpsd1() {

	psd1 = commodityform.yourpass.value;
	var flagZM = false;
	var flagSZ = false;
	var flagQT = false;
	if (psd1.length < 6 || psd1.length > 12) {
		summary.innerHTML = '<font class="tips_false">长度错误</font>';
	} else {
		for (i = 0; i < psd1.length; i++) {
			if ((psd1.charAt(i) >= 'A' && psd1.charAt(i) <= 'Z')
					|| (psd1.charAt(i) >= 'a' && psd1.charAt(i) <= 'z')) {
				flagZM = true;
			} else if (psd1.charAt(i) >= '0' && psd1.charAt(i) <= '9') {
				flagSZ = true;
			} else {
				flagQT = true;
			}
		}
		if (!flagZM || !flagSZ || flagQT) {
			summary.innerHTML = '<font class="tips_false">密码必须是字母数字的组合</font>';
		} else {
			summary.innerHTML = '<font class="tips_true">输入正确</font>';
		}
	}
}
//验证确认密码 
function checkpsd2() {
	if (commodityform.yourpass.value != commodityform.yourpass2.value) {
		content.innerHTML = '<font class="tips_false">您两次输入的密码不一样</font>';
	} else {
		content.innerHTML = '<font class="tips_true">输入正确</font>';
	}
}
//验证邮箱

function checkmail() {
	apos = commodityform.youremail.value.indexOf("@");
	dotpos = commodityform.youremail.value.lastIndexOf(".");
	if (apos < 1 || dotpos - apos < 2) {
		divmail.innerHTML = '<font class="tips_false">输入错误</font>';
	} else {
		divmail.innerHTML = '<font class="tips_true">输入正确</font>';
	}
}

function viewmypic(mypic, upfile) {
	if (upfile.files && upfile.files[0]) {
		mypic.style.display = "";
		//火狐下，直接设img属性  //mypic.src = upfile.files[0].getAsDataURL();  
		//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
		mypic.src = window.URL.createObjectURL(upfile.files[0]);
	} else {
		//IE下  
		if (upfile.value) {
			mypic.src = upfile.value;
			mypic.style.display = "";
			mypic.border = 1;
		}
	}
}