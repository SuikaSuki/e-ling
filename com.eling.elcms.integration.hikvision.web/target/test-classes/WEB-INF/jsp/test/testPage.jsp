<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'testPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-1.7.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		$(document).ready(function(){
			queryPageData();
		});
		
		function nextPage(){
			var currentPage = $("#currentPage").val();
			if(currentPage!=""&&currentPage!=undefined){
				currentPage = parseInt(currentPage)+1;
				$.ajax({
					url:"test/getPageData.json",
					dataType:"json",
					data:{"currentPage":currentPage},
					success:function(result){
						var data = result.data;
						var obj = "";
							  	$.each(data,function(i,n){
							  	obj+='<tr>'
							  	+'<td>'+n.id+'</td>'+'<td>'+n.name+'</td>'+'<td>'+n.age+'</td>'+'<td>'+n.phone+'<td>'+n.email+'</td>'
							  	+'</tr>';
						
							});
						$("#table_content").html(obj);
						$("#currentPage").val(currentPage);
					},error:function(e){
							alert(e.responseText);
						
					}
				});
			}else{
				alert("waring!");
			}
		}
		function backPage(){
			var currentPage = $("#currentPage").val();
			if(currentPage!=""&&currentPage!=undefined){
				currentPage = parseInt(currentPage)-1;
				$.ajax({
					url:"test/getPageData.json",
					dataType:"json",
					data:{"currentPage":currentPage},
					success:function(result){
						var data = result.data;
						var obj = "";
				
							  	$.each(data,function(i,n){
							  	obj+='<tr>'
							  	+'<td>'+n.id+'</td>'+'<td>'+n.name+'</td>'+'<td>'+n.age+'</td>'+'<td>'+n.phone+'<td>'+n.email+'</td>'
							  	+'</tr>';
						
							});
						$("#table_content").html(obj);
						$("#currentPage").val(currentPage);
					}
				});
			}else{
				alert("waring!");
			}
				
		}
		
		function queryPageData(){
		var currentPage = $("#currentPage").val();
		
			$.ajax({
				url:"test/getPageData.json",
				dataType:"json",
				data:{"currentPage":currentPage},
				success:function(result){
				var data = result.data;
					var obj = "";
			
						  	$.each(data,function(i,n){
						  	obj+='<tr>'
						  	+'<td>'+n.id+'</td>'+'<td>'+n.name+'</td>'+'<td>'+n.age+'</td>'+'<td>'+n.phone+'<td>'+n.email+'</td>'
						  	+'</tr>';
					
						});
					$("#table_content").html(obj);
					$("#currentPage").val(currentPage);
				}
			});
		}
		function setSession(){
			$.ajax({
				url:"test/setSession.json",
				dataType:"json",
				success:function(result){
					alert(result.testSession);
					}
				});
			
		}
		function getSession(){
			$.ajax({
				url:"test/getSession.json",
				dataType:"json",
				success:function(result){
					alert(result.testSession);
					}
				});
			
		}
	</script>
  </head>
  
  <body>
  <a href="javascript:void(0);" onClick="setSession();">setSession</a>
  <a href="javascript:void(0);" onClick="getSession();">getSession</a>
  
		<input type="test" value="123"/>
		<table id="table_content">
			
		</table>
		<a href="javascript:void(0);" onClick="backPage();">backPage</a> <a href="javascript:void(0);" onClick="nextPage();">nextPage</a><input id="currentPage" type="text" value="1" style="width:20px;">
  </body>
</html>
