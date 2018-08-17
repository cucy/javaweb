package org.dlj.demo3.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelAutoLoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 移除存储在session中的user
		request.getSession().removeAttribute("user");
		// 移除自动登陆的cookie
		removeAutoLoginCookie(request, response);
		// 注销用户后跳转到登录页面
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	private void removeAutoLoginCookie(HttpServletRequest request, HttpServletResponse response) {
		// 创建一个名字为autologin的cookie
		Cookie cookie = new Cookie("autologin", "");
		// 将cookie的有效期设置为0，命令浏览器删除该cookie
		cookie.setMaxAge(0);
		// 设置要删除的cookie的path
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
