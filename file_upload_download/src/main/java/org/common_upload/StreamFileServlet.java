package org.common_upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
 
public class StreamFileServlet extends HttpServlet {
 
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
			// 创建一个文件上传处理器
			ServletFileUpload upload = new ServletFileUpload();
 
			// 解析请求
			FileItemIterator iter;
			try {
				iter = upload.getItemIterator(req);
				while (iter.hasNext()) {
					FileItemStream item = iter.next();
					InputStream stream = item.openStream();
					if (item.isFormField()) {
						System.out.println("---------------普通表单项");
						String name = item.getFieldName();
						System.out.println("FieldName: " + name);
						String value = Streams.asString(stream, "UTF-8");
						System.out.println("FieldValue: " + value);
					} else {
						System.out.println("---------------文件表单项");
						String fieldName = item.getFieldName();
						System.out.println("FieldName: " + fieldName);
						String fileName = item.getName();
						System.out.println("FileName: " + fileName);
						OutputStream os = new FileOutputStream(new File("e:/"
								+ fileName));
						IOUtils.copy(stream, os);
						IOUtils.closeQuietly(stream);
						IOUtils.closeQuietly(os);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
 
		}
	}
}