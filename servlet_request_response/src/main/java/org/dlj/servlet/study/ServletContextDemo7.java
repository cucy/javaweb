package org.dlj.servlet.study;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用类装载器读取资源文件
 * 通过类装载器读取资源文件的注意事项：不适合装载大文件，否则会导致JVM内存溢出
 * @author zhxg
 *
 */
public class ServletContextDemo7 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		test1(response);
		response.getWriter().println("<hr />");
		test2(response);
		response.getWriter().println("<hr />");
		test3();
		//test4();
	}

	/**
	 * 读取类路径下的资源文件
	 * @param response
	 * @throws IOException 
	 */
	private void test1(HttpServletResponse response) throws IOException {
		// 获取到装载当前类的类装载器
		ClassLoader loader = ServletContextDemo7.class.getClassLoader();
		// 用类装载器读取src目录下的db1.properties配置文件
		InputStream in = loader.getResourceAsStream("db1.properties");
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("用类装载器读取src目录下的db1.properties配置文件：");
		response.getWriter().print(MessageFormat.format("server.name: {0}", serverName));
		
	}
	
	/**
	 * 读取类路径下包下面的资源文件
	 * @param response
	 * @throws IOException
	 */
	private void test2(HttpServletResponse response) throws IOException {
		// 获取到装载当前类的类装载器
		ClassLoader loader = ServletContextDemo7.class.getClassLoader();
		// 用类装载器读取src目录下的org.dlj.servlet.study包中的db4.properties配置文件
		InputStream in = loader.getResourceAsStream("org/dlj/servlet/study/db4.properties");
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("用类装载器读取src目录下的org.dlj.servlet.study包中的db4.properties配置文件: ");
		response.getWriter().println(MessageFormat.format("server.name={0}", serverName));
		
	}
	
	/**
	 * 通过类加载器读取资源文件的注意事项不适合加载大文件，否则会导致jvm内存溢出
	 * @throws IOException 
	 */
	private void test3() throws IOException {
		/*
		 * 01.avi是一个150多M的文件，使用类加载器去读取这个大文件时会导致内存溢出：
		 * 	java.lang.OutOfMemoryError: Java heap space
		 */
		InputStream in = ServletContextDemo7.class.getClassLoader().getResourceAsStream("01.mp4");
		
		byte buffer[] = new byte[1024];
		int len = 0;
		OutputStream out = new FileOutputStream("e:\\1.mp4");
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
		//System.out.println(in);
	}
	
	/**
	 * 读取01.avi，并拷贝到e:\根目录下
	 * 01.avi文件太大，只能用servletContext去读取
	 * @throws IOException
	 */
	private void test4() throws IOException {
		// path = E:\01.avi
		// path = 01.avi
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/01.avi");
		String filename = path.substring(path.lastIndexOf("\\"));
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/01.avi");
		byte buffer[] = new byte[1024];
		int len = 0;
		OutputStream out = new FileOutputStream("e:\\" + filename);
		while ((len = in.read(buffer)) > 0) {
			out.write(buffer, 0, len);
		}
		out.close();
		in.close();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
