package org.dlj.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储方法的访问路径
 * @author zhxg
 *
 */
public class RequestMapingMap {

	private static Map<String, Class<?>> requesetMap = new HashMap<String, Class<?>>();
	
	public static Class<?> getClassName(String path) {
		return requesetMap.get(path);
	}
	
	public static void put(String path, Class<?> className) {
		requesetMap.put(path, className);
	}
	
	public static Map<String, Class<?>> getRequestMap() {
		return requesetMap;
	}
}
