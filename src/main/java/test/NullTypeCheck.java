package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class NullTypeCheck {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		List<String> list = new ArrayList<>();
		String str = "dsa";
		list.add(str);
		System.out.println(list.isEmpty()); // 何も入れてなかったら true
		System.out.println(list == null); //false
		System.out.println(list != null); //true
		if(str == null || "".equals(str)) System.out.println("this is null");
		if(str != null) System.out.println("this is not null");
		int index = 0;
		if(index % 2 == 0) {
			System.out.println("index % 2 = 0");
		}
	}

}
