package org.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<Book> list = (List)session.getAttribute("list");
		if (list == null || list.size() == 0) {
			out.write("对不起，您还没有购买任何商品！！");
			return;
		}
		
		// 显示用户买过的商品
		out.write("您买过如下商品：<br>");
		for(Book book : list) {
			out.write(book.getName() + "<br/>");
		}
		System.out.println("session timeout(s): " + session.getMaxInactiveInterval());
		// 手工调用session.invalidate方法销毁session
		session.invalidate();
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
