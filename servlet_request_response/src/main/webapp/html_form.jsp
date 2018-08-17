<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Html的Form表单元素</title>
	</head>
	<body>
		<fieldset style="width:500px;">
			<form action="${pageContext.request.contextPath}/servlet/RequestDemo03" method="post">
				编&nbsp;&nbsp;号:
				<input type="text" name="userid" value="NO." size="2" maxlength="2"><br/>
				用户名：
				<input type="text" name="username" value="请输入用户名"><br/>
				密&nbsp;&nbsp;码：
				<input type="password" name="userpass" value="请输入密码"><br />
				性&nbsp;&nbsp;别：
				<input type="radio" name="sex" value="男" checked>男
				<input type="radio" name="sex" value="女">女<br/>
				部&nbsp;&nbsp;门：
				<select name="dept">
					<option value="技术部">技术部</option>
					<option value="销售部" selected>销售部</option>
					<option value="财务部">财务部</option>
				</select><br/>
				兴&nbsp;&nbsp;趣：
				<input type="checkbox" name="inst" value="唱歌">唱歌
				<input type="checkbox" name="inst" value="游泳">游泳
				<input type="checkbox" name="inst" value="跳舞">跳舞
				<input type="checkbox" name="inst" value="编程" checked>编程
				<input type="checkbox" name="inst" value="上网">上网<br/>
				
				说&nbsp;&nbsp;明：
				<textarea name="note" cols="34" rows="5"></textarea><br/>
				<input type="hidden" name="hiddenField" value="hiddenvalue" />
				<input type="submit" value="提交" />
				<input type="reset" value="重置" />
			</form>
		</fieldset>
	</body>
</html> 