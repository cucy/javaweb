package org.common_upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.apache.commons.io.IOUtils;
 
public class FileServlet extends HttpServlet {
 
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
 
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		if (ServletFileUpload.isMultipartContent(req)) {
			// 文件上传请求
			// 创建文件项工厂
			FileCleaningTracker fileCleaningTracker = FileCleanerCleanup
					.getFileCleaningTracker(this.getServletContext());
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setFileCleaningTracker(fileCleaningTracker);
			// 由内存存储到磁盘存储的临界值，默认为10KB
			// factory.setSizeThreshold(yourMaxMemorySize);
			// 文件上传的临时目录，默认为System.getProperty("java.io.tmpdir")
			// factory.setRepository(yourTempDirectory);
			// 创建文件上传处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置编码方式
			// upload.setHeaderEncoding("UTF-8");
			// 设置单个请求的大小，默认为不限制
			// upload.setSizeMax(yourMaxRequestSize);
			// 设置单个文件的大小，默认为不限制
			// upload.setFileSizeMax(yourFileSizeMax);
			try {
				// 解析请求
				List<FileItem> items = upload.parseRequest(req);
				// 处理上传项
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
 
					if (item.isFormField()) {
						System.out.println("---------------普通表单项");
						String name = item.getFieldName();
						System.out.println("FieldName: " + name);
						String value = item.getString("UTF-8");
						System.out.println("FieldValue: " + value);
					} else {
						System.out.println("---------------文件表单项");
						String fieldName = item.getFieldName();
						System.out.println("FieldName: " + fieldName);
						String fileName = item.getName();
						System.out.println("FileName: " + fileName);
						String contentType = item.getContentType();
						System.out.println("ContentType: " + contentType);
						InputStream is = item.getInputStream();
						OutputStream os = new FileOutputStream(new File("e:/"
								+ fileName));
						IOUtils.copy(is, os);
						IOUtils.closeQuietly(is);
						IOUtils.closeQuietly(os);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		} else {
			// 非文件上传请求
			throw new RuntimeException("非文件上传请求");
		}
	}
}