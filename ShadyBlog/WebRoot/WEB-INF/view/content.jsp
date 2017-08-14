<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>shady's blog</title>
    <link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.preview.min.css" />
	<link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.css" />
  </head>

  <body>
    	<div>
	 		${articleInfo.article.title}
	 	</div>
	 	<span>-----------------------------</span>
    <div id="contentMd">
    	<textarea style="display:none;">${content.textMd}</textarea>
    </div>
    	<span>-----------------------------</span>
	 	<div >
	 		${content.textHtml}
	 	</div>
    	<span>-----------------------------</span>
	 	<div >
	 		${content.textMd}
	 	</div>
	 	<div class="markdown-body editormd-preview-container" id="tocContainer"></div>
  </body>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/marked.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/prettify.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/editormd.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var editormdView;
			
		     testEditormdView = editormd.markdownToHTML("contentMd", {
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tocContainer    : "#tocContainer", // 自定义 ToC 容器层
                    tex             : true,  // 默认不解析
                    flowChart       : true,  // 默认不解析
                    sequenceDiagram : true,  // 默认不解析
                });
                    
                    //console.log("返回一个 jQuery 实例 =>", editormdView);
                    
                    // 获取Markdown源码
                    //console.log(editormdView.getMarkdown());
                    
                    //alert(editormdView.getMarkdown());
		});
	</script>
</html>
