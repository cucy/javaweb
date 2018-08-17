package org.dlj.demo2.web.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
 * 这个过滤器是用来解决中文乱码，转义内容中的html标签，过滤内容中的敏感字符
 * 
 * @author zhxg
 *
 */
public class AdvancedFilter implements Filter {

	private FilterConfig filterConfig = null;
	// 设置默认的字符编码
	private String defaultCharset = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) {
		// 得到过滤器的初始化配置信息
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		// 得到在web.xml中配置的字符编码
		String charset = filterConfig.getInitParameter("charset");
		if (charset == null) {
			charset = defaultCharset;
		}
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		response.setContentType("text/html;charset=" + charset);
		
		AdvancedRequest requestWrapper = new AdvancedRequest(request);
		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {
		
	}
	
	class AdvancedRequest extends HttpServletRequestWrapper {
		private List<String> dirtyWords = getDirtyWords();
		
		// 定义一个变量记住被增强对象
		private HttpServletRequest request;
		
		// 定义一个构造函数，接受被增强对象
		public AdvancedRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}
		
		@Override
		public String getParameter(String name) {
			String value = this.request.getParameter(name);
			if (value == null) {
				return null;
			}
			// 如果不是以get方式提交的数据，直接返回获取到的值
			if (!this.request.getMethod().equalsIgnoreCase("get")) {
				// 调用filter转义value中的html标签
				value = filter(value);
			} else {
				// 如果是get方式提交的数据，对获取的值进行转码处理
				try {
					value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 调用filter转义value中的html标签
				value = filter(value);
			}
			
			for (String dirtyWord : dirtyWords) {
				if (value.contains(dirtyWord)) {
					System.out.println("内容中包含敏感词：" + dirtyWord + ",将会被替换成****");
					value = value.replaceAll(dirtyWord, "****");
				}
			}
			return value;
		}
	}
	
	/**
	 * 过滤内容中的html标签
	 * @param value
	 * @return
	 */
	public String filter(String value) {
		if (value == null)
			return null;
		char content[] = new char[value.length()];
		value.getChars(0, value.length(), content, 0);
		StringBuffer result = new StringBuffer(content.length + 50);
		for (int i = 0; i < content.length; i++) {
			switch (content[i]) {
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
	
	private List<String> getDirtyWords() {
		List<String> dirtyWords = new ArrayList<String>();
		String dirtyWordPath = filterConfig.getInitParameter("dirtyWord");
		InputStream inputStream = filterConfig.getServletContext().getResourceAsStream(dirtyWordPath);
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(inputStream, defaultCharset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(is);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				dirtyWords.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dirtyWords;
	}
}
