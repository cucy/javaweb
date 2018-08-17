package org.dlj.request.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用request对象事项请求的转发。
 * 请求转发和请求重定向的区别
 * 	一个web资源接收到客户端请求后，通知服务器去调用另外一个web资源进行处理，称之为请求转发 /307
 * 	一个web资源接收到客户端请求后，通知浏览器去访问另外一个web资源进行处理，称之为请求重定向 /302
 * @author zhxg
 *
 */
public class RequestDemo06 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		String data = "大家好，Hello world！";
		request.setAttribute("data", data);
		request.getRequestDispatcher("/test.jsp").forward(request, response);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		
	}

}
