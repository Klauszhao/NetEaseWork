		function checkAndSubmit(){
			var bool_checkna=false;
			var bool_CheckNumLegal=false;
			bool_checkna=checkna();
			bool_CheckNumLegal=CheckNumLegal();
			if(bool_checkna==true && bool_CheckNumLegal==true){
				document.form1.submit();
  			}
			return false;
		}

	    function checkna(){

			na=form1.title.value;

		  	if( na.length <1 || na.length >15)  

	  		{  	

	  			divname.innerHTML='<font class="tips_false">长度是1~15个字符</font>';
				return false;
	  		     

	  		}else{  

	  		    divname.innerHTML='<font class="tips_true">输入正确</font>';
	  		    return true;

	  		}  

	  }

	  //验证密码 

		function checkpsd1(){    

			psd1=form1.yourpass.value;  

			var flagZM=false ;

			var flagSZ=false ; 

			var flagQT=false ;

			if(psd1.length<6 || psd1.length>12){   

				summary.innerHTML='<font class="tips_false">长度错误</font>';

			}else

				{   

				  for(i=0;i < psd1.length;i++)   

					{    

						if((psd1.charAt(i) >= 'A' && psd1.charAt(i)<='Z') || (psd1.charAt(i)>='a' && psd1.charAt(i)<='z')) 

						{   

							flagZM=true;

						}

						else if(psd1.charAt(i)>='0' && psd1.charAt(i)<='9')    

						{ 

							flagSZ=true;

						}else    

						{ 

							flagQT=true;

						}   

					}   

					if(!flagZM||!flagSZ||flagQT){

					summary.innerHTML='<font class="tips_false">密码必须是字母数字的组合</font>'; 

					 

					}else{

						

					summary.innerHTML='<font class="tips_true">输入正确</font>';

					 

					}  

				 

				}	

		}

		//验证确认密码 

		function checkpsd2(){ 

				if(form1.yourpass.value!=form1.yourpass2.value) { 

				     content.innerHTML='<font class="tips_false">您两次输入的密码不一样</font>';

				} else { 

				     content.innerHTML='<font class="tips_true">输入正确</font>';

				}

		}

		//验证邮箱

		

		function checkmail(){

					apos=form1.youremail.value.indexOf("@");

					dotpos=form1.youremail.value.lastIndexOf(".");

					if (apos<1||dotpos-apos<2) 

					  {

					  	divmail.innerHTML='<font class="tips_false">输入错误</font>' ;

					  }

					else {

						divmail.innerHTML='<font class="tips_true">输入正确</font>' ;

					}

		}

		//判断数字是否合法
		function CheckNumLegal(){
			var num=form1.price.value;

			if(num === "" || num ==null){
        		divnum.innerHTML='<font class="tips_false">请输入数字</font>';
        		return false;
    		}
    		if(isNaN(num) == true){
        		divnum.innerHTML='<font class="tips_false">请输入数字</font>';
        		return false;
    		}
    		var point = num.toString().indexOf(".");
    		if(point > -1 ){
        		var length = num.toString().split(".")[1].length;//数字小数点后有几位
        		if(length > 2){
            		divnum.innerHTML='<font class="tips_false">数字保留两位小数点</font>';
            		return false;
        		}else {
        			divnum.innerHTML='<font class="tips_true">输入正确</font>';
        			return true;
        		}
    		}

            divnum.innerHTML='<font class="tips_true">输入正确</font>';
    		return true;
		}

		function viewmypic(mypic,upfile) {  
			if(upfile.files && upfile.files[0])  
			{  
				mypic.style.display="";  
				//火狐下，直接设img属性  //mypic.src = upfile.files[0].getAsDataURL();  
 				//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式    
				mypic.src = window.URL.createObjectURL(upfile.files[0]);  
			}  
			else  
			{  
				//IE下  
				if (upfile.value){  
					mypic.src=upfile.value;  
					mypic.style.display="";  
					mypic.border=1;  
				}  
			}  
		}