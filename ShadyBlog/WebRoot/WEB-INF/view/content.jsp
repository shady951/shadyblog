<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common/tag.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>${articleInfo.article.title }</title>
    <link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.preview.min.css" />
	<link rel="stylesheet" href="<%=path%>/staticresources/editormd/css/editormd.min.css" />
    <link rel="stylesheet" href="<%=path%>/staticresources/assets/css/amazeui.min.css"/>
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
	<style>
    @media only screen and (min-width: 641px) {
      .am-offcanvas {
        display: block;
        position: static;
        background: none;
      }

      .am-offcanvas-bar {
        position: static;
        width: auto;
        background: none;
        -webkit-transform: translate3d(0, 0, 0);
        -ms-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
      }
      .am-offcanvas-bar:after {
        content: none;
      }

    }

    @media only screen and (max-width: 640px) {
      .am-offcanvas-bar .am-nav>li>a {
        color:#ccc;
        border-radius: 0;
        border-top: 1px solid rgba(0,0,0,.3);
        box-shadow: inset 0 1px 0 rgba(255,255,255,.05)
      }

      .am-offcanvas-bar .am-nav>li>a:hover {
        background: #404040;
        color: #fff
      }

      .am-offcanvas-bar .am-nav>li.am-nav-header {
        color: #777;
        background: #404040;
        box-shadow: inset 0 1px 0 rgba(255,255,255,.05);
        text-shadow: 0 1px 0 rgba(0,0,0,.5);
        border-top: 1px solid rgba(0,0,0,.3);
        font-weight: 400;
        font-size: 75%
      }

      .am-offcanvas-bar .am-nav>li.am-active>a {
        background: #1a1a1a;
        color: #fff;
        box-shadow: inset 0 1px 3px rgba(0,0,0,.3)
      }

      .am-offcanvas-bar .am-nav>li+li {
        margin-top: 0;
      }
    }

    .my-head {
      margin-top: 40px;
      text-align: center;
    }

    .my-button {
      position: fixed;
      top: 0;
      right: 0;
      border-radius: 0;
    }
    .my-sidebar {
      padding-left: 0;
      border-left: 1px solid #eeeeee;
    }

    .my-footer {
      border-top: 1px solid #eeeeee;
      padding: 10px 0;
      margin-top: 10px;
      text-align: center;
    }
    blockquote {
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 15px;
    border-left: 4px solid #ddd;
    font-family: Microsoft YaHei,Helvetica,Meiryo UI,Malgun Gothic,Segoe UI,Trebuchet MS,Monaco,monospace,sans-serif;
	}
	code {
	white-space:normal;
	color:#333;
	}
  </style>
  </head>

  <body>
	<header class="am-topbar" style="border-style:none;">
	<div class="am-collapse am-topbar-collapse" style="margin-left:30px;margin-right:30px;">
	  <h1 class="am-topbar-brand" >
	    <a href="<%=path %>/index">Daily Record</a>
	  </h1>
	    <ul class="am-nav am-nav-pills am-topbar-nav " style="margin:0px">
	      <li ><a href="<%=path %>/index">首页</a></li>
	      <li ><a href="<%=path %>/index">分类</a></li>
	      <li><a href="<%=path %>/index">关于</a></li>
	    </ul>
	 	<ul class="am-nav am-nav-pills am-topbar-nav" style="float:right;margin-top:0px;">
	      <li ><a href="">管理</a></li>
	    </ul>
	  </div>
	</header>
	<header class="am-g my-head">
	  <div class="am-u-sm-12 am-article">
	    <h1 class="am-article-title">${articleInfo.article.title }</h1>
	    <p class="am-article-meta"> 
	      <span style="color:#9e9e9e;">发表于</span>
	      <span>${articleInfo.dateString}</span>
	      <span style="color:#9e9e9e;">&nbsp;|&nbsp;</span>
	      <span style="color:#9e9e9e;">标签:</span>
	      <c:forEach var="keyword" items="${articleInfo.keywordList }">
		      <a href="<%=path %>/index?keywordId=${keyword.keywordId}" style="color:#333333;">${keyword.name}</a>
		      <span>&nbsp;</span>
	      </c:forEach></p>
	  </div>
	</header>
	<div class="am-g am-g-fixed" style="margin-top:40px;">
	 <div class="am-u-md-9 editormd-preview-theme-dark" id="contentMd">
	 	<textarea style="display:none;">${content.textMd }</textarea>
	 </div>
	 <div class="am-u-md-3 my-sidebar" >
	 	<div style="position:fixed;">
	 	<div class="markdown-body editormd-preview-container" id="tocContainer" ></div>
	 	</div>
	 </div>
	</div>
  <footer class="my-footer">
   <small><a href="http://www.miitbeian.gov.cn/">蜀ICP备17027700号</a> © 2017 by taochuang</small>
  </footer>
  </body>
	<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/marked.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/lib/prettify.min.js"></script>
	<script src="<%=path%>/staticresources/editormd/editormd.min.js"></script>
	<script src="<%=path%>/staticresources/assets/js/amazeui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var editormdView;
			
		     editormdView = editormd.markdownToHTML("contentMd", {
                    htmlDecode      : "style,script,iframe",  // you can filter tags decode
                    emoji           : true,
                    taskList        : true,
                    tocContainer    : "#tocContainer", // 自定义 ToC 容器层
                    //tocStartLevel : 2      // 从<h2>标签字体开始转换成toc，默认为1
                    tex             : false,  // 默认不解析
                    flowChart       : false,  // 默认不解析
                    sequenceDiagram : false,  // 默认不解析
                   previewTheme: "dark" 	//预览主题
                });
                    
                    //console.log("返回一个 jQuery 实例 =>", editormdView);
                    
                    // 获取Markdown源码
                    //console.log(editormdView.getMarkdown());
                    
                    //alert(editormdView.getMarkdown());
		});
	</script>
</html>
