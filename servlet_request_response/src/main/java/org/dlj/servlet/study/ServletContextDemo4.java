package org.dlj.servlet.study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo4 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = "<h1><font color='red'>abcde</font></h1>";
		response.getOutputStream().write(data.getBytes());
		ServletContext context = this.getServletContext(); // 获取ServletContext对象
		RequestDispatcher rd = context.getRequestDispatcher("/servlet/ServletContextDemo5"); // 获取请求转发对象
		rd.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
