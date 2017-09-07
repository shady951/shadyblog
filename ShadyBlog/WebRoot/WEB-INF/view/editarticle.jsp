<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link href="<%=path%>/staticresources/editormd/css/editormd.min.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
  	<form action="<%=path%>/manager/addarticle" method="post" id="articleform" enctype="multipart/form-data">
  	<span>标题:</span>
  	<input name="title" value="${articleInfo.article.title }" required="required">
  	<span>摘要:</span>
  	<input name="summary" value="${articleInfo.article.summary }">
  	<span>关键词(多个以英文逗号“,”分割):</span>
  	<input name="keywords" value="${keywords }" >
  	<input type="hidden" name="articleId" value="${articleInfo.article.articleId }"/>
  	<input id="newarticle" type="submit" value="保存"/>
  	<input id="oldarticle" type="submit" formaction="<%=path%>/manager/updatearticle" value="更新并保存" />
  	<span>创建时间：${articleInfo.dateString }</span>
  	</form>
    <!-- 以下为editormd内容 -->
    <div class="editormd editormd-vertical" id="my_editormd">
    <!-- textarea的属性中，class属性必须指定，
    	比如editormd-markdown-textarea，就指定该area保存md格式内容。
    	class=editormd-html-textarea，就指定该area保存html内容，但需要开启配置项 saveHTMLToTextarea == true -->
    <textarea style="display:none;" class="editormd-markdown-textarea" name="editormd" form="articleform">${content.textMd }</textarea>
    <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
    <textarea style="display:none;" class="editormd-html-textarea" name="editorhtml" form="articleform"></textarea>       
    </div>
    <!-- 以上 --> 
</body>
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/staticresources/editormd/editormd.min.js"></script>
	<script type="text/javascript">
	  var Editor;
	  Editor=$(function() {
	  	//各种参数配置
	      editormd("my_editormd", {
	      		//以下为主要配置项
	           width   : "90%", 
	           height  : 640,
	           path    : "<%=path%>/staticresources/editormd/lib/", /*静态资源中editormd的lib目录的路径*/
	           imageUpload: true,	/*开启图片上传功能*/
	           imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],		/*支持的图片格式*/
	           imageUploadURL : "<%=path%>/manager/uploadimg",	/*上传路径*/
	           saveHTMLToTextarea : true,     /*保存 HTML 到 Textarea*/
	           //以下为次要配置项
	           //markdown : md,	/*解析传入的markdown格式内容为html内容*/
	           codeFold : true,
	           syncScrolling : "single", /*同步滚动,默认为single，单向同步滚动*/
	           //htmlDecode : "style,script,iframe|on*",	/*开启 HTML 标签解析，为了安全性，默认不开启*/
	           //theme: "dark", /*工具栏主题*/
	           //editorTheme: "pastel-on-dark",/*编辑区的主题*/
	           //previewTheme: "dark", 	/*预览区的主题*/
	           //toolbar  : false,             /*关闭工具栏*/
               //previewCodeHighlight : false, /* 关闭预览区HTML 的代码块高亮，默认开启*/
	           emoji: true, /*表情包*/
	           //toc:true,	/*Table of Contents 目录*/
	           //tocContainer:"#custom-toc-container", /*目录的位置*/
	           //tocDropdown:false, /*目录是否为下拉形式*/
	           //tocStartLevel : 2      /*从<h2>标签字体开始转换成toc，默认为从h1开始*/
	           //tocm: true,         /*Using [TOCM]*/
	           taskList: true,/*任务列表支持*/
	           //tex: true,                   /* 开启科学公式TeX语言支持，默认关闭*/
	           //flowChart: true,             /* 开启流程图支持，默认关闭*/
	           //sequenceDiagram: true,       /* 开启时序/序列图支持，默认关闭*/
	      });
	  });
  	</script>
	<script type="text/javascript">
	  $(function() {
	  	if(${isNewArticle} == true) {
	  		$('#oldarticle').hide();
	  		$('#newarticle').show();
	  	} else {
	  		$('#newarticle').hide();
	  		$('#oldarticle').show();
	  	}
	  });
	</script>
</html>
