<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> -->
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统设置</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	
  </head>
  
  <body>
	<form action="video/saveProperties.do" method="post">
	<c:if test="${message!=null}">${message}<br/></c:if>
	VLC地址：<input type="text" name="vlcPath" value="${vlcPath}" size="50"><br/>
	开始端口：<input type="text" name="portStart" value="${portStart}"><br/>
	结束端口：<input type="text" name="portEnd" value="${portEnd}"><br/>
	<input type="submit" value="初始化信息">
	</form>
</body>
</html>
