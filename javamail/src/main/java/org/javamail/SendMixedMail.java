package org.javamail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMixedMail {

	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "SMTP.163.com");
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		
		// 使用javamail发送邮件的5个步骤
		// 1.创建session
		Session session = Session.getInstance(prop);
		// 开启session的debug模式
		session.setDebug(true);
		// 2.通过session得到transport对象
		Transport ts = session.getTransport();
		// 3.连上邮件服务器
		ts.connect("SMTP.163.com", "17091640604@163.com", "dlj123456dlj");
		// 4.创建邮件
		Message message = createMixedMail(session);
		// 5.发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		ts.close();
		
	}
	
	public static MimeMessage createMixedMail(Session session) throws AddressException, MessagingException, FileNotFoundException, IOException {
		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		
		// 设置邮件的基本信息
		message.setFrom(new InternetAddress("17091640604@163.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("17091640604@163.com"));
		message.setSubject("带附件和图片的邮件");
		
		// 正文
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("xxx这是女的xxxx<br/><img src='cid:aaa.jpg'>", "text/html;charset=UTF-8");
		
		// 图片
		MimeBodyPart image = new MimeBodyPart();
		image.setDataHandler(new DataHandler(new FileDataSource("src\\main\\java\\3.jpg")));
		image.setContentID("aaa.jpg");
		
		// 附件1
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource("src\\main\\java\\4.zip"));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName());
		
		// 附件2
		MimeBodyPart attach2 = new MimeBodyPart();
		DataHandler dh2 = new DataHandler(new FileDataSource("src\\main\\java\\波子.zip"));
		attach2.setDataHandler(dh2);
		attach2.setFileName(MimeUtility.encodeText(dh2.getName()));
		
		// 描述关系：正文和图片
		MimeMultipart mp1 = new MimeMultipart();
		mp1.addBodyPart(text);
		mp1.addBodyPart(image);
		mp1.setSubType("related");
		
		// 描述关系：正文和附件
		MimeMultipart mp2 = new MimeMultipart();
		mp2.addBodyPart(attach);
		mp2.addBodyPart(attach2);
		
		// 代表正文的bodypart
		MimeBodyPart content = new MimeBodyPart();
		content.setContent(mp1);
		mp2.addBodyPart(content);
		mp2.setSubType("mixed");
		
		message.setContent(mp2);
		message.saveChanges();
		
		message.writeTo(new FileOutputStream("E:\\MixedMail.eml"));
		// 返回创建好的邮件
		return message;
	}
}
