package org.dlj.demo3.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 禁止浏览器缓存所有动态页面
 * 	有3个http响应字段都可以禁止浏览器缓存当前页面，这三个http header不同的浏览器支持可能不同
 * response.setDateHeader("Expires", -1);
 * response.setHeader("Cache-Control", "no-cache");
 * resposne.setHeader("Pragma", "no-cache");
 * 
 * @author zhxg
 *
 */
public class NoCacheFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		System.out.println(response.getHeaderNames());
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig config) {
		
	}
	
	public void destroy() {
		
	}
}
