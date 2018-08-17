package org.dlj.response.study;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo01 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 使用OutputStream流输出中文
		//outputChineseByOutputStream(response);
		// 使用PrintWriter流输出中文
		//outputChineseByWriter(response);
		
		//outputOneByOutputStream(response);
		outputOneByPrintWriter(response);
		
	}
	
	/**
	 * 使用OutputStream流向浏览器输出中文
	 * @param response
	 * @throws IOException
	 */
	private void outputChineseByOutputStream(HttpServletResponse response) throws IOException {
		
		String data = "中国";
		OutputStream outputStream = response.getOutputStream();
		response.setHeader("content-type", "text/html;charset=UTF-8");
		/*
		 * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
		 * 如果是中文的操作系统环境，默认就是查找Gb2312的码表，
		 * 将字符转换为字节数组的过程就是将中文字符转换为GB2312的码表上对应的数字
		 * 比如：“中”在GB2312的码表上对应的数字是98
		 * 		“国”在GB2312的码表上对应的数字是99
		 * 
		 * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，
		 * 如果是中文操作系统，那么就是用gb2312的码表
		 */
		byte[] dataByteArr1 = data.getBytes();
		System.out.println(Arrays.toString(dataByteArr1));
		byte[] dataByteArr = data.getBytes("UTF-8");
		outputStream.write(dataByteArr); // 使用OutputStream流向客户端输出字节数组
	}
	
	/**
	 * 使用printWriter向浏览器输出中文
	 * 当需要向浏览器输出字符数据时，使用PrintWriter比较方便，省去了将字符转换成字节数组那一步
	 * @param response
	 * @throws IOException
	 */
	private void outputChineseByWriter(HttpServletResponse response) throws IOException {
		String data = "中国";
		// 设置将字符以UTF-8编码输出到客户端浏览器
		response.setCharacterEncoding("UTF-8");
		// 获取PrintWriter输出流，注意这两者的先后顺序
		PrintWriter out = response.getWriter();
		//response.setHeader("content-type", "text/html;charset=utf-8");
		/*
		 * 多学一招：使用html语言中的<meta>标签来控制浏览器行为，模拟通过设置响应头控制浏览器行为
		 * out.write("<meta http-equiv='content-type' content='text/html;charset=utf-8'/>")
		 * 等同于
		 * response.setHeader("content-type", text/html;charset=UTF-8");
		 */
		out.write("<meta http-equiv='content-type' content='text/html;charset=utf-8'/>");
		// 使用PrintWriter流向客户端输出字符
		out.write(data);
	}
	
	/**
	 * 使用outputStream输出数字到浏览器
	 * 在开发过程中，如果希望服务器输出什么浏览器就能看到什么，那么在服务器端都要
	 * 以字符串形式进行输出。
	 * @param response
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public void outputOneByOutputStream(HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write("使用OutputStream流输出数字1： ".getBytes("utf-8"));
		// 以int类型将1流入outputStream输出流中，则浏览器中不显示这个数字
		//outputStream.write(1);
		outputStream.write(String.valueOf(1).getBytes("utf-8"));
	}
	
	/**
	 * 使用PrintWriter输出数字到浏览器
	 * @param response
	 * @throws IOException
	 */
	public void outputOneByPrintWriter(HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter(); 
		out.write("使用PrintWriter流输出数字1：");
		// 以int类型将1流入PrintWriter输出流中，则浏览器中不显示这个数字
		//out.write(1);
		out.write(1 + "");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
