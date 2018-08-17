<%@ page language="java" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE HTML>
<html>
<body>
	index, hello!
	<form action="${pageContext.request.contextPath }/servlet/CancelAutoLoginServlet"
		method="post">
		<input type="submit" value="退出登录">
	</form>
</body>
</html>