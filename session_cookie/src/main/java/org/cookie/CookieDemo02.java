package org.cookie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 删除cookie，设置maxAge为零，删除cookie时，path必须一致，否则不会删除
 * @author zhxg
 *
 */
public class CookieDemo02 extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4326638520951778912L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 创建一个名字为lastAccessTime的cookie
		Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis() + "");
		// 将cookie的有效期设置为0，命令浏览器删除该cookie
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		// 获取cookie中的userName的值,包含中文时需要使用URLDecoder解码
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				if (c.getName().equals("userName")) {
					System.out.println(c.getValue() + "; decode username: " + URLDecoder.decode(c.getValue(), "utf-8"));
					System.out.println("username cookie path:" + c.getPath());
				}
			}
		} else {
			System.out.println("不存在cookie");
		}
		// 在cookie中存储中文，必须使用URLEncoder类的encode(s, enc)方法进行中文转码;前端可以使用js的decodeURI方法解码
		cookie = new Cookie("userName", URLEncoder.encode("孤傲苍狼", "UTF-8"));
		cookie.setPath(request.getContextPath() + "/cookie.jsp");
		//cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.sendRedirect(request.getContextPath() + "/cookie.jsp");
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}

}
