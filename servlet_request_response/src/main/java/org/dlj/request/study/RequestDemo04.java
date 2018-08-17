package org.dlj.request.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo04 extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
		/*
		 *  客户端post请求传递中文参数，服务器接受参数出现乱码，
		 *  之所以会出现乱码，是因为服务器和客户端沟通的编码不一致造成的，
		 *  解决办法：客户端和服务器之间设置一个统一的编码，之后就按照这个编码进行数据的传输和接受
		 */
//		String userName = request.getParameter("userName");
//		System.out.println("userName: " + userName);
		
		/*
		 * 客户端已UTF-8编码传输数据到服务器端的，所以需要设置服务器端以UTF-8的编码进行接收，
		 * 否则对于中文数据就会产生乱码
		 */
		request.setCharacterEncoding("utf-8");
		String userName = request.getParameter("userName");
		System.out.println("userName: " + userName);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//this.doPost(request, response);
		String name1 = request.getParameter("name");
		System.out.println("get before setCharacterEncoding utf-8 name: " + name1);
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		System.out.println("get after  setcharacterencoding utf-8 name: " + name);
		
		/*
		 * 获取request对象以ISO8859-1字符编码接收到的原始数据的字节数组，
		 * 然后通过字节数组以指定的编码构建字符串，解决乱码问题
		 */
		System.out.println(" name : " + new String(name.getBytes("ISO8859-1"), "UTF-8"));
		
	}

}
