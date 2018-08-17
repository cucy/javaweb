package org.dlj.demo2.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * web资源缓存过滤器
 * @author zhxg
 *
 */
public class WebResourceCachedFilter implements Filter {

	private Map<String, byte[]> map = new HashMap<String, byte[]>();
	
	@Override
	public void init(FilterConfig filterConfig) {
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 1.得到用户请求的uri
		String uri = request.getRequestURI();
		// 2.看缓存中有没有URI对应的数据
		byte b[] = map.get(uri);
		// 3.如果缓存中有，直接拿缓存的数据打给浏览器，程序返回
		if (b != null) {
			// 根据字节数组和指定的字符编码构建字符串
			String webResourceHtmlStr = new String(b, response.getCharacterEncoding());
			System.out.println(webResourceHtmlStr);
			response.getOutputStream().write(b);
			return;
		}
		// 4.如果缓存没有，让目标资源执行，并捕获目标资源的输出
		BufferResponse myresponse = new BufferResponse(response);
		chain.doFilter(request, myresponse);
		// 获取缓冲流中的内容的字节数组
		byte out[] = myresponse.getBuffer();
		// 5.把资源的数据以用户请求的uri为关键字保存到缓存中
		map.put(uri, out);
		// 6.把数据打给浏览器
		response.getOutputStream().write(out);
	}
	
	@Override
	public void destroy() {
		
	}
	
	class BufferResponse extends HttpServletResponseWrapper {
		private ByteArrayOutputStream bout = new ByteArrayOutputStream();
		private PrintWriter pw;
		private HttpServletResponse response;
		
		public BufferResponse(HttpServletResponse response) {
			super(response);
			this.response = response;
		}
		@Override
		public ServletOutputStream getOutputStream() {
			return new MyServletOutputStream(bout);
		}
		
		@Override
		public PrintWriter getWriter() throws UnsupportedEncodingException {
			pw = new PrintWriter(new OutputStreamWriter(bout, this.response.getCharacterEncoding()));
			return pw;
		}
		
		public byte[] getBuffer() {
			if (pw != null) {
				pw.close();
			}
			return bout.toByteArray();
		}
	}

	class MyServletOutputStream extends ServletOutputStream{
		private ByteArrayOutputStream bout;
		public MyServletOutputStream(ByteArrayOutputStream bout) {
			this.bout = bout;
		}
		
		@Override
		public void write(int b) {
			bout.write(b);
		}
	}
}