package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class HashMapTest {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		Map<String,String> str = new HashMap<String,String>();
		str.put("email", "aaa@aaa");
		//input text type
				for (String ary : str.keySet()) {
						System.out.println(str.get(ary));
				}
	}

}
