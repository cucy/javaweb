package org.dlj.demo1.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * MyServletContextListener类实现了ServletContextListener接口，
 * 可以对ServletContext对象的创建和销毁这两个动作进行监听
 * @author zhxg
 *
 */
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContext对象创建");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("ServletContext对象销毁");
	}
}
