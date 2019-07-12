package com.qh.test.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

public class JsonUtil {
	public static Map<String, String[]> toHashMap(JSONObject jsonObject) {
		// 用于存储接收到的key：value
		Map<String, String[]> data = new HashMap<String, String[]>();
		// 获取json对象中的键
		@SuppressWarnings("unchecked")
		Set<String> keySet = jsonObject.keySet();
		String key = "";
		String[] value = null;

		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
			key = iterator.next();
			value = (String[]) jsonObject.get(key);
			data.put(key, value);
		}
		return data;
	}
}
