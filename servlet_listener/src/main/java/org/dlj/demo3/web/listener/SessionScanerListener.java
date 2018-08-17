package org.dlj.demo3.web.listener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 自定义session扫描器
 * 当一个web应用创建的Session很多事，为了避免Session占用太多的内存，可以选择手动将这些内存中的session销毁
 * @author zhxg
 *
 */
public class SessionScanerListener implements HttpSessionListener, ServletContextListener {

	/*
	 * 使用Collections.synchronizedList(List<T> list) 方法将LinkedList包装成一个线程安全的集合
	 */
	private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
	/*
	 * 定义一个对象，让这个对象充当一把锁，用这把锁来保证往list集合添加的新的session和遍历
	 * list集合中的session这两个操作达到同步
	 */
	private Object lock = new Object();
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session被创建了！！");
		HttpSession session = se.getSession();
		synchronized (lock) {
			list.add(session);
		}
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session被销毁了！！");
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("web应用初始化");
		// 创建定时器
		Timer timer = new Timer();
		// 每隔30秒就定时执行任务
		timer.schedule(new MyTask(list, lock), 0, 1000 * 30);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("web应用关闭");
	}
}

class MyTask extends TimerTask {
	// 存储HttpSession的list集合
	private List<HttpSession> list;
	// 存储传递过来的锁
	private Object lock;
	public MyTask(List<HttpSession> list, Object lock) {
		this.list = list;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized(lock) {
			System.out.println("定时器执行！！");
			ListIterator<HttpSession> it = list.listIterator();
			
			while (it.hasNext()) {
				HttpSession session = (HttpSession) it.next();
				
				/*
				 * 如果当前时间-session的最后访问时间 > 1000*15 s
				 * session.getLastAccessedTime()获取session的最后访问时间
				 */
				if (System.currentTimeMillis() - session.getLastAccessedTime() > 1000 * 30) {
					// 手动销毁session
					session.invalidate();
					// 移动集合中已经被销毁的session
					it.remove();
				}
			}
		}
	}
}
