package org.dlj.web.controller;

import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dlj.annotation.Controller;
import org.dlj.annotation.RequestMapping;
import org.dlj.util.BeanUtils;
import org.dlj.util.RequestMapingMap;
import org.dlj.util.ScanClassUtil;
import org.dlj.web.context.WebContext;
import org.dlj.web.view.DispatchActionConstant;
import org.dlj.web.view.View;

/**
 * AnnotationHandleServlet作为自定义注解的核心处理器以及负责调用目标业务方法处理用户请求
 * @author zhxg
 *
 */
public class AnnotationHandleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6646046421497737032L;

	private String pareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath() + "/";
		String requestUri = request.getRequestURI();
		String midUrl = requestUri.replaceFirst(path, "");
		String lasturl = midUrl.substring(0, midUrl.lastIndexOf("."));
		System.out.println("uri: " + requestUri + "; url: " + request.getRequestURL());
		System.out.println("path: " + path + "; midUrl: " + midUrl + "; lasturl: " + lasturl);
		return lasturl;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		this.excute(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		this.excute(request, response);
	}
	
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		// 将当前线程中HTTPServletRequest对象存储到ThreadLocal中，以便在controller类中使用
		WebContext.requestHodler.set(request);
		// 将当前线程中HttpServletResponse对象存储在ThreadLocal中，以便在Controller类中使用
		WebContext.responseHodler.set(response);
		// 解析url
		String lasturl = pareRequestURI(request);
		// 获取要使用的类
		Class<?> clazz = RequestMapingMap.getRequestMap().get(lasturl);
		System.out.println(RequestMapingMap.getRequestMap());
		// 创建类的实例
		Object classInstance = BeanUtils.instanceClass(clazz);
		// 获取类中定义的方法
		Method [] methods = BeanUtils.findDeclaredMethods(clazz);
		Method method = null;
		for(Method m : methods) {
			// 循环方法，找匹配的方法进行执行
			if (m.isAnnotationPresent(RequestMapping.class)) {
				String anoPath = m.getAnnotation(RequestMapping.class).value();
				if (anoPath != null && !"".equals(anoPath.trim()) && lasturl.equals(anoPath.trim())) {
					// 找到要执行的目标方法
					method = m;
					break;
				}
			}
		}
		
		try {
			if (method != null) {
				// 执行目标方法处理用户请求
				Object retObject = method.invoke(classInstance);
				// 如果方法有返回值，那么就表示用户需要返回视图
				if (retObject != null) {
					View view = (View)retObject;
					// 判断要使用的跳转方式
					if (view.getDispathAction().equals(DispatchActionConstant.FORWARD)) {
						// 使用服务端跳转方式
						System.out.println("view.dispath: " + view.getDispathAction() + "; url: " + view.getUrl());
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					} else if (view.getDispathAction().equals(DispatchActionConstant.REDIRECT)) {
						// 使用客户端跳转方式
						response.sendRedirect(request.getContextPath() + view.getUrl());
					} else {
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		/*
		 * 重写了Servlet的init方法后一定要记得调用父类的init方法，
		 * 否则，在service/doGet/doPost方法中使用getServletContext()方法获取ServletContext对象时
		 * 会出现空指针异常
		 */
		super.init(config);
		System.out.println("----初始化开始----");
		// 获取web.xml中配置的要扫描的包
		String basePackage = config.getInitParameter("basePackage");
		// 如果配置了多个包，如：<param-value>me.gacl.web.controller,me.gacl.web.ui</param-value>
		if (basePackage.indexOf(",") > 0) {
			// 使用逗号进行分隔
			String[] packageNameArr = basePackage.split(",");
			for (String packageName : packageNameArr) {
				initRequestMapingMap(packageName);
			}
		} else {
			initRequestMapingMap(basePackage);
		}
		System.out.println("---初始化结束---");
	}
	
	/**
	 * 添加使用了Controller注解的Class到RequestMapingMap中
	 * @param packageName
	 */
	private void initRequestMapingMap(String packageName) {
		Set<Class<?>> setClasses = ScanClassUtil.getClasses(packageName);
		for (Class<?> clazz : setClasses) {
			if (clazz.isAnnotationPresent(Controller.class)) {
				Method[] methods = BeanUtils.findDeclaredMethods(clazz);
				for (Method m : methods) {
					// 循环方法，找到匹配的方法进行执行
					if (m.isAnnotationPresent(RequestMapping.class)) {
						String anoPath = m.getAnnotation(RequestMapping.class).value();
						if (anoPath != null && !"".equals(anoPath.trim())) {
							if (RequestMapingMap.getRequestMap().containsKey(anoPath)) {
								throw new RuntimeException("RequestMapping映射的地址不允许重复！");
							}
							RequestMapingMap.put(anoPath, clazz);
						}
					}
				}
			}
		}
	}
}
