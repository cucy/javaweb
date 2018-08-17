package org.dlj.demo1.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo02 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) {
		System.out.println("---FilterDemo02过滤器初始化---");
		// 得到过滤器的名字
		String filterName = filterConfig.getFilterName();
		// 得到在web.xml文件中配置的初始化参数
		String initParam1 = filterConfig.getInitParameter("name");
		String initParam2 = filterConfig.getInitParameter("like");
		// 返回过滤器的所有初始化参数的名字的枚举集合
		Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
		
		System.out.println(filterName);
		System.out.println(initParam1);
		System.out.println(initParam2);
		while (initParameterNames.hasMoreElements()) {
			String paramName = (String)initParameterNames.nextElement();
			System.out.println(paramName);
		}
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("FilterDemo02执行前！！！");
		chain.doFilter(request, response); // 让目标资源执行， 放行
		System.out.println("FilterDemo02执行后！！！");
	}
	
	@Override
	public void destroy() {
		System.out.println("---FilterDemo02过滤器销毁---");
	}
}
