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
		��&nbsp;&nbsp;����<input type="text" name="userName" /> <br /> �û����� <input
			type="submit" value="Post��ʽ�ύ��">
	</form>

	<form action="<%=request.getContextPath()%>/servlet/RequestDemo05"
		method="get">
		��&nbsp;&nbsp;����<input type="text" name="name" /> <br /> �û����� <input
			type="submit" value="get��ʽ�ύ��">
	</form>

	<a
		href="${pageContext.request.contextPath }/servlet/RequestDemo05?nameName=gacl&name=����� ">���1</a>

	<a
		href="${pageContext.request.contextPath }/servlet/RequestDemo05?nameName=gacl&name=<%=java.net.URLEncoder.encode("�����", "UTF-8") %>">���2</a>
</body>
</html>