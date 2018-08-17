package org.dlj.servlet.study;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo5 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String data = "abcdefghijklmnopqrst";
		/*
		 * 设置数据合理的缓存时间值，以避免浏览器频繁项服务器发送请求，提升服务器的性能
		 * 这里设置数据缓存时间为1天
		 */
		response.setDateHeader("expires", System.currentTimeMillis() + 24 * 3600 *1000);
		response.getOutputStream().write(data.getBytes());
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

}
