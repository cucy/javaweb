<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>RequestAndSessionAttributeListener监听器测试</title>
	</head>
	<body>
		<%
			// 向session域对象中添加属性
			session.setAttribute("aa", "bb");
			// 替换session域对象中aa属性的值
			session.setAttribute("aa", "xx");
			// 移除session域对象中aa的属性
			session.removeAttribute("aa");
			
			// 向request域对象中添加属性
			request.setAttribute("aa", "bb");
			request.setAttribute("aa", "xx");
			request.removeAttribute("aa");
		%>
	</body>
</html>