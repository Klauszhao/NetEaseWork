<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>发布商品</title>
<link rel="stylesheet" href="CSS/style.css" />
<link rel="stylesheet" href="CSS/style_PublishGoods.css" />
<script>
function Submit(){ 
    document.getElementById("commodityform").submit();
}
</script>
</head>
<body>
    <div class="n-head">
        <div class="g-doc f-cb">
            <div class="user">
                卖家你好，<span class="name">seller</span>！ <a href="/ContentSales/quit">[退出]</a>
            </div>
            <ul class="nav">
                <li><a href="/ContentSales/QueryForSaler">首页</a></li>
            </ul>
        </div>
    </div>
    <div class="g-doc">
        <div class="m-tab m-tab-fw m-tab-simple f-cb">
            <h2>内容发布</h2>
        </div>
        <div class="n-public">
            <form id="commodityform" name="commodityform" method="POST"
                action="SubmitCommodity" modelAttribute="commodity"
                onsubmit="return false;" autocomplete="off" class="m-form m-form-ht">
                <div class="fmitem">
                    <label class="fmlab">标题：</label>
                    <div class="fmipt">
                        <input id="title" class="u-ipt ipt" name="title" path="title"
                            placeholder="2-80字符" /> 
                        <span class="tips" id="divname"></span>
                    </div>
                 
                </div>
                <div class="fmitem">
                    <label class="fmlab">摘要：</label>
                    <div class="fmipt">
                        <input id="summary"  class="u-ipt ipt" path="summary" name="summary"
                            placeholder="2-140字符" />
                            <span class="tips" id="sumdivname"></span>
                    </div>
                </div>
               <div class="fmitem">
            <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <input type="radio" value="url" name="pic" /> 图片地址
                    <input name="pic" type="radio" value="file" /> 本地上传
                </div>  
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input id="urlText" class="u-ipt ipt" path="url" name="url" placeholder="图片地址"/>
                </div>
                <div class="fmipt" id="fileUpload"  style="display:none">
                    <!-- <input class="u-ipt ipt" name="file" type="file" id="fileUp"/> -->
                    <button class="u-btn u-btn-primary" id="upload">上传</button>
                    <span class="tips" id="urldivname"></span>
                </div>
            </div>

                <div class="fmitem">
                    <label class="fmlab">正文：</label>
                    <div class="fmipt">
                        <textarea id="content" class="u-ipt" name="content" path="content"
                            rows="10" placeholder="2-1000个字符"></textarea>
                            <span class="tips" id="condivname"></span>
                    </div>
                </div>
                <div class="fmitem">
                    <label class="fmlab">价格：</label>
                    <div class="fmipt">
                        <input id="price" class="u-ipt price" path="price" name="price" onblur="CheckNumLegal()"/>元
                         <span class="tips" id="pricedivnum"></span>
                    </div>
                  
                </div>
                <div class="fmitem fmitem-nolab fmitem-btn">
                    <div class="fmipt">
                        <button id="btone" type="submit"
                            class="u-btn u-btn-primary u-btn-lg" onClick="checkAndSubmit()">发布</button>
                    </div>
                </div>
            </form>
            <span class="imgpre">
            <img src="" alt="" id="imgpre"></span>
        </div>
    </div>
    <div class="n-foot">
        <p>
            版权所有：<a href="https://www.kaola.com">网易考拉</a>
        </p>
    </div>
    <script type="text/javascript" src="https://cdn.staticfile.org/jquery/2.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/moxie.js"></script>
    <script type="text/javascript" src="https://cdn.staticfile.org/plupload/2.1.9/plupload.dev.js"></script>
    <script type="text/javascript" src="https://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.min.js"></script>
    
    <script type="text/javascript" src="JS/global.js"></script> 
    <script type="text/javascript" src="JS/qiniu.js"></script>
    
    <!-- <script type="text/javascript" src="JS/public.js"></script> -->
    <script type="text/javascript" src="JS/PublishGoods.js"></script>
</body>
</html>