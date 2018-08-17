package org.dlj.demo2.web.filter;

import java.io.IOException;

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
 * html转义过滤器
 * @author zhxg
 *
 */
public class HtmlFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		MyHtmlRequest myrequest = new MyHtmlRequest(request);
		chain.doFilter(myrequest, response);
	}
	
	public void destroy() {
		
	}
	
	public void init(FilterConfig filterConfig) {
		
	}
}

class MyHtmlRequest extends HttpServletRequestWrapper {
	
	private HttpServletRequest request;
	
	public MyHtmlRequest(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getParameter(String name) {
		String value = this.request.getParameter(name);
		if (value == null) {
			return null;
		}
		// 调用filter转义value中的html标签
		return filter(value);
	}
	
	private String filter(String message) {
		if (message == null)
			return null;
		char content[] = new char[message.length()];
		message.getChars(0, message.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i=0; i < content.length; i++) {
			switch(content[i]) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
					result.append(content[i]);
			}
		}
		return result.toString();
	}
}
