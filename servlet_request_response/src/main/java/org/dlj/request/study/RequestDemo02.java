package org.dlj.request.study;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo02 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Enumeration<String> reqHeadInfos = request.getHeaderNames(); // 获取所有的请求头
		out.write("获取到的客户端所有的请求头信息如下：");
		out.write("<hr />");
		while(reqHeadInfos.hasMoreElements()) {
			String headName = reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName); // 根据请求头的名字获取对应的请求头的值；
			out.write(headName + ":" + headValue);
			out.write("<br/>");
		}
		out.write("<br/>");
		out.write("获取到的客户端Accept-Encoding请求头的值: ");
		out.write("<br/>");
		String value = request.getHeader("Accept-Encoding"); // 获取Accept-Encoding请求头对应的值
		out.write(value);
		
		Enumeration<String> e = request.getHeaders("Accept-language");
		while(e.hasMoreElements()) {
			String str = e.nextElement();
			System.out.println(str + "<>");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
