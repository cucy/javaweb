package org.dlj.demo1.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * filter的生命周期：
 * 	Filter的创建：
 * 	filter的创建和销毁是由web服务器负责。web应用程序启动时，web服务器将创建Filter的实例对象
 * 并调用init方法，完成对象的初始化功能，从而为后续的用户请求做好拦截的准备工作，filter对象只会创建一次，
 * init方法也只会执行一次，通过init方法的参数，可获得代表filter配置信息的FilterConfig对象
 * 
 * 	Filter的销毁：
 * 	Web容器调用destroy方法销毁Filter。destroy方法在Filter的生命周期中仅执行一次。在
 * destroy方法中，可以释放过滤器使用的资源。
 * @author zhxg
 *
 */
public class FilterDemo01 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("---FilterDemo01 过滤器初始化---");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 对request和response进行一些预处理
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("FilterDemo01执行前！！！");
		chain.doFilter(request, response); // 让目标资源执行，放行
		System.out.println("FilterDemo01执行后！！！");
	}
	
	@Override
	public void destroy() {
		System.out.println("----FilterDemo01 过滤器销毁----");
	}
}
