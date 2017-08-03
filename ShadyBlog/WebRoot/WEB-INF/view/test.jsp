<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.preview.min.css" />
	<link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.css" />
  </head>
  
  <body>
    <div class="markdown-body editormd-html-preview" id="content">${editormd}</div>
  </body>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/marked.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/prettify.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/editormd.min.js"></script>
</html>
