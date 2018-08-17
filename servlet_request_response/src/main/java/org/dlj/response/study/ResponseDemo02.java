package org.dlj.response.study;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ResponseDemo02 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//downloadFileByOutputStream(response);
		
		downloadFileByPrintWriter(response);
	}
	
	private void downloadFileByOutputStream(HttpServletResponse response) throws IOException {
		// 1.获取要下载的文件的绝对路径
		//String realPath = this.getServletContext().getRealPath("/download/1.jpg");
		String realPath = this.getServletContext().getRealPath("/download/中文.jpg");
		
		// 2.获取要下载的文件名，为防止中文乱码需要使用URLEncoder.encoder进行编码
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		// 3.设置content-disposition响应头控制浏览器以下载的形式打开文件
		response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		// 4.获取要下载的文件输入流
		InputStream in = new FileInputStream(realPath);
		int len = 0;
		// 5.创建数据缓冲区
		byte[] buffer = new byte[1024];
		// 6.通过response对象获取OutputStream流
		OutputStream out = response.getOutputStream();
		// 7.将FileInputStream流写入到buffer缓冲区
		while ((len = in.read(buffer)) > 0) {
			// 8.使用OutputStream将缓冲区的数据输出到客户端浏览器
			out.write(buffer, 0, len);
		}
		in.close();
	}
	
	private void downloadFileByPrintWriter(HttpServletResponse response) throws IOException {
		String realPath = this.getServletContext().getRealPath("/download/中文.png");
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		System.out.println(fileName);
		response.setHeader("content-disposition", "attachment;filement="+URLEncoder.encode(fileName, "utf-8"));
		FileReader in = new FileReader(realPath);
		int len = 0;
		char[] buffer = new char[1024];
		PrintWriter out = response.getWriter();
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len); // 将缓冲区的数据输出到客户端浏览器
		}
		in.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
