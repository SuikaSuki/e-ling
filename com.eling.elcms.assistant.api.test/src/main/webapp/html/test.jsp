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
								$("#test").append("imei号："+data[i].imei+"<br/>添加紧急联系人电话:<input type='text' size='15' id='sosPhone"+data[i].imei+"'><input type='button' value='提交' onclick='setSos("+data[i].imei+")'>"
										+"<br/>删除紧急联系人电话<input type='text' size='15' id='sosPhoneDelete"+data[i].imei+"'><input type='button' value='提交' onclick='deleteSos("+data[i].imei+")'>"+
										"<br/>设置主控电话（服务中心电话）<input type='text' size='15' id='setServicePhone"+data[i].imei+"'><input type='button' value='提交' onclick='setServicePhone("+data[i].imei+")'>"+
										"<br/>设置白名单（可拨入电话,一次可设置多个，用','分隔）<input type='text' size='35' id='setWhiteList"+data[i].imei+"'><input type='button' value='提交' onclick='setWhiteList("+data[i].imei+")'>"+
										"<br/> 语音编号：<input type='text' size='15' id='VoiceMessageDownLoadNum"+data[i].imei+"'> 留言下发<input type='button' value='提交' onclick='VoiceMessageDownLoad("+data[i].imei+")'>"+
										"<br/>设置语音提醒（YYYYMMDDhhmm）<input type='text' size='15' id='vocieRemind"+data[i].imei+"'>语音编号：<input type='text' size='15' id='vocieRemindNum"+data[i].imei+"'> <input type='button' value='提交' onclick='vocieRemind("+data[i].imei+")'>"+
										"<br/>语音提醒查询<input type='button' value='提交' onclick='vocieRemindQuery("+data[i].imei+")'><br/>删除语音提醒（YYMMDDhhmm）<input type='text' size='15' id='vocieRemindDelete"+data[i].imei+"'><input type='button' value='提交' onclick='deleteVoiceRemind("+data[i].imei+")'>"+
										/* "<br/>语音标签下发，语音编号：<input type='text' size='15' id='vocieLabelNum"+data[i].imei+"'> <input type='button' value='提交' onclick='vocieLabel("+data[i].imei+")'>"+ */
										"<br/><input type='button' value='恢复出厂设置' onclick='reset("+data[i].imei+")'>"+
										"<br/>")
							}
						} else {
						}
					}
				});
		function reset(imei){
			var r=confirm("确定要恢复出厂设置吗？");
			if(r==false){
				return;
			}
			$.ajax({
				url : "<%=path%>/test/reset",
				type : "post",
				data : {
					"imei" : imei
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						alert("设置成功")
					} else {
					}
				}
			});
		} 
		function setSos(imei){
			$.ajax({
				url : "<%=path%>/test/setSos",
				type : "post",
				data : {
					"imei" : imei,"phone" : $("#sosPhone"+imei).val(),
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} 
		
	
		function deleteSos(imei){
			$.ajax({
				url : "<%=path%>/test/deleteSos",
				type : "post",
				data : {
					"imei" : imei,"phone" : $("#sosPhoneDelete"+imei).val(),
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} 
		function newVoiceMessage(imei){
			$.ajax({
				url : "<%=path%>/test/callSocket",
				type : "post",
				data : {
					"imei" : imei,"para" : "BP27"
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						alert(data);
						/* for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						} */
					} else {
					}
				}
			});
		} 
		function VoiceMessageDownLoad(imei){
			$.ajax({
				url : "<%=path%>/test/callSocket",
				type : "post",
				data : {
					"imei" : imei,"para" : "BP28", "voiceNum":$("#VoiceMessageDownLoadNum"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
							alert(data);
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} 
		function setServicePhone(imei){
			$.ajax({
				url : "<%=path%>/test/setServicePhone",
				type : "post",
				data : {
					"imei" : imei,"phone" : $("#setServicePhone"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
							alert(data);
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		}
		function setWhiteList(imei){
			$.ajax({
				url : "<%=path%>/test/setWhiteList",
				type : "post",
				data : {
					"imei" : imei,"phone" : $("#setWhiteList"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						alert("设置成功");
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		}
		function vocieRemind(imei){
			$.ajax({
				url : "<%=path%>/test/callSocket",
				type : "post",
				data : {
					"imei" : imei,"para" : "BP36","time" : $("#vocieRemind"+imei).val(), "voiceNum":$("#vocieRemindNum"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
							alert(data);
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} 
		function vocieRemindQuery(imei){
			$.ajax({
				url : "<%=path%>/test/callSocket",
				type : "post",
				data : {
					"imei" : imei,"para" : "BP37"
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
							alert(data);
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} function vocieLabel(imei){
			$.ajax({
				url : "<%=path%>/test/callSocket",
				type : "post",
				data : {
					"imei" : imei,"para" : "BP38","voiceNum":$("#vocieLabelNum"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
							alert(data);
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
						}
					} else {
					}
				}
			});
		} 
		function deleteVoiceRemind(imei){
			$.ajax({
				url : "<%=path%>/test/deleteVoiceRemind",
				type : "post",
				data : {
					"imei" : imei,"date" : $("#vocieRemindDelete"+imei).val()
				},
				dataType:"json",
				success : function(data) {
					if (data != null && data != '') {
						for (var i=0;i<data.length;i++){
							//alert(data[i].imei);
							//$("#test").append(data[i].imei+"<br/><test>")
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
</body>
</html>