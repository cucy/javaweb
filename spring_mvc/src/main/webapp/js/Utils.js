// 立即执行的js
(function() {
	// 获取contextPath
	var contextPath = getContextPath();
	// 获取basePath
	var basePath = getBasePath();
	// 将获取到contextPath和basePath分别赋值给window对象的g_contextPath属性和g_basePath属性
	window.g_contextPath = contextPath;
	window.g_basePath = basePath;
})();

/**
 * 获取项目根路径，等价于jsp页面中
 * <%
 * 		Stromg basePath = request.getScheme() + "://" + request.getServerName() + ":" + 
 * 							request.getServerPort() + path + "/";
 * %>
 */ 
function getBasePath() {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0, pos);
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPath + projectName);
}

/**
 *	获取web应用的contextPath，等价于jsp页面中
 *	<%
 *		String path = request.getContextPath();
 *	%>
 *	@returns /项目名称(/EasyUIStudy_20180523)
 */
function getContextPath() {
	return window.document.location.pathname.substring(0, window.document.location.pathname.indexOf('\/', 1));
}