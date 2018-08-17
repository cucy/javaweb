package org.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 首页：列出所有书（使用url重写解决禁用cookie后servlet共享session中的数据）
 * response.encodeRedirectURL(url)和response.encodeURL(url)是
 * 两个非常智能的方法，当检测到浏览器没有禁用cookie时，就不进行url重写。
 * response.encodeRedirectURL(url)用户对sendRedirect方法后的url地址进行重写
 * response.encodeURL(url)用于对表单action和超链接的url地址进行重写
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		// 创建session
		request.getSession();
		out.write("本网站有如下书：<br/>");
		Set<Map.Entry<String, Book>> set = DB.getAll().entrySet();
		for (Map.Entry<String, Book> me : set) {
			Book book = me.getValue();
			String url = request.getContextPath() + "/servlet/BuyServlet?id=" + book.getId();
			// response.encodeURL(java.lang.String url)用于对表单action和超链接的url地址进行重写
			url = response.encodeURL(url); // 将超链接的url地址进行重写
			out.println(book.getName() + "  <a href='" + url + "'>购买</a><br/>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

/**
 * 模拟数据库
 */
class DB{
	private static Map<String, Book> map = new LinkedHashMap<String, Book>();
	
	static {
		map.put("1", new Book("1", "javaweb开发"));
		map.put("2", new Book("2", "spring开发"));
		map.put("3", new Book("3", "hibernate开发"));
		map.put("4", new Book("4", "struts开发"));
		map.put("5", new Book("5", "ajax开发"));
	}
	
	public static Map<String, Book> getAll() {
		return map;
	}
}

class Book{
	
	private String id;
	private String name;
	
	public Book() {
		super();
	}
	
	public Book(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}