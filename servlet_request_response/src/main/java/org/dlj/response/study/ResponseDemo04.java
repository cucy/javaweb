package org.dlj.response.study;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求重定向：一个web资源收到客户端请求后，通知客户端去访问另外一个web资源
 * 应用场景： 用户登录，用户首先访问登录页面，登录成功后，就会跳转到某一个页面
 * 实现方式：response.sendRedirect(String location)
 * 
 * sendRedirect()内部的实现原理：使用response设置302状态码和设置location响应头实现重定向
 * @author zhxg
 *
 */
public class ResponseDemo04 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 1.调用sendRedirect方法实现请求重定向
		 * sendRedirect方法内部调用了
		 * response.setHeader("Location", "servlet_request_response/Index.jsp");
		 * response.setStatus(HttpServletResponse.SC_FOUND); // 设置302状态码
		 */
		System.out.println(request.getContextPath() +  "/index.jsp");
		//response.sendRedirect("/index.jsp");
		response.sendRedirect(request.getContextPath() +  "/index.jsp");
		
		/*
		 * 2.使用response设置302状态码和location响应头实现重定向
		 */
//		response.setHeader("Location", request.getContextPath() + "/index.jsp");
//		response.setStatus(HttpServletResponse.SC_FOUND);
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
