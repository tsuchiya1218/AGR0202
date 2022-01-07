package util;

import java.util.Map;

public class XssFilter {
	private static XssFilter xssFilter = new XssFilter();
	
	public static XssFilter getInstance() {
		return xssFilter;
	}
	
	private XssFilter() {}
	
	
	public String stripTagAll(String str) {
		if(!"".equals(str) && str != null) {
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
		}
		return str;
	}
	public Map<String,String> stripTagAll(Map<String,String> map){
		for(String key : map.keySet()) {
			if(!"".equals(map.get(key)) && map.get(key) != null) {
				String str = map.get(key);
				str = str.replaceAll("<", "&lt;");
				str = str.replaceAll(">", "&gt;");
				map.put(key, str);
			}
		}
		return map;
	}
	
	public String[] stripTagAll(String[] array){
		int index = 0;
		for(String str : array) {
			if(!"".equals(str) && str != null) {
				String str1 = str.replaceAll("<", "&lt;");
				str = str.replaceAll(">", "&gt;");
				array[index] = str1;
			}
			index++;
		}
		return array;
	}

}
