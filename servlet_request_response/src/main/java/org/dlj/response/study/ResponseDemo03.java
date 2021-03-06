package org.dlj.response.study;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseDemo03 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("refresh", "5"); // 设置refresh响应头控制浏览器每隔5秒钟刷新一次
		// 1.在内存中创建一张图片
		BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
		// 2.得到图片
		Graphics2D g = (Graphics2D)image.getGraphics();
		g.setColor(Color.white); // 设置图片的背景色
		g.fillRect(0, 0, 80, 20); // 填充背景色
		// 3.向图片上写数据
		g.setColor(Color.blue); // 设置图片上字体的颜色
		g.setFont(new Font(null, Font.BOLD, 20));
		g.drawString(makeNum(), 0, 20);
		// 4.设置响应头控制浏览器以图片的方式打开
		response.setContentType("image/jpeg"); // 等同于resposne.setHeader("content-type", "image/jpeg");
		// 5.设置响应头控制浏览器不缓存图片数据
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		// 6.将图片写给浏览器
		ImageIO.write(image, "jpg", response.getOutputStream());
	}
	
	private String makeNum() {
		Random random = new Random();
		String num = random.nextInt(9999999) + "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 7 - num.length(); i++) {
			sb.append("0");
		}
		num = sb.toString() + num;
		return num;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
