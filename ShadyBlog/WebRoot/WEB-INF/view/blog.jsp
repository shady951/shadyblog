<%@ page language="java" import="java.util.*, com.shadyblog.util.DateUtil" contentType="text/html; charset=UTF-8"%>
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
  <link rel="alternate icon" type="image/jpg" href="<%=path%>/staticresources/favicon.jpg">
  <link rel="stylesheet" href="<%=path%>/staticresources/assets/css/amazeui.min.css"/>
  <style>
    @media only screen and (min-width: 800px) {
      .blog-g-fixed {
        max-width: 800px;
      }
    }

    @media only screen and (min-width: 641px) {
      .blog-sidebar {
        font-size: 1.6rem;
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
    a {
    	color:#333333;
    }
  </style>
</head>
<body class="container" >
<header class="am-topbar" style="border-style:none;">
  <div class="am-collapse am-topbar-collapse" style="margin-left:30px;margin-right:30px;">
  <h1 class="am-topbar-brand" >
    <a href="<%=path %>/index">Shady's Blog</a>
  </h1>
    <ul class="am-nav am-nav-pills am-topbar-nav " style="margin:0px">
      <li ><a href="<%=path %>/index">首页</a></li>
    </ul>
 	<ul class="am-nav am-nav-pills am-topbar-nav" style="float:right;margin-top:0px;">
      <li >
      <shiro:guest>
	      <a href="<%=path %>/manager/index">管理</a>
      </shiro:guest>
      <shiro:authenticated>
		<a href="<%=path %>/manager/writearticle">新文章</a>
      </shiro:authenticated>
      </li>
    </ul>
  </div>
</header>

<div class="am-g am-g-fixed blog-g-fixed">
  <div class="am-u-md-10">
  <c:forEach var="articleInfo" items="${articleInfoList }">
    <article class="blog-main">
      <h3 class="am-article-title blog-title">
        <a href="<%=path %>/content?articleId=${articleInfo.article.articleId}">${articleInfo.article.title}</a>
      </h3>
	  <div class="am-article-meta">
      <h4 class="blog-meta">
	      <span style="color:#9e9e9e;">发表于</span>
	      <span>${articleInfo.dateString}</span>
	      <span style="color:#9e9e9e;">&nbsp;|&nbsp;</span>
	      <span style="color:#9e9e9e;">标签:</span>
	      <c:forEach var="keyword" items="${articleInfo.keywordList }">
		      <a style="color:#333333" href="<%=path %>/index?keywordId=${keyword.keywordId}">${keyword.name}</a>
		      <span>&nbsp;</span>
	      </c:forEach>
	      <shiro:authenticated>
	      	<span style="color:#9e9e9e;">浏览量:${articleInfo.article.clickNumber }</span>
	      	<a href="<%=path %>/manager/alterarticle?articleId=${articleInfo.article.articleId}">编辑文章</a>
	      </shiro:authenticated>
      </h4>
      </div>
      <div class="am-g blog-content am-u-lg-12">${articleInfo.article.summary}</div>
     </article>
     <hr class="am-article-divider blog-hr">
  </c:forEach>

    <ul class="am-pagination blog-pagination">
      	<li class="am-pagination-prev" id="pagebeginl"><a href="<%=path%>/index?pageNum=1&keyworId=${pageNumInfo.keywordId}">&laquo; 首页</a></li>
		<li class="am-pagination-prev" id="pagebeginm"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum - 2}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum - 2}</a></li>
		<li class="am-pagination-prev" id="pagebeginr"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum - 1}&keyworId=${pageNumInfo.keywordId}">上一页</a></li>
      	<li class="am-pagination-next" id="pageendr"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageAmount}&keyworId=${pageNumInfo.keywordId}">尾页&raquo;</a></li>
		<li class="am-pagination-next" id="pageendm"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum + 2}&keyworId=${pageNumInfo.keywordId}">${pageNumInfo.pageNum + 2}</a></li>
		<li class="am-pagination-next" id="pageendl"><a href="<%=path%>/index?pageNum=${pageNumInfo.pageNum + 1}&keyworId=${pageNumInfo.keywordId}">下一页</a></li>
    </ul>
  </div>
  
  <div class="am-u-md-2 blog-sidebar" style="border-left:1px solid #eeeeee;margin-top: 40px;">
    <div class="am-panel-group">
        <div style="font-weight:600">标签统计</div>
        <ul class="am-list blog-list">
        	<c:forEach var="keyword" items="${keywordList }">
        		<li style="color:#9e9e9e;"><a href="<%=path %>/index?keywordId=${keyword.keywordId}">${keyword.name }<span style="color:#9e9e9e;">(${keyword.amount })</span></a></li>
        	</c:forEach>
        </ul>
    </div>
  </div>

</div>

 <shiro:authenticated>
  <div class="am-u-md-12">
   <table class="am-table">
    <thead>
        <tr>
            <th>IP(默认排序)</th>
            <th>归属地</th>
            <th>首页浏览量</th>
            <th>内容浏览量</th>
            <th>第一次访问</th>
            <th>最近访问</th>
        </tr>
    </thead>
    <tbody>
     <c:forEach var="iprecord" items="${iprecordList }">
        <tr>
            <td>${iprecord.ip}</td>
            <td>${iprecord.address}</td>
            <td>${iprecord.indexnum}</td>
            <td>${iprecord.contentnum}</td>
            <td><fmt:formatDate value="${iprecord.firstTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${iprecord.lastTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
     </c:forEach>
    </tbody>
   </table>
  </div>
 </shiro:authenticated>

<footer class="blog-footer">
   <p>
    <small style="color:#9e9e9e"><a style="color:#9e9e9e" href="http://www.miitbeian.gov.cn/">蜀ICP备17027700号</a> © 2017 Shady</small>
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
