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
    
    <title>welcome</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	<!--video-js依赖-->
	<link href="js/video-js.css" rel="stylesheet">
	<script src="js/video.js"></script>
	<!-- If you'd like to support IE8 -->
	
	<script src="js/videojs-ie8.min.js"></script>
	<!-- <script src="js/videojs-media-sources.js"></script> -->
	
  </head>
  
  <body>
	<!-- <a href="test/testPage.do" onClick="">测试页面</a> -->
	<!-- <video id="my-video" class="video-js" controls preload="auto"
		width="640" height="264" poster="" data-setup="{}">"autoplay": true
		<source src="rtmp://rtmp.jim.stream.vmmacdn.be/vmma-jim-rtmplive-live/jim" type="rmpt/flv"/>
		<source src="rtmp://rtmp.jim.stream.vmmacdn.be/vmma-jim-rtmplive-live/jim" type="rmpt/mp4"/>
		<source src="http://www.iptv-player.com/index.php?fdb=1&title=%20+JIMTV%20%20&stream=rtmp%3A%2F%2Frtmp.jim.stream.vmmacdn.be%2Fvmma-jim-rtmplive-live%2Fjim" type="rmpt/flv"/>
		<source src="http://www.iptv-player.com/index.php?fdb=1&title=%20+JIMTV%20%20&stream=rtmp%3A%2F%2Frtmp.jim.stream.vmmacdn.be%2Fvmma-jim-rtmplive-live%2Fjim" type="rmpt/mp4"/>




		<source src="js/test.mp4" type='video/mp4' />
		<source src="js/test.mp4" type='video/webm'>

		<source src="http://127.0.0.1:8080/test" type="http/flv"/>
		<source src="http://127.0.0.1:8080/test" type="application/x-mpegURL"/>
		
		
		<source src="http://127.0.0.1:8080/test" type="http/mp4"/>
        <source src="http://127.0.0.1:8080/test" type='application/x-mpegURL' />
        <source src="http://127.0.0.1:8080/test" type="http/flv"/>
        <source src="http://127.0.0.1:8080/test" type='video/webm'> 
        <source src="http://127.0.0.1:8080/test" type='application/x-mpegURL'>  
        <source src="http://127.0.0.1:8080/test" type='video/ogg' />
        <source src="http://127.0.0.1:8080/test" type='video/mp4' />
	<source src="http://127.0.0.1:8080/test" type='video/webm'> -->
<!-- 	<video id=example-video width=960 height=540 class="video-js vjs-default-skin" controls>
	  <source  src="http://127.0.0.1:8080/test" type="application/x-mpegURL">
	</video>
	<script>
	var player = videojs('example-video');
	player.play();
	</script> -->



	<video id="example_video" class="video-js vjs-default-skin" controls preload="auto" width="640" height="264" autoplay="autoplay"
      poster="http://video-js.zencoder.com/oceans-clip.png"
      data-setup='{"controls":true}'>
       <!-- <source src="http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8" type="rtmp/flv"/> -->
       <!--可用  <source src="rtmp://live.hkstv.hk.lxdns.com/live/hks" type='rtmp/mp4' /> -->
        <!-- <source src="rtmp://127.0.0.1:1935/myapp/test1" type='rtmp/mp4' /> -->
        <source src=" http://127.0.0.1:8081/test" type='video/flv' /> 
       
       <!--  <source src="http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8" type='application/vnd.apple.mpegurl' />
		<source src="http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8" type='application/x-mpegURL' /> -->
     </video>
  	 <!--  <source src="js/tt.mp4" type='video/mp4' /> -->
	 <!--   <source src="js/tt.mp4" type='video/mp4' /> -->
 	 <!--  <source src="http://127.0.0.1:8080/test" type='video/mp4' />  -->
    <!-- <source src="js/tt.mp4" type='video/mp4' /> -->
