package com.eling.elcms.assistant.api.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

public class DateUtils {

	public static final String datePattern = "yyyy-MM-dd";
	public static final String datePatternStringWithHMS = "yyyyMMddHHmmss";
	public static final String datePatternStringWithHM = "yyyyMMddHHmm";

	public static String parseDateToString(Date date, String format) {
		FastDateFormat sdf = FastDateFormat.getInstance(format);
		return sdf.format(date);
	}

	public static Date strToDate(String src, String format) {
		java.util.Date temp = null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			temp = dateFormat.parse(src);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static Date strToDate(String src) {
		return strToDate(src, "yyyy-MM-dd");
	}

	public static Date strToDatehhmmss(String src) {
		return strToDate(src, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getNow(String fmt) {

		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		Date date = new Date();
		String dateStr = sdf.format(date);
		return dateStr;
	}

	/**
	 * 得到UTC时间，类型为字符串，格式为"yyyyMMddHHmmss"<br />
	 * 如果获取失败，返回null
	 * 
	 * @return
	 */
	public static String getUTC0TimeStr() {

		StringBuffer UTCTimeBuffer = new StringBuffer();
		// 1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		UTCTimeBuffer.append(year);
		if (month < 10) {
			UTCTimeBuffer.append("0" + month);
		} else {
			UTCTimeBuffer.append(month);
		}
		if (day < 10) {
			UTCTimeBuffer.append("0" + day);
		} else {
			UTCTimeBuffer.append(day);
		}
		if (hour < 10) {
			UTCTimeBuffer.append("0" + hour);
		} else {
			UTCTimeBuffer.append(hour);
		}
		if (minute < 10) {
			UTCTimeBuffer.append("0" + minute);
		} else {
			UTCTimeBuffer.append(minute);
		}
		// format.parse(UTCTimeBuffer.toString()) ;
		return UTCTimeBuffer.toString();
	}
	/**
	 * 获取当前日期之后的某个时间
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date,int minute){
		
		
		Calendar curr = Calendar.getInstance();
		curr.setTime(date);
		curr.set(Calendar.MINUTE,curr.get(Calendar.MINUTE)+minute);
		return curr.getTime();
	}
	public static void main(String args[]) {
		System.out.println(addMinute(new Date(),1));
	}
	
}
