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
    
    <title>摄像头</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--video-js依赖-->
	<script src="js/videojs-ie8.min.js"></script>
	<link href="css/video-js.css" rel="stylesheet">
	<script src="js/video.js"></script>
	<!-- If you'd like to support IE8 -->
	
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	<!-- <script src="js/videojs-media-sources.js"></script> -->
	
	 
  </head>
  
  <body>

	<input type="hidden" value="${ip}${port}${size}" id="videoId">
	<div align="center" style="height:100%;length:100%;">
	<video  id="example_video" preload="none"  class="video-js vjs-default-skin" controls preload="auto" width="480" height="264" autoplay="autoplay"
      poster="http://video-js.zencoder.com/oceans-clip.png" data-setup='{"controls":true,"example_option":true}'>
        <%-- <source src="http://${serverIp}:49999/${ip}${size}" type='video/flv' /> --%>
       
        <source src="http://${serverIp}:${serverPort}/${ip}${size}" type='video/flv' />
     </video>
     </div>
     <script type="text/javascript"> 
     
	//发送video存在请求
    function videoAlive() {
    	$.ajax({
    	       type: "POST",
    	       url: "video/videoAlive.json",
    	       data: {"videoId":$("#videoId").val()},
    	       dataType:"json",
    	       success: function(data){
    	    	  // console.log(data.msg)
    	       }
    	       
    	});
    	  /*   var $tempForm = $('<form method="post" target="_blank" action="www.baidu.com"></form>');  
    	    $("body").append($tempForm);  
    	    $tempForm.submit();  
    	    $tempForm.remove();   */
    	//console.log(document.getElementById("aaa"));
    	//document.getElementById("aaa").click();
    	//var a = $("#aaa")
    }
	
    setInterval('videoAlive();', 5000);  //每隔3秒循环执行过程函数！
  
    </script> 
</body>
</html>
