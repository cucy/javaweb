package org.dlj.web.UI;

import org.dlj.annotation.Controller;
import org.dlj.annotation.RequestMapping;
import org.dlj.web.view.View;

/**
 * 使用Controller注解标注LoginUI类
 * @author zhxg
 *
 */
@Controller
public class LoginUI {

	// 使用RequestMapping注解指明forward1方法的访问路径
	@RequestMapping("LoginUI/Login2")
	public View forward1() {
		// 执行完forward1方法之后返回的视图
		return new View("/login2.jsp");
	}
	
	// 使用RequestMapping注解指明forward2方法的访问路径
	@RequestMapping("LoginUI/Login3")
	public View forward2() {
		// 执行完forward2方法之后返回的视图
		return new View("/login3.jsp");
	}
}
