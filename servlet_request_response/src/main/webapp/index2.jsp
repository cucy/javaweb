<%--@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"--%>
<%--@ page language="java" import="java.util.*" pageEncoding="GB2312" --%>
<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/servlet/RequestDemo05"
		method="post">
		姓&nbsp;&nbsp;名：<input type="text" name="userName" /> <br /> 用户名： <input
			type="submit" value="Post方式提交表单">
	</form>

	<form action="<%=request.getContextPath()%>/servlet/RequestDemo05"
		method="get">
		姓&nbsp;&nbsp;名：<input type="text" name="name" /> <br /> 用户名： <input
			type="submit" value="get方式提交表单">
	</form>

	<a
		href="${pageContext.request.contextPath }/servlet/RequestDemo05?nameName=gacl&name=徐培达 ">点击1</a>

	<a
		href="${pageContext.request.contextPath }/servlet/RequestDemo05?nameName=gacl&name=<%=java.net.URLEncoder.encode("徐培达", "UTF-8") %>">点击2</a>
</body>
</html>