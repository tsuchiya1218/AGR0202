package test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class DateTest {

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() throws Exception {
//		String brith = "2022-12-01";
//		Date date = Date.valueOf(brith);
//		String dateToString = date.toString();
//		System.out.println(date);
//		System.out.println(dateToString);
		String date2 = "2020-08-01";
		String addYear = AddDate(date2, 1, 0, 0);
		String addMonth = AddDate(date2, 0, 1, 0);
		String addDay = AddDate(date2, 0, 0, 53);
		System.out.println(addYear);
		System.out.println(addMonth);
		System.out.println(addDay);
	}

	private static String AddDate(String strDate, int year, int month, int day) throws Exception {
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date dt = Date.valueOf(strDate);
		cal.setTime(dt);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DATE, day);
		return dtFormat.format(cal.getTime());
	}

}
