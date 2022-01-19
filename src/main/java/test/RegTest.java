package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class RegTest {
	String patternNum = "^[0-9]*$";
	String patternEmail = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
	String patternString = "^[A-Za-z0-9]{0,64}$";
	String regPm = "^[0-9]+(.)?[0-9]*$";
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String str = "adsadsad sa321";
		String num = "1";
		System.out.println("12".matches(patternNum) + "\t"+"patternNumber"); //数字のみの場合 true
		System.out.println("".matches(patternEmail)); //<= false  asd@asd.com => true
		System.out.println(str.matches(patternString)); // false
		System.out.println(str.replaceAll("\\s", ""));
		System.out.println(num.matches(regPm));
	}

}
