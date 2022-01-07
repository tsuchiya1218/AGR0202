package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class BirthRegTest {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		String[] ep_expiry_date = {"2022","12","31"};
		String birth = ep_expiry_date[0]+"-"+ep_expiry_date[1]+"-"+ep_expiry_date[2];
		String patternNum = "^[0-9]*$";
		System.out.println(birth.replaceAll("-", ""));
		System.out.println(birth.replaceAll("-", "").matches(patternNum));
		
		if (ep_expiry_date[0].length() < 4 || ep_expiry_date[1].length() < 2 || ep_expiry_date[2].length() < 2
				|| Integer.parseInt(ep_expiry_date[1]) < 1 || Integer.parseInt(ep_expiry_date[1]) > 12
				|| Integer.parseInt(ep_expiry_date[2]) < 1 || Integer.parseInt(ep_expiry_date[2]) > 31) {
			System.out.println("正しい有効時間を入力してください。");
		}
	}

}
