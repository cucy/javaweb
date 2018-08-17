package org.dlj.servlet.study;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 使用ServletContext读取资源文件
 * @author zhxg
 *
 */
public class ServletContextDemo6 extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("content-type", "text/html;charset=utf-8");
		readSrcDirPropCfgFile(response); // 读取src/main/java目录下的properties配置文件
		response.getWriter().println("<hr/>");
		readWebRootDirPropCfgFile(response); // 读取webRoot目录下的properties配置文件
		response.getWriter().println("<hr/>");
		readPropCfgFile(response); // 读取src/main/java目录下的org.dlj包中的db3.properties配置文件
		response.getWriter().print("<hr/>");
		readPropCfgFile2(response); // 读取src/main/java目录下的org.dlj.servlet.study包中的db4.properties配置文件
	}
	
	private void readPropCfgFile2(HttpServletResponse response) throws IOException {
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/org/dlj/servlet/study/db4.properties");
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("读取src/main/java目录下org.dlj.servlet.study包中的db4.properties配置文件：");
		response.getWriter().println(MessageFormat.format("server.name={0}", serverName));
		
	}
	
	private void readPropCfgFile(HttpServletResponse response) throws IOException {
		// 通过ServletContext获取web资源的绝对路径
		String path = this.getServletContext().getRealPath("/WEB-INF/classes/org/dlj/db3.properties");
		System.out.println("servletContext.getRealPath: " + path);
		InputStream in = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("读取src/main/java目录下的db.config包中的db3.properties配置文件");
		response.getWriter().println(MessageFormat.format("server.name={0}", serverName));
	}
	
	private void readWebRootDirPropCfgFile(HttpServletResponse response) throws IOException {
		/*
		 * 通过ServletContext对象读取WebRoot目录下的properties配置文件
		 * ‘/’代表的是项目根目录
		 */
		InputStream in = this.getServletContext().getResourceAsStream("/db2.properties");
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("读取WebRoot目录下的db2.properties配置文件: ");
		response.getWriter().print(MessageFormat.format("server.name={0}", serverName));
	}

	private void readSrcDirPropCfgFile(HttpServletResponse response) throws IOException {
		/*
		 * 通过ServletContext对象读取src目录下的db1.properties配置文件
		 */
		InputStream in = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
		Properties prop = new Properties();
		prop.load(in);
		String serverName = prop.getProperty("server.name");
		response.getWriter().println("读取src/main/java目录下的db1.properties配置文件：");
		response.getWriter().println(MessageFormat.format("server.name: {0}", serverName));
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
