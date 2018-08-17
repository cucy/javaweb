<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Request对象实现请求转发</title>
</head>
<body>
	使用普通方式取出存储在request对象中的数据：
	<h3 style="color:red;"><%=(String)request.getAttribute("data") %></h3>
	使用El表达式取出存储在request对象中的数据：
	<h3 style="color:red;">${data }</h3>
</body>
</html>