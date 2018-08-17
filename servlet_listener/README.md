参考blog：[javaweb学习总结（四十四）--监听器（Listener）学习](http://www.cnblogs.com/xdp-gacl/p/3961929.html)
练习类所在包：org.dlj.demo1

SERVLET监听器分类：
在servlet规范中定义了多种类型的监听器，它们用于监听的事件源分别是ServletContext,HttpSession,ServletRequest这三个域对象
	Servlet规范针对这三个对象上的操作，又把多种类型的监听器划分为三种类型：
	1. 监听域对象自身的创建和销毁的事件监听器(ServletContextListener, HttpSessionListener, ServletRequestListener)
	2. 监听域对象中的属性的增加和删除的事件监听器(ServletContextAttributeListener, HttpSessionAttributeListener, ServletRequestAttributeListener)
	3. 监听绑定到HttpSession域中的某个对象的状态的事件监听器(HttpSessionBindingListener, HttpSessionActivationListener)
	

参考blog：[javaweb学习总结（四十五）--监听器（Listener）学习](http://www.cnblogs.com/xdp-gacl/p/3969249.html)
练习类所在的包：org.dlj.demo2
MyRequestAndSessionAttributeListener 监听ServletRequest和ServletSession中添加移除修改属性时的事件
MyServletContextAttributeListener    监听ServletContext中添加移除修改属性时的事件
JavaBeanDemo1.java  sessionbind.jsp			测试实现HttpSessionBindingListener接口的javabean对象感知自己绑定到HttpSession中和从HttpSession中解除绑定事件

JavaBeanDemo2.java	sessionActivation.jsp	测试实现HttpSessionActivationListener接口的javabean对象感知自己被活化或钝化事件
为了观察绑定到HttpSession对象中的javabean对象随HttpSession对象一起被钝化到硬盘上和从硬盘上重新活化到内存中的过程，需要借助Tomcat服务器帮助完成HttpSession对象的钝化和活化过程
，具体做法如下：在WebRoot\META-INF文件夹下创建一个context.xml文件进行设置。


参考blog：[javaweb学习总结（四十七）--监听器（Listener）在开发中的应用](http://www.cnblogs.com/xdp-gacl/p/3965508.html)
练习类所在的包：org.dlj.demo3
