(function(w,d,u){

	var name = 'products';
	var products = util.getCookie(name);
	var $id = function(id){
		return document.getElementById(id);
	}

	window.onload = function(){
		$id('newTable').onclick = function(e){
			var e = arguments[0] || window.event;
			target = e.srcElement ? e.srcElement : e.target;
			if(target.nodeName == "SPAN" && target.className == "moreNum"){
				var num = target.parentElement.children[1].textContent;
				var id = target.parentElement.children[2].textContent;
				num ++;
				target.parentElement.children[1].textContent = num;
				util.modifyOne(products,id,num);
			}else if(target.nodeName == "SPAN" && target.className == "lessNum"){
				var num = target.parentElement.children[1].textContent;
				var id = target.parentElement.children[2].textContent;
				num --;
				if(num >1 ){
					target.parentElement.children[1].textContent = num;
					util.modifyOne(products,id,num);
				}
			}
			return false;
		};
	};

	var loading = new Loading();
	var layer = new Layer();
	$id('Account').onclick = function(e){
		var idlist=getValueByName("shoppingRecorfId");
		var commodityIdlist=getValueByName("commodityId");
		var numlist=getValueByName("commodityNum");
		if(idlist.length==0){
			return;
		}
		var datalist=[];
		var data;
		for(var i=0;i<idlist.length;i++){
			data={
					"id":idlist[i].value,
					"commodityId":commodityIdlist[i].value,
					"num":numlist[i].innerHTML
			};
			datalist.push(data);
		}
		var ele = e.target;
			layer.reset({
				content:'确认购买吗？',
				onconfirm:function(){
					layer.hide();
					loading.show();
					
					if(SubmitAjax(datalist)){
						loading.result('购买成功',function(){location.href = '/ContentSales/accountShow';});
					}else{
						loading.result('购买失败');
					}				
				}.bind(this)
			}).show();
			return;
	};
	$id('back').onclick = function(){
		location.href = window.history.back();
	}
})(window,document);

function SubmitAjax(dataList){
	var boolean;
	$.ajax({
		cache : false,
		async : false,
		type : "POST",
		dataType : "json",
		data :JSON.stringify(dataList),
		url : "/ContentSales/cartSubmit",
		contentType : 'application/json',//注意类型
		success : function(data) {
			if ("success" == data.check_type) {
				boolean= true;
			} else {
				boolean= false;
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("nihao="+XMLHttpRequest.readyState+"  "+textStatus);
			boolean=false;
		}
	});

	return boolean;
}

function getValueByName(name){
    node=window.document;
    var x=[]; 

    var y=node.getElementsByName(name);
    //y=node.getElementsByTagName('*');
    for (var i=0;i<y.length;i++){
        x.push(y[i]);
    }
    return x;
}