package org.file_upload_download;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1375681909962673661L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取上传文件的目录
		String uploadFilePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// 存储要下载的文件名
		Map<String, String> fileNameMap = new HashMap<String, String>();
		// 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储在map集合中
		listfile(new File(uploadFilePath), fileNameMap);
		
		// 将Map集合发送到listfile.jsp页面进行显示
//		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8"); 
		request.setAttribute("fileNameMap", fileNameMap);
		request.getRequestDispatcher("/listfile.jsp").forward(request, response);
	}
	
	public void listfile(File file, Map<String, String> map) {
		// 如果file代表的不是一个文件，而是目录
		if (!file.isFile()) {
			// 列出该目录下的所有文件和目录
			File files[] = file.listFiles();
			// 遍历files[]数组
			for(File f : files) {
				// 递归
				listfile(f, map);
			}
		} else {
			/*
			 * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
			 * file.getName().indexOf("_")检索字符串中第一次出现"_"字符的位置，如果文件名类似于：
			 * 923453-939932-3493_阿_凡_达.avi
			 * 那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到
			 * 阿_凡_达.avi部分
			 */
			String realName = URLDecoder.decode(file.getName().substring(file.getName().indexOf("_") + 1));
			System.out.println("map.key: " + URLDecoder.decode(file.getName()) + " ; map.value: " + realName);
			// file.getName()得到的是文件的原始名称，这个名称是唯一的，因此可以作为key，realName是处理过后的名称有可能会重复
			map.put(URLDecoder.decode(file.getName()), realName);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