<!--     <source src="http://video-js.zencoder.com/oceans-clip.mp4" type='video/mp4' />
	<source src="http://vjs.zencdn.net/v/oceans.webm" type='video/webm'>
    <source src="http://vjs.zencdn.net/v/oceans.ogv" type='video/ogg'> -->
   <!--     <source src="http://127.0.0.1:8080/test" type='video/mp4' />
	<source src="http://127.0.0.1:8080/test" type='video/webm'>
    <source src="http://127.0.0.1:8080/test" type='video/ogg'> -->
   <!--  <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9
    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9 -->
    
    
     <!--  <source src="http://127.0.0.1:8080/test.mp4" type='video/mp4' />
    <source src="http://127.0.0.1:8080/test.webm" type='video/webm' />application/x-mpegURL
    <source src="http://127.0.0.1:8080/test.ogv" type='video/ogg' /> -->
   <!--   <source src="http://127.0.0.1:8080/test.htp" type='application/octet-stream' /> -->
      <!--  <source src="rtmp://cp67126.edgefcs.net/ondemand/&mp4:mediapm/ovp/content/test/video/spacealonehd_sounas_640_300.mp4" type='rtmp/mp4'>  -->
      
     <!-- <source src="http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8" type='application/x-mpegurl'> -->
     
     <!--  <source src="rtmp://cp67126.edgefcs.net/ondemand/&mp4:mediapm/ovp/content/test/video/spacealonehd_sounas_640_300.mp4" type='rtmp/mp4'> -->
 <!--    <source src="http://192.168.1.26:8080/test" type='application/x-mpegURL' /> 
    <source src="http://192.168.1.26:8080/test" type="video/mp4" />   -->
    <!-- <source src="http://127.0.0.1:8080/test" type='application/x-mpegURL' /> 
    <source src="http://127.0.0.1:8080/test" type="video/mp4" />  
    <source src="http://127.0.0.1:8080/test" type="video/mp4" />
    <source src="http://127.0.0.1:8080/test" type='video/mp4' /> -->
      
  <!-- -->
  	<!-- <source src="http://127.0.0.1:8080/test" type='application/octet-stream' />
  	 <source src="http://127.0.0.1:8080/test" type='video/flv' />      -->
  	<!--  <source src="http://127.0.0.1:8080/hls/testnginx" type='application/octet-stream' />   -->
  <!--  <source src="http://127.0.0.1:8080/test" type='video/x-flv' /> -->
   <!--  <source src="http://127.0.0.1:8080/test" type='video/webm' />
    <source src="http://127.0.0.1:8080/test" type='video/ogg' /> 
    <source src="http://127.0.0.1:8080/test" type='application/x-mpegURL' /> 
    <source src="http://127.0.0.1:8080/test" type='application/octet-stream' /> 
    <source src="http://127.0.0.1:8080/test" type="rtmp/flv"/>
    <source src="http://127.0.0.1:8080/test" type='application/x-mpegURL' />  
    <source src="http://127.0.0.1:8080/test" type='video/mp2t' />  
   <source src="http://127.0.0.1:8080/test" type='video/flv' />     
   <source src="http://127.0.0.1:8080/test" type='video/x-flv' />    -->  
    

	
	<!-- <script src="js/videojs.hls.min.js"></script> -->
	
	<script>
	//var player = videojs('example-video');
	//player.play();
	//player.hls('http://devimages.apple.com/iphone/samples/bipbop/bipbopall.m3u8');
	</script>
	<!-- <script src="js/videojs-media-sources.js"></script>
	<script src="js/videojs-hls.js"></script> -->
	<!-- <script>  
   		 videojs.options.flash.swf = "js/video-js.swf";  
  	</script>
	<script>
	var player = videojs('example-video');
	player.play();
	</script> -->
	<div style="width: 640px; height: 360px; ">
	
	</div>
	<p class="vjs-no-js">
		To view this video please enable JavaScript, and consider upgrading to
		a web browser that <a href="http://videojs.com/html5-video-support/"
			target="_blank">supports HTML5 video</a>
	</p>
</body>
</html>
