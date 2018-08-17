package org.dlj.demo2.demain;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 实现了HttpSessionActivationListener接口的javabean对象可以感知自己被活化和钝化的事件
 * 	活化：javabean对象和session一起被序列化到内存中。
 * 	钝化：javabean对象存在session中，当服务器把session序列化到硬盘上时，如果session中的javabean对象实现了serializable接口
 * 那么服务器会把session中的javabean对象一起序列化到硬盘中的这个操作称为钝化。如果session中的javabean对象没有实现Serializable接口，
 * 那么服务器会先把session中没有实现serializable接口的javabean对象移除，然后再把session序列化到硬盘中。
 * 
 * 	当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被钝化之前，web服务器调用该javabean对象的sessionWillPassivate(HttpSessionEvent event)方法
 * 这样javabean对象就可以知道自己将要和HttpSession对象一起序列化到硬盘中。
 * 	当绑定到HttpSession对象中的javabean对象将要随HttpSession对象被活化之后，web服务器调用该javabean对象的sessionDidActive(HttpSessionEvent event)方法
 * 这样javabean对象就可以知道自己将要和HttpSession对象一起被反序列化会内存中
 * @author zhxg
 *
 */
public class JavaBeanDemo2 implements HttpSessionActivationListener, Serializable {

	private static final long serialVersionUID = -3655682464363784883L;
	
	private String name;
	
	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println(name + " 和session一起被序列化（钝化）到硬盘了，session的id是：" + se.getSession().getId());
		
	}
	
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println(name + " 和session一起从硬盘反序列化（活化）到内存了，session的id是：" + se.getSession().getId());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public JavaBeanDemo2(String name) {
		this.name = name;
	}
}
