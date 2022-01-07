package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	private DateUtil() {}

	private static class LazyHolder {
		public static final DateUtil INSTANCE = new DateUtil();
	}

	public static DateUtil getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String AddDate(String strDate, int year, int month, int day) throws Exception {
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
