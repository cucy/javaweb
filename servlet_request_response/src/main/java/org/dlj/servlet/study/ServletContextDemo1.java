package org.dlj.servlet.study;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo1 extends HttpServlet {
	// 覆写父类的init方法，出现this.getServletConfig()为null
//	/**
//	 * 定义ServletConfig对象来接受配置的初始化参数
//	 */
//	private ServletConfig config;
//	
//	@Override
//	public void init(ServletConfig config) {
//		this.config = config;
//	}
	/**
	 * 当servlet配置了初始化参数后，web容器在创建servlet实例对象时，
	 * 会自动将这些初始化参数封装到ServletConfig对象中，并在调用servlet的init方法时，
	 * 将ServletConfig对象传递给servlet。进而，程序员通过ServletConfig对象可以得到
	 * 当前servlet的初始化参数信息
	 * @throws IOException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 多个Servlet通过ServletContext对象实现数据共享
		String data = "xdp_gacl";
		/*
		 * Servletconfig对象中维护了ServletContext对象的引用，开发人员在编写servlet时，
		 * 可以通过ServletConfig.getServletContext方法获得ServletContext对象。
		 */
		ServletContext context = this.getServletConfig().getServletContext(); //获取ServletContext对象
		context.setAttribute("data", data); //将data存储到ServletContext对象中
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}

}
