<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>"/"代表webapps目录的常见应用场景</title>
		<%--使用绝对路径的方式引入js脚本 --%>
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/index.js"></script>
		<%--${pageContext.request.contextPath} 与request.getContextPath()写法得到的结果是一样的 --%>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
		
		<%--使用绝对路径的方式引入css样式 --%>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/index.css" type="text/css" />
		
	</head>
	
	<body>
		<%--form 表单提交 --%>
		<form action="${pageContext.request.contextPath}/servlet/CheckServlet" method="post">
			<input type="submit" value="提交">
		</form>
		
		<%--超链接跳转页面 --%>
		<a href="${pageContext.request.contextPath }/index.jsp">跳转到首页</a>
	</body>
</html>