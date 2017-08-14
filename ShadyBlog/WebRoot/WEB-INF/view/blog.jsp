<%@ page language="java" import="java.util.* " contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common/tag.jsp" %>

<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>shady's blog</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="<%=path%>/staticresources/assets/i/favicon.png">
  <link rel="stylesheet" href="<%=path%>/staticresources/assets/css/amazeui.min.css"/>
  <style>
    @media only screen and (min-width: 800px) {
      .blog-g-fixed {
        max-width: 800px;
      }
    }

    @media only screen and (min-width: 641px) {
      .blog-sidebar {
        font-size: 1.4rem;
      }
    }

    .blog-main {
      padding: 20px 0;
    }

    .blog-title {
      margin: 10px 0 20px 0;
    }

    .blog-meta {
      font-size: 14px;
      margin: 10px 0 20px 0;
      color: #222;
    }

    .blog-meta a {
      color: #27ae60;
    }

    .blog-pagination a {
      font-size: 1.4rem;
    }

    .blog-team li {
      padding: 4px;
    }

    .blog-team img {
      margin-bottom: 0;
    }

    .blog-content img,
    .blog-team img {
      max-width: 100%;
      height: auto;
    }

    .blog-footer {
      padding: 10px 0;
      text-align: center;
    }
    .my-sidebar-left {
      border-left: 2px solid #eeeeee;
    }
    .my-sidebar-right {
      border-right: 2px solid #eeeeee;
    }
  </style>
</head>
<body class="container" >
<header class="am-topbar" style="border-style:none;">
  <div class="am-collapse am-topbar-collapse" style="margin-left:30px;margin-right:30px;">
  <h1 class="am-topbar-brand" >
    <a href="<%=path %>/index">Daily Record</a>
  </h1>
    <ul class="am-nav am-nav-pills am-topbar-nav " style="margin:0px">
      <li ><a href="<%=path %>/index">首页</a></li>
      <li ><a href="<%=path %>/index">分类</a></li>
      <li><a href="">关于</a></li>
    </ul>
 	<ul class="am-nav am-nav-pills am-topbar-nav" style="float:right;margin-top:0px;">
      <li ><a href="">管理</a></li>
    </ul>
  </div>
</header>

<div class="am-g am-g-fixed blog-g-fixed">
  <div class="am-u-md-12">
  <c:forEach var="articleInfo" items="${articleInfoList }">
    <article class="blog-main">
      <h3 class="am-article-title blog-title">
        <a href="<%=path %>/content?articleId=${articleInfo.article.articleId}">${articleInfo.article.title}</a>
      </h3>
	  <div class="am-article-meta">
      <h4 class="blog-meta">
	      <span>
	  	   于 ${articleInfo.dateString}
	      </span>
	      <span style="color:#666;">&nbsp;|&nbsp;</span>
	      <span >标签:</span>
	      <c:forEach var="keyword" items="${articleInfo.keywordList }">
		      <a href="<%=path %>/index?keywordId=${keyword.keywordId}" style="color:#666;">${keyword.name}</a>
		      <span>&nbsp;</span>
	      </c:forEach>
      </h4>
      </div>
      <div class="am-g blog-content am-u-lg-12">${articleInfo.article.summary}</div>
     </article>
     <hr class="am-article-divider blog-hr">
  </c:forEach>

    <ul class="am-pagination blog-pagination">
      	<li class="am-pagination-prev" id="pagebeginl"><a href="<%=path%>/index?pageNum=1&keyworId=${pageNumInfo.keywordId}">&laquo; 首页</a></li>
		<li class="am-pagination-prev" id="pagebeginm"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum - 2}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum - 2}</a></li>
		<li class="am-pagination-prev" id="pagebeginr"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum - 1}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum - 1}</a></li>
      	<li class="am-pagination-next" id="pageendr"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageAmount}&keyworId=${pageNumInfo.keywordId}">尾页&raquo;</a></li>
		<li class="am-pagination-next" id="pageendm"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum + 2}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum + 2}</a></li>
		<li class="am-pagination-next" id="pageendl"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum + 1}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum + 1}</a></li>
    </ul>
  </div>

  <!-- 
  <div class="am-u-md-4 blog-sidebar">
    <div class="am-panel-group">
      <section class="am-panel am-panel-default">
        <div class="am-panel-hd">关于我</div>
        <div class="am-panel-bd">
          <p>this is taochuang's blog</p>
          <a class="am-btn am-btn-success am-btn-sm" href="#">查看更多 →</a>
        </div>
      </section>
      <section class="am-panel am-panel-default">
        <div class="am-panel-hd">标签</div>
        <ul class="am-list blog-list">
        	<c:forEach var="keyword" items="${keywordList }">
        		<li><a href="<%=path %>/index?keywordId=${keyword.keywordId}">${keyword.keywordId }</a>(${keyword.amount })</li>
        	</c:forEach>
        </ul>
      </section>
    </div>
  </div>
   -->
</div>

<footer class="blog-footer">
  <p>
    <small>© 2017 by taochuang. All rights reserved</small>
  </p>
</footer>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<!--<![endif]-->
<script src="<%=path%>/staticresources/assets/js/amazeui.min.js"></script>
<script type="text/javascript">
$(function() {
	var pageNum = ${pageNumInfo.pageNum};
	var pageAmount = ${pageNumInfo.pageAmount};
	if(pageNum == 1) {
		$('#pagebeginl').hide();
		$('#pagebeginm').hide();
		$('#pagebeginr').hide();
	}; 	  	
	if(pageNum == 2) {
		$('#pagebeginl').hide();
		$('#pagebeginm').hide();
	};
	if(pageNum == 3) {
		$('#pagebeginl').hide();
	};
	if(pageNum == pageAmount - 2) {
		$('#pageendr').hide();
	};
	if(pageNum == pageAmount - 1) {
		$('#pageendr').hide();
		$('#pageendm').hide();
	};
	if(pageNum == pageAmount) {
		$('#pageendr').hide();
		$('#pageendm').hide();
		$('#pageendl').hide();
	};
	  });
</script>
</body>
</html>
