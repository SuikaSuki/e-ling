<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.web.WebAttributes,org.springframework.security.core.AuthenticationException,org.springframework.security.authentication.BadCredentialsException" %>
<!DOCTYPE html>
<html>
  <head>
    <title>登录</title>
    <meta content='text/html;charset=utf-8' http-equiv='content-type'>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    
    <link href="assets/bootstrap/bootstrap.css" media="all" rel="stylesheet" type="text/css"/>
    <link href="assets/flatty/light-theme.css" media="all" id="color-settings-body-color" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="assets/eling/elcms/signin/signin.css">
    
  </head>
  <body>
  		<div class="container el-container">
	  		<div class="middle row">
	  			<div class="left col-xs-12 col-md-6">
	  				<div class="left-bg"></div>
	  			</div>
	  			<div class="right col-xs-12 col-md-6">
	  				<div class="right-bg">
	  					<div class="logo"></div>
	  					<div class="login-container">
	       					<form action="<c:url value="/j_spring_security_check"/>" class='validate-form' method='post'>
	          					<div class="form-group">
	          						<div class="controls with-icon-over-input">
	            						<input required placeholder="用户名" class="form-control" name="j_username" id="j_username" type="text">
	            						<i class="icon-user text-muted"></i>
	          						</div>
	          					</div>
	          					<div class="form-group">
	          						<div class="controls with-icon-over-input">
	            						<input required placeholder="密码" class="form-control" name="j_password" id="j_password" type="password">
	          							<i class="icon-lock text-muted"></i>
	  								</div>
	          					</div>
	           					<button id='j_btn_submite' class='loginBtn btn btn-block btn-danger'>登 录</button>
	           					<div class="forgetandregister">
	           						<a href="#" class="forget">忘记密码?</a>
	           						<a href="sign_up.jsp" class="J-register register text-danger hidden">注册</a>
	           					</div>
	                  					<!-- 错误提示信息显示（封存，错误用户名） -->
					                      <%
					                      if("true".equals(request.getParameter("error"))){
					                    	  AuthenticationException ex=(AuthenticationException) request.getSession().getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
					                    	  if(ex instanceof BadCredentialsException){
					                     	%>
					                    	<div style="color:red;clear: both;" class="error">用户名或密码错误，请重新输入</div>
					                    
					                     <% 	
					               			   }else {
					                     	
					                     %>
					                    	  <div style="color:red;clear: both;"><%= ex.getMessage()%></div>
					                    
					                     <% 	
					               			   }; 	
					                     	 };
					                    %> 
	           				</form>
	         	 		</div>
	  				</div>
	  			</div>
	  		</div>
  		</div>
  		<div class="footer"></div>
  		
  		<script type="text/javascript">
	    	localStorage.setItem("ctx","<c:url value='/'/>");
	    </script>
	
		<script id="seajsnode" type="text/javascript" src="assets/seajs/seajs/2.2.1/sea.js"></script>
		<script type="text/javascript" src="assets/config/sea_config.js?randomId=<%=Math.random()%>"></script>
		<script type="text/javascript">
			seajs.use(['$', 'store', "eling/app_config"], function($, store, appConfig) {
				window.$ = window.jQuery = $;
				
				//0.是否显示注册按钮
				if(appConfig.isSignUp){
					$(".J-register").removeClass("hidden");
				}
				
				//1.获取最近使用用户名
				var user = store.get("user") || {};
				var usercode = user.code || "";
				
				//2.清空前台缓存
				store.remove("history");
				
				//3.设置最近使用用户
			    $("#j_username").val(usercode);
			    if(usercode){
			    	$("#j_password").focus();
			    }else{
			    	$("#j_username").focus();
			    }
			});
		</script>
  </body>
</html>
