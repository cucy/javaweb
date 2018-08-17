package org.file_upload_download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtil {

	private static final String url = "https://xgsj3-alpha.istarshine.com/file/675260933588992.db?sign=676c50787a2b6966365738502b42345566707a334f50534569597131564a4c30727633544a63536e514a62724436617678374d4631646859354f7a6d57774662";
	public static void saveImageToDisk(String desc) {
		InputStream inputStream = getInputStream(url);
		
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(desc);
			while ((len = inputStream.read(data)) != -1) {
				file.write(data, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
		}
	}
	
	public static InputStream getInputStream(String urlStr) {
		InputStream inputStream = null;
		HttpURLConnection httpUrlConn = null;
		try {
			URL url = new URL(urlStr);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			// 设置网络连接超时
			httpUrlConn.setConnectTimeout(3000);
			// 设置应用程序要从网络连接读取数据
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			int code = httpUrlConn.getResponseCode();
			if (code == 200) {
				// 从服务器返回一个输入流
				inputStream = httpUrlConn.getInputStream();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	
	public static void main(String[] args) throws MalformedURLException {
		saveImageToDisk("e://456");
		//URL url2 = new URL(url);
		
		File file = new File("e://456");
		System.out.println(file.exists());
		System.out.println(file.delete());
		
	}
}
