<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.web.WebAttributes,org.springframework.security.core.AuthenticationException,org.springframework.security.authentication.BadCredentialsException" %>
<!DOCTYPE html>
<%

String path = request.getContextPath();

%>
<html>
<head>
<title>测试</title>

	<script type="text/javascript" src="<%=path%>/assets/jquery/jquery-1.7.js"></script>
</head>
<body>
	<script type="text/javascript">
		$.ajax({
					url : "<%=path%>/test/getAllImei",
					type : "post",
					data : {
						//"monitor.coordinate" : ''
					},
					dataType:"json",
					success : function(data) {
						if (data != null && data != '') {
							for (var i=0;i<data.length;i++){
								//alert("111"+data[i].imei);
								$("#test").append("imei号："+data[i].imei
										//+"		<input type='button' value='发送定位请求' onclick='locationNow("+data[i].imei+")'>"+
										+"		获取助手计步信息<input type='button' value='确定' onclick='getSteps("+data[i].imei+")'><br/>")
							}
						} else {
						}
					}
				});
		
		function   formatDate(now)   {     
            var   year=now.getYear();     
            var   month=now.getMonth()+1;     
            var   date=now.getDate();     
            var   hour=now.getHours();     
            var   minute=now.getMinutes();     
            var   second=now.getSeconds();     
            return   month+"-"+date+"   "+hour+":"+minute+":"+second;     
            }     
		function getSteps(imei){
			$.ajax({
				url : "<%=path%>/test/getSteps",
				type : "post",
				data : {
					"imei" : imei
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						$("#gpsDiv").html("");
						for (var i=(data.length-1);i>=0;i--){
							/* $("#gpsDiv").append("id:"+data[i].pkAssistantInfo); */
							$("#gpsDiv").append("时间:"+formatDate(new Date(data[i].createDate)));
							var str= new Array();
							str = data[i].info.split(",")
							$("#gpsDiv").append("	步数"+str[2]+"<br/>");
						}
					} else {
					}
				}
			});
		} 
	</script>
	<div id = "test">
		<!-- a12312312     设置SOS<input type='text' size='5' id='sosPhonea12312312'> -->
	</div>
	<div id = "gpsDiv">
		<!-- a12312312     设置SOS<input type='text' size='5' id='sosPhonea12312312'> -->
	</div>
</body>
</html>