package org.dlj.request.study;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo03 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 客户端是以utf-8编码提交表单数据的，所以需要设置服务器端以utf-8的编码进行接收，否则对于中文数据就会产生乱码
		request.setCharacterEncoding("utf-8");

		String userid = request.getParameter("userid");
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		String sex = request.getParameter("sex");
		String dept = request.getParameter("dept");
		/*
		 * 获取选中的兴趣，因为可以选中多个值，所以获取到的值是一个字符串数组， 因此需要使用getParameterValues方法来获取
		 */
		String[] insts = request.getParameterValues("inst");
		String insts2 = request.getParameter("inst");
		System.out.println("兴趣多选框： " + insts2);
		String note = request.getParameter("note");
		String hiddenField = request.getParameter("hiddenField");

		String instStr = "";
		/*
		 * 获取数组数据技巧，可以避免insts数组为null时引发的空指针异常错误
		 */
		for (int i = 0; insts != null && i < insts.length; i++) {
			instStr += insts[i] + ",";
		}
		String htmlStr = "<table>" + "<tr><td>填写的编号：</td><td>{0}</td></tr>" + "<tr><td>填写的用户名：</td><td>{1}</td></tr>"
				+ "<tr><td>填写的密码：</td><td>{2}</td></tr>" + "<tr><td>选中的性别：</td><td>{3}</td></tr>"
				+ "<tr><td>选中的部门：</td><td>{4}</td></tr>" + "<tr><td>选中的兴趣：</td><td>{5}</td></tr>"
				+ "<tr><td>填写的说明：</td><td>{6}</td></tr>" + "<tr><td>隐藏域的内容：</td><td>{7}</td></tr>" + "</table>";
		htmlStr = MessageFormat.format(htmlStr, userid,username,userpass,sex,dept,instStr.substring(0, instStr.length() - 1),note,hiddenField);
		
		// 设置服务端以UTF-8编码输出数据到客户端
		response.setCharacterEncoding("utf-8");
		// 设置客户端浏览器以utf-8编码解析数据
		response.setContentType("text/html;charset=utf-8");
		// 输出htmlStr里面的内容到客户端浏览器显示
		response.getWriter().write(htmlStr);
		
		// 服务器端使用getParameterNames方法接收表单参数
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			String value = request.getParameter(name);
			System.out.println(MessageFormat.format("{0}={1}", name, value));
		}
		
		// 服务器端使用getParameterMap方法接受表单参数
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			String paramName = entry.getKey();
			String[] paramValues = entry.getValue();
			String paraVal = "";
			for (int i = 0; paramValues != null && i < paramValues.length; i++) {
				paraVal += paramValues[i] + ",";
			}
			System.out.println(MessageFormat.format("-->> {0}={1}", paramName, paraVal.substring(0, paraVal.length() - 1)));
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
