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
 * 敏感词过滤器
 * 
 * @author zhxg
 *
 */
public class DirtyFilter implements Filter {

	private FilterConfig config = null;

	@Override
	public void init(FilterConfig filterConfig) {
		this.config = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		DirtyRequest dirtyrequest = new DirtyRequest(request);
		chain.doFilter(dirtyrequest, response);
	}

	@Override
	public void destroy() {

	}

	private List<String> getDirtyWords() {
		List<String> dirtyWords = new ArrayList<String>();
		String dirtyWordPath = config.getInitParameter("dirtyWord");
		InputStream inputStream = config.getServletContext().getResourceAsStream(dirtyWordPath);
		InputStreamReader is = null;
		try {
			is = new InputStreamReader(inputStream, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(is);
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				dirtyWords.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirtyWords;

	}

	/**
	 * 使用Decorator模式包装request对象，实现敏感字符过滤功能
	 * 
	 * @author zhxg
	 *
	 */
	class DirtyRequest extends HttpServletRequestWrapper {
		private List<String> dirtyWords = getDirtyWords();
		private HttpServletRequest request;

		public DirtyRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
		}

		@Override
		public String getParameter(String name) {
			String value = this.request.getParameter(name);
			if (value == null) {
				return null;
			}

			for (String dirtyWord : dirtyWords) {
				if (value.contains(dirtyWord)) {
					System.out.println("内容中包含敏感词： " + dirtyWord + ", 将会被替换成***");
					// 替换敏感字符串
					value = value.replaceAll(dirtyWord, "****");
				}
			}
			return value;
		}
	}
}
