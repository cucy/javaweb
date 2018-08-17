package org.javamail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachMail {

	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "SMTP.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		// 使用javamail发送邮件的5个步骤
		// 1.创建session
		Session session = Session.getInstance(prop);
		// 开启Session的Debug模式，可以查看程序发送Email的运行状态
		session.setDebug(true);
		// 2.通过session的到transport对象
		Transport ts = session.getTransport();
		// 3.连上邮件服务器
		ts.connect("SMTP.163.com", "17091640604@163.com", "dlj123456dlj");
		// 4.创建邮件
		Message message = createAttachMail(session);
		// 5.发送邮件
		ts.sendMessage(message, message.getAllRecipients());
	}
	
	/**
	 * @description: 创建一封带附件的邮件
	 * @param session
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws MessagingException
	 */
	public static MimeMessage createAttachMail(Session session) throws FileNotFoundException, IOException, MessagingException {
		MimeMessage message = new MimeMessage(session);
		
		// 设置邮件的基本信息
		// 发送人
		message.setFrom(new InternetAddress("17091640604@163.com"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("17091640604@163.com"));
		// 邮件标题
		message.setSubject("javamail邮件发送测试");
		
		// 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("使用javamail创建带附件的邮件", "text/html;charset=UTF-8");
		
		// 创建邮件附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src\\main\\java\\2.jpg"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		
		// 创建容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");
		
		message.setContent(mp);
		message.saveChanges();
		// 将创建的email写入到E盘存储
		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		// 返回生成的邮件
		return message;
	}
}
