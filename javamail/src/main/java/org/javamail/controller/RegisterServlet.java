package org.javamail.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理用户注册的servlet
 * @author zhxg
 *
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setUsername(username);
		
		System.out.println("把用户信息注册到数据库中");
		// 用户注册成功之后就使用用户注册时的邮箱给用户发送一封email
		// 发送邮件是一件非常耗时的操作，因此这里开辟另一个线程来专门发送邮件
		Sendmail send = new Sendmail(user);
		// 启动线程，线程启动后就会执行run方法发送邮件
		send.start();
		
		// 注册用户
		// new UserService().registerUser(user);
		request.setAttribute("message", "恭喜您，注册成功，我们已经发送一封注册信息单子邮件，请查收，如果没有收到可能是网络原因，过一会就收到了！");
		try {
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
