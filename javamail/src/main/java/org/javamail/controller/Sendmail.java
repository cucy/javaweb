package org.javamail.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Sendmail类继承Thread，这个线程类用于给指定的用户发送Email
 * @author zhxg
 *
 */
public class Sendmail extends Thread {

	// 用于给用户发送邮件的邮箱
	private String from = "17091640604@163.com";
	// 邮箱用户名
	private String username = "17091640604@163.com";
	// 邮箱密码
	private String password="dlj123456dlj";
	// 发送邮件的服务器地址
	private String host = "SMTP.163.com";
	
	private User user;
	public Sendmail(User user) {
		this.user = user;
	}
	
	/*
	 * 重写run方法实现，在run方法中发送邮件给指定的用户
	 */
	@Override
	public void run() {
		try {
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			session.setDebug(true);
			Transport ts = session.getTransport();
			ts.connect(host, username, password);
			Message message = createEmail(session, user);
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Message createEmail(Session session, User user) throws AddressException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
		message.setSubject("用户注册邮件");
		String info = "恭喜您注册成功，您的用户名：" + user.getUsername() + ",您的密码：" + user.getPassword() + ", 请妥善保管，如有问题请联系网站客服！！";
		message.setContent(info, "text/html;charset=UTF-8");
		message.saveChanges();
		return message;
	}
}
