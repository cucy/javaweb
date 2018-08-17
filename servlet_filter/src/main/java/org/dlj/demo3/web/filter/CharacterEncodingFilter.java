package org.dlj.demo3.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * 此过滤器用来解决全站中文编码乱码问题
 * @author zhxg
 *
 */
public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig = null;
	// 设置默认的字符编码
	private String defaultCharset = "UTF-8";
	
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String charset = filterConfig.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		
		MyCharacterEncodingRequest requestWrapper = new MyCharacterEncodingRequest(request);
		chain.doFilter(requestWrapper, response);
	}
	
	public void init(FilterConfig filterConfig) {
		// 得到过滤器的初始化配置信息
		this.filterConfig = filterConfig;
	}
	
	public void destroy() {
		
	}
	
}

class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest request;
	public MyCharacterEncodingRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String value = this.request.getParameter(name);
		if (value == null)
			return null;
		
		if (!this.request.getMethod().equalsIgnoreCase("get")) {
			return value;
		} else {
			// 如果是以get方式提交数据的，就对获取到的值进行转码处理
			try {
				value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return value;
		}
	}
}