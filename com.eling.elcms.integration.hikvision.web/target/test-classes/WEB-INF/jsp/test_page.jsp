<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'test_page.jsp' starting page</title>
		<meta http-equiv="Content-Type" content="text/html ;charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="">
		.line_div{
			color:#FF0000
		}
	</style>
		<script type="text/javascript" src="js/jquery-1.7.js"></script> 
		<!-- <script type="text/javascript" src="js/jquery.js"></script>-->
		<script type="text/javascript" src="js/jquery.form.js"></script>
		<script type="text/javascript" src="js/ajaxfileupload.js"></script>
		<!-- http://blog.csdn.net/liuzuochen/article/details/1516522 -->
		<script type="text/javascript">
		
		function testJSON(){
		$.ajax({
			url:"test/methodJson.json",
			dataType : 'json',
				success:function(result){
				var obj = result.result;
				var content ="<tr><td>id</td><td>name</td><td>age</td><td>phone</td><td>address</td></tr>";
					$.each(obj,function(i,n){
						content+="<tr>"+
						"<td>"+n.id+
						"</td><td>"+
						n.name+"</td><td>"+
						n.age+"</td><td>"+
						n.phone+"</td><td>"+
						n.address+"</td>"+
						"</tr>";
					});
					$("#id_json_content").html(content);
				},
				error:function(e){
					alert(e.responseText);
				}
		});
		}
		/**
		http://zhidao.baidu.com/question/422305732.html
		
		**/
		function testPicture(){alert(1);
		$.ajax({
			url:"test/testPicture.json?name=james",
			dataType : 'json',
				success:function(result){
					
				var fileName = result.fileNames;
			alert(fileName.length);
				$.each(fileName,function(i,n){
					$("#testPicture_div").append('<br/><input type="checkbox" value="'+n.name+'"/>');
				});
					
				},
				error:function(error){
				alert(error.responseText);
				}
				
			});	
		}
		/**
			上传
		**/
		function testajaxSubmit(){
		/**
		$("#formid_ajaxSubmit").ajaxSubmit({
			success:function(result,status){
				alert(result.msg);
			
			},error:function(error){
				alert(error.responseText);
			}
		});
		
		**/
		
		
		}
		 function ajaxFileUpload()
    {
        
        $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来
        
        $.ajaxFileUpload
        (
            {
                url:'test/ajaxSubmit.json',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'id_file1',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                	var obj = jQuery.parseJSON(data);
                	alert(obj);
                    alert(data.msg);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                    
                    if(typeof(data.error) != 'undefined')
                    {
                        if(data.error != '')
                        {
                            alert(data.error);
                        }else
                        {
                            alert(data.message);
                        }
                    }
                },
                 error: function (data, status, e)//服务器响应失败处理函数
                {
                alert("error:"+data.responseText);
                }
            }
        );
        
        return false;

    }
    
    function getIP(){
    	$.ajax({
			url:"test/getRealIP.json",
			dataType : 'json',
				success:function(result){
				alert(result.msg);
				}
			});
    	
    }
    
    
     function uploadImages(type) {
$.ajaxFileUpload({
		//跟具updateP得到不同的上传文本的ID
                 url:'test/uploadImg.json?updateP='+type,             //需要链接到服务器地址
                 secureuri:false,
                 fileElementId:''+type+'',                         //文件选择框的id属性（必须）
                 dataType: 'json',  
                 success: function (data){  
                 var data = eval("("+data+")");
					if(data.updateP=='uploada'){
						document.getElementById("显示文件访问路径文本的IDA").value=baseurl+"/upload/"+data.fileName;
					}else if(data.updateP=='uploadb'){
						document.getElementById("显示文件访问路径文本的IDB").value=baseurl+"/upload/"+data.fileName;
					}else if(data.updateP=='uploadc'){
						document.getElementById("显示文件访问路径文本的IDC").value=baseurl+"/upload/"+data.fileName;
					}
			alert('上传失败1'+data);				
                 },
                 error: function (data, status, e){  
                 alert("error");
                var result = jQuery.parseJSON(data);
					alert(result);
                 }
               }
            );

}
function changePage(){
	window.location.href="/test/changePage.jspx";
}
	</script>
	</head>

	<body>
		<a href="test/methodOne.do">.do请求</a>
		<a href="test/changePage.do">.do请求</a>
		<a href="test/changejspx.jspx">.jspx请求跳转页面</a>
		<button onClick="testJSON();">ajax返回JSON</button>
		<a href="test/getAllStudent.json">数据库取数据</a>
		<div>
			<table id="id_json_content" border="2"
				style="border: 1px solid #EE0000;" cellspacing="2" cellpadding="2">
			</table>
		</div>
		


<div class="line_div">form表单上传============================================</div>
<!-- 测试上传 -->
<form enctype="multipart/form-data"  action="test/testUpload.json" method="post">  
    <input type="file" name="file1" /> <input type="text" name="alais" /><br />  
    <input type="file" name="file2" /> <input type="text" name="alais" /><br />  
    <input type="file" name="file3" /> <input type="text" name="alais" /><br />  
    <input type="submit" value="上传" />  
</form> 
<div id="testPicture_div">测试上传div</div>
<div class="line_div">============================================</div>
<!-- 测试forward 和 redirect -->
<a href="test/testURL.do?name=123">testURL</a>
<input type="button" onClick="testPicture();"/>
<div class="line_div">============================================</div>
<input type="button" value="获得ip" onClick="getIP();"/> 


<form action="test/ajaxSubmit.json?name=james"  id="formid_ajaxSubmit" method="post">
 <input type="file" id="id_file1" name="file1" /> <input type="text" name="alais" /><br />  
    <input type="file" name="file2" /> <input type="text" name="alais" /><br />  
	<input type="button" value="测试ajaxSubmit" onClick="testajaxSubmit();"  />  
</form>
 <img src="images/loding.gif" id="loading" style="display: none;">
        <input type="file" id="file" name="file" />
        <br />
        <input type="button" value="上传" onclick="ajaxFileUpload();">
  
  <div class="line_div">ajax 上传============================================</div>
  <form action="#" name="myform" method="post">
<input type='file'  id="uploada" name="uploada" size='50'/>
<input type="button" style="width: 5em"  onclick="uploadImages('uploada')" value='上传'/><br>
<input type='file'  id="uploadb" name="uploadb" size='50'/>
<input type="button" style="width: 5em"  onclick="uploadImages('uploadb')" value='上传'/><br>
<input type='file'  id="uploadc" name="uploadc" size='50'/>
<input type="button" style="width: 5em"  onclick="uploadImages('uploadc')" value='上传'/><br>
</form>
	<a href="test/changePage.do" onClick="">跳转至分页页面</a>
	
	<a href="test/test404.jspx">测试404页面</a>
	</body>
</html>
