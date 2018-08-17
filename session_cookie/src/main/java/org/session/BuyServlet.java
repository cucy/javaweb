package org.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BuyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		Book book = DB.getAll().get(id); // 得到用户想买的书
		HttpSession session = request.getSession();
		List<Book> list = (List)session.getAttribute("list"); // 得到用户用于保存所有书的容器
		if (list == null) {
			list = new ArrayList<Book>();
			session.setAttribute("list", list);
		}
		list.add(book);
		// response.encodeRedirectURL(java.lang.String url)用于对sendRedirect方法后的地址url进行重写
		String url = response.encodeRedirectURL(request.getContextPath() + "/servlet/ListCartServlet");
		System.out.println(url);
		response.sendRedirect(url);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
