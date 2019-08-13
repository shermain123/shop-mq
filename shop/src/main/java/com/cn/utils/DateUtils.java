package com.cn.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/***
	 * 时间格式转化 String-->Date
	 * 
	 * @param strTime
	 * @param formatType
	 * @return
	 */
	public static Date stringToDate(String strTime, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		Date date = null;
		try {
			date = sdf.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String dateToString(Date dateTime, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		String strTime = null;
		strTime = sdf.format(dateTime);
		return strTime;
	}

	/**
	 * @param date1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
	 * @param date2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return 举例： compareDate("2009-09-12", null, 0);//比较天
	 *         compareDate("2009-09-12", null, 1);//比较月
	 *         compareDate("2009-09-12", null, 2);//比较年
	 */
	public static int compareDate(String startDay, String endDay, int stype) {
		int n = 0;
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		endDay = endDay == null ? getCurrentDate("yyyy-MM-dd") : endDay;

		DateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}

		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}

		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}
	
	public static String getCurrentDate(String format) {
		Calendar day = Calendar.getInstance();
		day.add(Calendar.DATE, 0);
		SimpleDateFormat sdf = new SimpleDateFormat(format);// "yyyy-MM-dd"
		String date = sdf.format(day.getTime());
		return date;
	}

	public static String getCurYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return sdf.format(date);
	}

	public static int getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		return Integer.parseInt(sdf.format(date));
	}
	//多少年的日期
	public static String moreYear(int year){
		 Calendar calendar = Calendar.getInstance();       
		 Date date = new Date(System.currentTimeMillis());        
		 calendar.setTime(date);
		 calendar.add(Calendar.YEAR, year);        
		 date = calendar.getTime();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 return sdf.format(date);
	}
	
	public static String addDate(String dateStr, int month) {
		Calendar ca = Calendar.getInstance();
		String s = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dd;
		try {
			dd = sdf.parse(dateStr);
			ca.setTime(dd);
			ca.add(Calendar.MONTH, month);
			s = sdf.format(ca.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return s;
	}
	
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

}
