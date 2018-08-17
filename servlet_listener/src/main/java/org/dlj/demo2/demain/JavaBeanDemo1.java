package org.dlj.demo2.demain;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 	实现了HttpSessionBindingListener接口的javaBean对象可以感知自己
 * 被绑定到Session中和从session中删除的事件
 * 	当对象被绑定到HttpSession对象中时，web服务器调用该对象的valueBound方法
 * 	当对象从HttpSession对象中解除绑定时，web服务器调用该对象的valueUnbound方法
 * @author zhxg
 *
 */
public class JavaBeanDemo1 implements HttpSessionBindingListener {

	private String name;
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(name + "被添加到session中了");
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(name + "被session踢出来了");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public JavaBeanDemo1(String name) {
		this.name = name;
	}
}
