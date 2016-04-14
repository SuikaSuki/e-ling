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
    
    <title>摄像头组</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	<!--video-js依赖-->
	<link href="css/video-js.css" rel="stylesheet">
	<script src="js/video.js"></script>
	<!-- If you'd like to support IE8 -->
	
	<script src="js/videojs-ie8.min.js"></script>
	<!-- <script src="js/videojs-media-sources.js"></script> -->
	
	<script language="JavaScript"> 

	//发送video存在请求
    function videoAlive() {
    	$.ajax({
    	       type: "POST",
    	       url: "video/videoAlive.json",
    	       data: {"videoId":$("#videoId").val()},
    	       dataType:"json",
    	       success: function(data){
    	    	   console.log(data.msg)
    	       }
    	       
    	});
    }

    setInterval('videoAlive();', 5000);  //每隔n秒循环执行过程函数！
    </script>  
	<style type="text/css">
	.videoLeft {
		display:inline;
		float:left;
	    zoom: 1;
	    width: 78%;
	     /* height: 380px;  */
	    margin: 0 auto;
	    font-family: sans-serif;
	}
	.videoRight { 
		display:inline;
		float:right;
		background-color:#33cc99 ;
	    zoom: 1;
	    width: 22%;
	  	height: 1000px;
	    margin: 0 auto;
	    font-family: sans-serif;
	}
	
	.videoLeftIn {
		display:inline;
		float:left;
		background-color:white;
	    zoom: 1;
	    width: 450px;
	    height: 295px;
	    margin:0px 10px 40px 10px;
	    font-family: sans-serif;
	}
	.videoLeftFont{
		font-size:24px;
		color:#333333;
	}
	.videoRightFont{
		font-size:24px;
		color:#ffffff;
		margin-left:16px
	}
	.videoRightFontCount{
		font-size:24px;
		color:#ffffff;
	}
	</style>
	 
  </head>
  
  <body>

	<input type="hidden" value="<c:forEach var="video" items="${videos}">${video.caremaIp}${video.caremaPort}${video.caremaSize},</c:forEach>" id="videoId">
	
	<div class="videoLeft">
	<div style="height:60px;lenght:100%;"></div>
	
	<c:forEach var="video" items="${videosReverse}">
	<div class="videoLeftIn">
	<font class="videoLeftFont">${video.caremaName}</font>
	<video  class="video-js vjs-default-skin" controls preload="auto" style="margin-top:10px" width="450" height="264" autoplay="autoplay"
      poster="http://video-js.zencoder.com/oceans-clip.png"
      data-setup='{"controls":true}'>
        <source src="http://${serverIp}:${video.serverPort}/${video.caremaIp}${video.caremaSize}" type='video/flv' /> 
     </video>
	</div>
	</c:forEach>
	
	</div>
	<div class="videoRight">
	<div style="height:60px;lenght:100%;"></div>
	<c:forEach var="video" items="${videos}" varStatus="status">
	<div style="margin-bottom: 20px;margin-left: 20px;">
	<font class="videoRightFontCount" >${status.index + 1}.</font>
	<font class="videoRightFont" >${video.caremaName}</font>
	</div>
	</c:forEach>
	</div>
</body>
</html>
