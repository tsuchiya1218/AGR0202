package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class XssFilterTest {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Map<String,String> map = new HashMap<String,String>();
		map.put("email", "123@123");
		String testStr = "0";
//		map = stripTagAll(map);
		System.out.println("1".equals(testStr) ? true : false);
		
		String[] str = new String[] {"12-12","123"};
		str = stripTagAll(str);
		System.out.println(str[0]);
		
		System.out.println(stripTagNum(map));
		
		
	}
	public static Map<String,String> stripTagAll(Map<String, String> map){
		for(String key : map.keySet()) {
			String str = map.get(key);
			str = str.replaceAll("1", "5");
			str = str.replaceAll(">", "&gt;");
			map.put(key, str);
		}
		return map;
	}
	public static Map<String,String> stripTagNum(Map<String, String> map){
		for(String key : map.values()) {
			key = key.replaceAll("123", "567");
			System.out.println(key+"    num method");
			key = key.replaceAll("1", "5");
			key = key.replaceAll(">", "&gt;");
		}
		return map;
	}
	public static String[] stripTagAll(String[] array){
		int index = 0;
		for(String str : array) {
			System.out.println(str);
			str = str.replaceAll("-", " ");
			str = str.replaceAll(">", "&gt;");
			array[index] = str;
			
			index++;
			
		}
		return array;
	}

}
