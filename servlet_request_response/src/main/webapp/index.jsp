<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>request接收中文参数乱码问题</title>
	</head>
	<body>
		<form action="<%=request.getContextPath() %>/servlet/RequestDemo04" method="post">
			姓&nbsp;&nbsp;名：<input type="text" name="userName" /> <br/>
			用户名：
			<input type="submit" value="Post方式提交表单" >
		</form>
		<img alt="沙漠" src="http://localhost:18080/servlet_request_response/servlet/ResponseImage">
		<form action="<%=request.getContextPath() %>/servlet/RequestDemo04" method="get">
			姓&nbsp;&nbsp;名：<input type="text" name="name" /> <br/>
			用户名：
			<input type="submit" value="get方式提交表单" >
		</form>
		
		<a href="${pageContext.request.contextPath }/servlet/RequestDemo04?nameName=gacl&name=徐培达 ">点击1</a>
		
		<a href="${pageContext.request.contextPath }/servlet/RequestDemo04?nameName=gacl&name=<%=java.net.URLEncoder.encode("徐培达", "UTF-8") %>">点击2</a>
	</body>
</html>