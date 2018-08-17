package org.dlj.response.study;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class ResponseImage extends HttpServlet  {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		outputChineseByOutputStream(response);
	}
	
	private void outputChineseByOutputStream(HttpServletResponse response) throws IOException {
		
		//String data = "中国";
//		OutputStream outputStream = response.getOutputStream();
		//response.setHeader("content-type", "text/image;charset=UTF-8");
		/*
		 * data.getBytes()是一个将字符转换成字节数组的过程，这个过程中一定会去查码表，
		 * 如果是中文的操作系统环境，默认就是查找Gb2312的码表，
		 * 将字符转换为字节数组的过程就是将中文字符转换为GB2312的码表上对应的数字
		 * 比如：“中”在GB2312的码表上对应的数字是98
		 * 		“国”在GB2312的码表上对应的数字是99
		 * 
		 * getBytes()方法如果不带参数，那么就会根据操作系统的语言环境来选择转换码表，
		 * 如果是中文操作系统，那么就是用gb2312的码表
		 */
//		byte[] dataByteArr1 = data.getBytes();
//		System.out.println(Arrays.toString(dataByteArr1));
//		byte[] dataByteArr = data.getBytes("UTF-8");
		
//		Path path=Paths.get(filePath);
//		System.out.println("Files.exists(filePath) :" + Files.exists(path));
//        if(!Files.exists(path)){
//        	return "redirect:/error/404.html";
//        }
		//InputStream inputStream = OfficeToPDF2.getInputStream(urlStr);
//		if (inputStream == null || (fileName.lastIndexOf("/") == -1 && fileName.lastIndexOf("\\") == -1)) {
//			return "redirect:/error/404.html";
//		}

		//int pos = fileName.lastIndexOf("/") == -1 ? fileName.lastIndexOf("\\") : fileName.lastIndexOf("/");
		// System.out.println("URLEncoder.encode(fileName, \"utf-8\"): " +
		// URLEncoder.encode(fileName, "utf-8"));
		// 设置响应头，控制浏览器下载该文件
//		response.setHeader("content-disposition",
//				"attachment;fileName=" + URLEncoder.encode(fileName, "utf-8") + "." + fileExt);
//		response.setContentLengthLong(rvo.getSize());
		//try (InputStream inputStream = new FileInputStream(new File(""))) {
		try (InputStream inputStream = this.getServletContext().getResourceAsStream("/img/Desert.jpg")) {
		     IOUtils.copy(inputStream, response.getOutputStream());
		}
		
		//outputStream.write(dataByteArr); // 使用OutputStream流向客户端输出字节数组
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doGet(request, response);
	}
}
