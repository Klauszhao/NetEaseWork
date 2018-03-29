(function(w,d,u){
    var form = util.get('commodityform');
    if(!form){
        return;
    }
    var title = form['title'];
    var summary = form['summary'];
    var image = form['image'];
    var detail = form['content'];
    var price = form['price'];
    var uploadInput = form['file'];
    var isSubmiting = false;
    var imgpre = util.get('imgpre');
    var loading = new Loading();
    var imageUrl;
    var imageMode = "urlUpload";


    var page = {
        init:function(){
            var $id = function(id){
                return document.getElementById(id);
            };
            
            $id('uploadType').onclick = function(e){
                e = window.event || e;
                o = e.srcElement || e.target;
                if(o.nodeName==="INPUT"){
                    var s,h;
                    o.value==='url'?(s='urlUpload',h='fileUpload'):(s='fileUpload',h='urlUpload');
                    imageMode = o.value==='url'?"urlUpload":"fileUpload";
                    image.classList.remove("z-err");
                    uploadInput.classList.remove("z-err");
                    $id(s).style.display='block';
                    $id(h).style.display='none';
                }
            };
            
            $id('upload').addEventListener('click', function (){
            	QiNiuUpload();
                /*uploadInput.addEventListener('change', function() {
                    console.log(uploadInput.files) // File listing!
                });
                for (var i = 0, fileCount = uploadInput.files.length; i < fileCount; i++) {
                     console.log(uploadInput.files[i]);
                }
                var maxAllowedSize = 1000000;
                var file = uploadInput.files[0];

                    if(uploadInput.files[0].size > maxAllowedSize) {
                        alert("超过文件上传大小限制");
                    }else{
                    var form = new FormData();
                    form.append('file', file, file.name);
                    form.enctype = "multipart/form-data";
                    
                    QiNiuUpload();
                }*/
            });
            form.addEventListener('submit',function(e){
                if(!isSubmiting && this.check()){
                    price.value = Number(price.value);
                    isSubmiting = true;
                    form.submit();
                }
            }.bind(this),false); 
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
                uploadInput.classList.add('z-err');
                result = false;
            }
            return result;
        }
    };
    page.init();
})(window,document);