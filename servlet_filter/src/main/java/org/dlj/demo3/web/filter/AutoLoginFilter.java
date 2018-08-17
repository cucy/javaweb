package org.dlj.demo3.web.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dlj.demo3.web.dao.UserDao;
import org.dlj.demo3.web.entity.User;
import org.dlj.util.WebUtils;

public class AutoLoginFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 如果已经登录了，直接chain.doFilter(request, response)放行
		if (request.getSession().getAttribute("user") != null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		// 1.得到用户带来的authlogin的cookie
		String value = null;
		Cookie cookies[] = request.getCookies();
		for (int i=0; cookies != null && i< cookies.length; i++) {
			if (cookies[i].getName().equals("autologin")) {
				value = cookies[i].getValue();
			}
		}
		
		// 2.得到cookie中的用户名和密码
		if (value != null) {
			String username = URLDecoder.decode(value.split("\\.")[0], "utf-8");
			String password = value.split("\\.")[1];
			
			// 3.调用到获取用户对应的密码
			UserDao dao = new UserDao();
			User user = dao.find(username);
			String dbpassword = user.getPassword();
			
			// 4.检查用户带过来的md5的密码和数据库中的密码是否匹配，如匹配则自动登录
			if (password.equals(WebUtils.md5(dbpassword))) {
				System.out.println("autoLogin success");
				request.getSession().setAttribute("user", user);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	public void destroy() {
		
	}
	
	public void init(FilterConfig filterConfig) {
		
	}
	
}
