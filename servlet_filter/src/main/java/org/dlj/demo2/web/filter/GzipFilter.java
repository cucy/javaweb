package org.dlj.demo2.web.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPOutputStream;

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

public class GzipFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		BufferResponse myresponse = new BufferResponse(response);
		chain.doFilter(request, myresponse);
		// 拿出缓存中的数据，压缩后再打给浏览器
		byte out[] = myresponse.getBuffer();
		System.out.println("原始大小： " + out.length);
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		// 压缩输出流中的数据
		GZIPOutputStream gout = new GZIPOutputStream(bout);
		gout.write(out);
		gout.close();
		
		byte gzip[] = bout.toByteArray();
		System.out.println("压缩后的大小：" + gzip.length);
		response.setHeader("content-encoding", "gzip");
		response.setContentLength(gzip.length);
		response.getOutputStream().write(gzip);
	}
	
	
	
	public void destroy() {
		
	}
	
	public void init(FilterConfig filterConfig) {
		
	}
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
	
	//@Override
	public byte[] getBuffer() {
		if (pw != null) {
			pw.close();
		}
		if (bout != null) {
			try {
				bout.flush();
				return bout.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			
		}
		return null;
	}
}

class MyServletOutputStream extends ServletOutputStream {
	private ByteArrayOutputStream bout;
	public MyServletOutputStream(ByteArrayOutputStream bout) {
		this.bout = bout;
	}
	
	@Override
	public void write(int b) {
		this.bout.write(b);
	}
}
