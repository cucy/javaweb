package org.dlj.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dlj.annotation.Controller;
import org.dlj.annotation.RequestMapping;
import org.dlj.web.context.WebContext;
import org.dlj.web.view.View;
import org.dlj.web.view.ViewData;

/**
 * 处理用户登录的Servlet，LoginServlet现在就是一个普通的java类，不是一个真正的servlet
 * @author zhxg
 *
 */
@Controller	// 使用Controller注解标注LoginServlet2
public class LoginServlet2 {
	
	/**
	 * 处理以普通方式提交的请求
	 * @return
	 */
	// 使用RequestMapping注解标注loginHandle方法，指明loginHandle方法的访问路径是login/handle
	@RequestMapping("login/handle")
	public View loginHandle() {
		// 创建一个ViewData对象，用于存储需要发送到客户端的响应数据
		ViewData viewData = new ViewData();
		// 通过WebContext类获取当前线程中的HttpServletRequest对象
		HttpServletRequest request = WebContext.requestHodler.get();
		// 接受提交上来的参数
		String username = request.getParameter("usename");
		String pwd = request.getParameter("pwd");
		if (username.equals("gacl") && pwd.equals("xdp")) {
			request.getSession().setAttribute("usename", username);
			// 将响应数据存储在ViewData对象中
			viewData.put("msg", "欢迎您！" + username);
			// 返回一个View对象，指明要跳转的视图路径
			return new View("/index.jsp");
		} else {
			// 将响应数据存储在ViewData对象中
			viewData.put("msg", "登录失败，请检查用户名和密码是否正确!");
			// 返回一个View对象，指明要跳转的视图路径
			return new View("/login2.jsp");
		}
	}
	
	@RequestMapping("ajaxLogin/handle")
	public void ajaxLoginHandle() throws IOException {
		// 通过WebContext类获取当前线程中的HttpServletRequest对象
		HttpServletRequest request = WebContext.requestHodler.get();
		// 接受提交上来的参数
		String username = request.getParameter("usename");
		String pwd = request.getParameter("pwd");
		// 通过WebContext类获取当前线程中的HttpServletResponse对象
		HttpServletResponse response = WebContext.responseHodler.get();
		if (username.equals("gacl") && pwd.equals("xdp")) {
			request.getSession().setAttribute("usename", username);
			response.getWriter().write("success");
		} else {
			response.getWriter().write("fail");
		}
	}
}
