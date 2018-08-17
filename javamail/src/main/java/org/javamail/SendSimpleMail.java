package org.javamail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendSimpleMail {
	
	public static void main(String[] args) throws MessagingException {

		Properties prop = new Properties();
		prop.setProperty("mail.host", "SMTP.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		
		// 使用JavaMail发送邮件的5个步骤
		// 1.创建session
		Session session = Session.getInstance(prop);
		// 开启session的debug模式，这样就可以查看到程序发送email的运行状态
		session.setDebug(true);
		// 2.通过session得到transport对象
		Transport ts = session.getTransport();
		// 3.使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发送人需要提交邮箱的用户名和密码给SMTP服务器
		// 用户名和密码都通过验证之后才能够正常发送邮件给邮件收件人
		ts.connect("SMTP.163.com", "17091640604@163.com", "dlj123456dlj");
		// 4.创建邮件
		Message message = createSimpleMail(session);
		// 5.发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
	}
	
	public static MimeMessage createSimpleMail(Session session) throws AddressException, MessagingException {
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress("17091640604@163.com"));
		// 指明邮件的收件人，现在发件人和收件人是一样的，自己发送给自己
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("17091640604@163.com"));
		// 邮件标题
		message.setSubject("只包含文本的简单邮件");
		// 邮件的文本内容
		message.setContent("你好啊！", "text/html;charset=UTF-8");
		// 返回创建好的邮件对象
		return message;
	}
}
