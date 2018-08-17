<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>cookie回显提示</title>
<script type="text/javascript">
	function getCookie(c_name) {
		console.log(c_name);
		console.log(document.cookie);
		if (document.cookie.length > 0) {
			c_start = document.cookie.indexOf(c_name + "=")
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1
				c_end = document.cookie.indexOf(";", c_start)
				if (c_end == -1)
					c_end = document.cookie.length
				// unescape已不推荐使用，
				// 可以使用decodeURI() 函数可对 encodeURI() 函数编码过的 URI 进行解码
				// 使用decodeURIComponent() 对encodeURIComponent()编码的uri进行解码
					return decodeURI(document.cookie.substring(c_start, c_end))
			}
		}
		return ""
	}

	/*
	function setCookie(c_name, value, expiredays) {
		var exdate = new Date()
		exdate.setDate(exdate.getDate() + expiredays)
		document.cookie = c_name
				+ "="
				+ escape(value)
				+ ((expiredays == null) ? "" : "; expires="
						+ exdate.toGMTString())
	}
	*/
	function checkCookie() {
		userName = getCookie('userName')
		if (userName != null && userName != "") {
			console.log('Welcome again ' + userName + '!');
			alert('Welcome again ' + userName + '!')
		} else {
			console.log("userName is null");
			alert("userName is null")
		}
	}
</script>
</head>
<body onLoad="checkCookie()"></body>
</html>