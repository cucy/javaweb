package org.dlj.demo2.web.listener;

import java.text.MessageFormat;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * ServletContext域对象中属性的变更的事件监听器
 * @ClassName: MyServletContextAttributeListener
 * @author zhxg
 *
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中添加了属性：{0}, 属性值是：{1}", scab.getName(), scab.getValue());
		System.out.println(str);
	}
	
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中替换了属性：{0}, 属性值是：{1}", scab.getName(), scab.getValue());
		System.out.println(str);
	}
	
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scab) {
		String str = MessageFormat.format("ServletContext域对象中移除了属性：{0}的值", scab.getName());
		System.out.println(str);
	}
}
