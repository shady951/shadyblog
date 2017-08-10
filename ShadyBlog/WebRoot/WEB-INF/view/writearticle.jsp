<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- editormd start -->
    <link href="<%=path%>/staticresources/editormd/css/editormd.min.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="<%=path%>/staticresources/editormd/editormd.min.js"></script>
    <script type="text/javascript">
  var testEditor;

  testEditor=$(function() {
      editormd("test-editormd", {
           width   : "90%",
           height  : 640,
           //markdown : md,
           codeFold : true,
           syncScrolling : "single",
           //你的lib目录的路径
           path    : "<%=path%>/staticresources/editormd/lib/",
           imageUpload: true,//开启图片上传功能
           imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
           imageUploadURL : "<%=path%>/manager/uploadimg",
          /*  theme: "dark",//工具栏主题
           previewTheme: "dark",//预览主题
           editorTheme: "pastel-on-dark",//编辑主题 */
           emoji: true,
           taskList: true, 
           tocm: true,         // Using [TOCM]
           tex: false,                   // 开启科学公式TeX语言支持，默认关闭
           flowChart: false,             // 开启流程图支持，默认关闭
           sequenceDiagram: false,       // 开启时序/序列图支持，默认关闭,
          //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
           saveHTMLToTextarea : true            
      });
  });
</script>
  <!-- editormd end -->  
  </head>
  
  <body>
  	<form action="<%=path%>/manager/addarticle" method="post" id="articleform" enctype="multipart/form-data"
  	<span>标题:</span>
  	<input name="title" value="">
  	<span>摘要:</span>
  	<input name="summary" value="">
  	<span>关键词(多个以英文逗号“,”分割):</span>
  	<input name="keywords" value="">
  	<input type="submit" value="提交"/>
  	</form>
    <!-- editormd start -->
    <div class="editormd" id="test-editormd">
    <!-- textarea的属性中，class属性必须指定，比如editormd-markdown-textarea，就指定该area保存md格式内容 -->
    <!-- class=editormd-html-textarea，就指定该area保存html内容，但需要开启配置项 saveHTMLToTextarea == true -->
    <textarea class="editormd-markdown-textarea" name="editormd" form="articleform"></textarea>
    <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
    <textarea class="editormd-html-textarea" name="editorhtml" form="articleform"></textarea>       
    </div>
    <!-- editormd end --> 
</body>
</html>
