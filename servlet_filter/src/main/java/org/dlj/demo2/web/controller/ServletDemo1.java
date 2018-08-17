package org.dlj.demo2.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo1 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -108921870556187413L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 接收参数
		String username = request.getParameter("username");
		// 获取请求方式
		String method = request.getMethod();
		// 获取输出流
		PrintWriter out = response.getWriter();
		out.write("请求的方式： " + method);
		out.write("<br/>");
		out.write("接受到的参数： " + username);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
