(function(w,d,u){
	var form = util.get('form');
	if(!form){
		return;
	}
	var title = form['title'];
	var summary = form['summary'];
	var url = form['url'];
	var content = form['content'];
	var price = form['price'];
	var isSubmiting = false;
	var imgpre = util.get('imgpre');
	var loading = new Loading();
	var page = {
		init:function(){
			form.addEventListener('submit',function(e){
				if(!isSubmiting && this.check()){
					price.value = Number(price.value);
					isSubmiting = true;
					form.submit();
				}
			}.bind(this),false);
			[title,summary,url,content,price].forEach(function(item){
				item.addEventListener('input',function(e){
					item.classList.remove('z-err');
				}.bind(this),false);
			}.bind(this));
			url.addEventListener('input',function(e){
				var value = url.value.trim();
				if(value != '' && /^(http|https):\/\//.test(value) && /\.(jpg|gif|png)$/.test(value)){
					imgpre.src = value;
				}
			}.bind(this),false);
		},
		check:function(){
			var result = true;
			[
				[title,function(value){return value.length<2 || value.length>80}],
				[summary,function(value){return value.length<2 || value.length>140}],
				[url,function(value){return value == '' || !(/^(http|https):\/\//.test(value) && /\.(jpg|gif|png)$/.test(value))}],
				[content,function(value){return value.length<2 || value.length>1000}],
				[price,function(value){return value == '' || !Number(value)}]
			].forEach(function(item){
				var value = item[0].value.trim();
				if(item[1](value)){
					item[0].classList.add('z-err');
					result = false;
				}
				item[0].value = value;
			});
			return result;
		}
	};
	page.init();
})(window,document);