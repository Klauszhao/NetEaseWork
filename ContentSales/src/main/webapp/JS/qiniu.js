(function(w, d, u) {
	var uploader;
    var $id = function(id){
        return document.getElementById(id);
    };
	function uploaderReady(token) {
		// 引入Plupload 、qiniu.js后
		uploader = Qiniu.uploader({
			runtimes : 'html5,flash,html4', // 上传模式,依次退化
			browse_button : 'upload', // 上传选择的点选按钮，**必需**
			uptoken : token,

			// uptoken_url: '/token', //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
			// uptoken: '', //若未指定uptoken_url,则必须指定 uptoken ,uptoken由其他程序生成
			save_key: false,  // save_key: true, // 默认 false。若在服务端生成uptoken的上传策略中指定了 `sava_key`，则开启，SDK会忽略对key的处理
			domain : 'http://p3d53vnjf.bkt.clouddn.com', // bucket
			// 域名，下载资源时用到，**必需**
			// http://p3d53vnjf.bkt.clouddn.com
			// neteasework
			get_new_uptoken : false, // 设置上传文件的时候是否每次都重新获取新的token
			container : 'fileUpload', // 上传区域DOM ID，默认是browser_button的父元素，
			max_file_size : '4mb', // 最大文件体积限制
			flash_swf_url : 'Moxie.swf', // 引入flash,相对路径
			max_retries : 3, // 上传失败最大重试次数
			dragdrop : true, // 开启可拖曳上传
			drop_element : 'fileUpload', // 拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
			chunk_size : '4mb', // 分块上传时，每片的体积
			auto_start : true, // true表示选择文件后自动上传，若关闭需要自己绑定事件触发上传
			unique_names:false, // unique_names: true, 
			filters: {
			      mime_types : [
			        { title : "Image files", extensions : "jpg,gif,png"}
			      ],
			      max_file_size: "4mb",
			      prevent_duplicates: true
			},
			init : {
				'FilesAdded' : function(up, files) {
					plupload.each(files, function(file) {
						// 文件添加进队列后,处理相关的事情
						console.log(file.name);
					});
				},
				'BeforeUpload' : function(up, file) {
					// 每个文件上传前,处理相关的事情
/*					alert("file="+file);
					var exten=file.substring(file.length-3);
					var extenlist=["jpg","gif","png"];
					var bloon=false;
					for(var i=0;i<extenlist.length;i++){
						if(extenlist[i]==exten){
							bloon=true;
							break;
						}
					}
					alert("hehe");
					if(!bloon){
						alert("文件类型不对，请上传jpg、gif、png类型的图片");
						return ;
					}*/
				},
				'UploadProgress' : function(up, file) {
					// 每个文件上传时,处理相关的事情
				},
				'FileUploaded' : function(up, file, info) {
					// 每个文件上传成功后,处理相关的事情
					// 其中 info 是文件上传成功后，服务端返回的json，形式如
					// {
					// "hash": "Fh8xVqod2MQ1mocfI4S4KpRL6D98",
					// "key": "gogopher.jpg"
					// }
					// 参考http://developer.qiniu.com/docs/v6/api/overview/up/response/simple-response.html

					var domain = up.getOption('domain');
					var res =  JSON.parse(info);
					var sourceLink = domain +"/"+ res.key; // 获取上传成功后的文件的Url
					console.log("url="+sourceLink);
					$id("urlText").value=sourceLink; // 将连接放进一个input标签中
					$id("imgpre").src = sourceLink;  // 预览功能
					$id("urldivname").style.display='inline';
					$id('urldivname').innerHTML = '<font class="tips_true">上传成功！</font>'; // 显示 是否正确
				},
				'Error' : function(up, err, errTip) {
					// 上传出错时,处理相关的事情
				},
				'UploadComplete' : function() {
					// 队列文件处理完毕后,处理相关的事情
				},
				'Key' : function(up, file) {
					// 若想在前端对每个文件的key进行个性化处理，可以配置该函数
					// 该配置必须要在 unique_names: false , save_key: false 时才生效
	                //key就是上传的文件路径  
	                  var key = "";  
	                  //获取年月日时分秒  
	                  var date = new Date();  
	                  var year = date.getFullYear();  
	                  var month = date.getMonth()+1;  
	                  var day = date.getDate();  
	                  var hour = date.getHours();  
	                  var minute = date.getMinutes();  
	                  var second = date.getSeconds();  
	                  key += '/Netease/' + year+'_'+month+'_'+day+'_'+hour+minute+second;  
	                  console.log("文件路径："+file.name);  
	                  key += file.name;
					return key
				}
			}
		});
	};

	// domain 为七牛空间（bucket)对应的域名，选择某个空间后可以看到
	// uploader 为一个plupload对象，继承了所有plupload的方法，参考http://plupload.com/docs

	$(document).ready(function() {
			var upToken;
			var data={"name":"zhaonan"};
			$.ajax({
				cache : false,
				async : false,
				type : "POST",
				dataType : "json",
				url : "/ContentSales/Uptoken",
				data :JSON.stringify(data),
				success : function(data) {
					upToken = data.upToken;
					uploaderReady(upToken);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("textStatus="+textStatus+"   errorThrown="+errorThrown);
					alert("获取token失败");
				}
			});
	});
	var token=function() {
		var upToken;
		var data={"name":"zhaonan"};
		$.ajax({
			cache : false,
			async : false,
			type : "POST",
			dataType : "json",
			url : "/ContentSales/Uptoken",
			data :JSON.stringify(data),
			success : function(data) {
				upToken = data.upToken;
				uploaderReady(upToken);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("textStatus="+textStatus+"   errorThrown="+errorThrown);
				alert("获取token失败");
			}
		});
	}
	// 把这个函数放到按钮的click事件中，就可以执行上传命令
/*	function QiNiuUpload() {
		uploader.start();
	};*/
	
	document.getElementById('upload').onclick = function() {
		$id("urldivname").style.display='none';
		uploader.start();
	};
	
    var form = $id('commodityform');
    if(!form){
        return;
    }
    var title = form['title'];
    var summary = form['summary'];
    var image = form['urlText'];
    var detail = form['content'];
    var price = form['price'];
    var urldivname = form['urldivname'];
    
    var isSubmiting = false;
    var imgpre = util.get('imgpre');
    var loading = new Loading();
    var imageUrl;
    var imageMode = "urlUpload";


    var page = {
        init:function(){
            $id('uploadType').onclick = function(e){
                e = window.event || e;
                o = e.srcElement || e.target;
                if(o.nodeName==="INPUT"){
                    var s,h;
                    o.value==='url'?(s='urlUpload',h='fileUpload'):(s='fileUpload',h='urlUpload');
                    imageMode = o.value==='url'?"urlUpload":"fileUpload";
                    image.classList.remove("z-err");
                   /* uploadInput.classList.remove("z-err");*/
                    $id(s).style.display='block';
                    $id(h).style.display='none';
                }
            };
            [title,summary,image,detail,price].forEach(function(item){
                item.addEventListener('input',function(e){
                    item.classList.remove('z-err');
                }.bind(this),false);
            }.bind(this));
            image.addEventListener('input',function(e){
                var value = image.value.trim();
                if(value != ''){
                    imgpre.src = value;
                }
            }.bind(this),false);
        },
        check:function(){
            var result = true;
            [
                [title,function(value){return value.length<2 || value.length>80}],
                [summary,function(value){return value.length<2 || value.length>140}],
                [image,function(value){return imageMode == "urlUpload" && value == ''}],
                [detail,function(value){return value.length<2 || value.length>1000}],
                [price,function(value){return value == '' || !Number(value)}]
            ].forEach(function(item){
                var value = item[0].value.trim();
                if(item[1](value)){
                    item[0].classList.add('z-err');
                    result = false;
                }
                item[0].value = value;
            });
            if(imageMode == "fileUpload" && !imageUrl){
                /*uploadInput.classList.add('z-err');*/
                result = false;
            }
            return result;
        }
    };
    page.init();
})(window, document);