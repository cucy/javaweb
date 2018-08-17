package org.dlj.demo2.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9061467307423982932L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 获取用户输入的内容
		String message = request.getParameter("message");
		response.getWriter().write("您上一次的留言是： <br />" + message);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
