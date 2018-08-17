package org.dlj.demo1.web.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * MyServletRequestListener类实现了ServletRequestListener接口，
 * 因此可以对ServletRequest对象的创建和销毁这两个动作进行监听
 * @author zhxg
 *
 */
public class MyServletRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println(sre.getServletRequest() + "销毁了!!");
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println(sre.getServletRequest() + "创建了!!");
	}
}
