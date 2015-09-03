package com.pw.util;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectFactory {
	private static Properties classMetadata;
	private static Map<String, Object> objectMap;

	static {
		classMetadata = new Properties();
		try {
			classMetadata.load(ObjectFactory.class.getClassLoader()
					.getResourceAsStream("appClasses.properties"));
			objectMap = new ConcurrentHashMap<String, Object>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Object getObject(String clazzName)
			throws Exception {
		Object obj = null;
		String fClassName = null;

		if (objectMap.containsKey(clazzName) == true) {
			obj = objectMap.get(clazzName);
		} else {
			fClassName = classMetadata.getProperty(clazzName);
			if (fClassName == null) {
				throw new Exception("Class not configured");
			}
			obj = Class.forName(fClassName).newInstance();
			objectMap.put(clazzName, obj);
		}

		return obj;
	}
}
