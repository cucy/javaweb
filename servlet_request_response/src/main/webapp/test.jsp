<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Request����ʵ������ת��</title>
</head>
<body>
	ʹ����ͨ��ʽȡ���洢��request�����е����ݣ�
	<h3 style="color:red;"><%=(String)request.getAttribute("data") %></h3>
	ʹ��El���ʽȡ���洢��request�����е����ݣ�
	<h3 style="color:red;">${data }</h3>
</body>
</html>