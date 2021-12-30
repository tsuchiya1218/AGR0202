package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class RegTest {
	String patternNum = "^[0-9]*$";
	String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
	String patternString = "^[A-Za-z0-9]{0,64}$";
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String str = "adsadsad sa321";
		System.out.println("111".matches(patternNum)); //true
		System.out.println("asd@asd".matches(patternEmail)); //false .com => true
		System.out.println(str.matches(patternString)); // false
		System.out.println(str.replaceAll("\\s", ""));
		int year = 32;
		if(year < 1 || year > 31) {
			System.out.println("brith error");
		}
	}

}
