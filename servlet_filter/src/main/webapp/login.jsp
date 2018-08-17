<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>login</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/LoginServlet"
		method="post">
		用户名： <input type="text" name="username" value="孤傲苍狼" />
		密   码： <input type="password" name="password" />	
			  <%-- <input type="hidden" name="logintime" value="<% out.print(new java.util.Date().getTime()); %>" /> --%>
			   <input type="hidden" name="logintime" value="4" />
			   <input type="submit" value="post方式提交">
	</form>
</body>
</html>