package com.vanke.journal.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	private static SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat formatTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date toDate(String date) throws Exception {
		return formatTimestamp.parse(date);
	}

	public static String toDateString(Date date) {
		return formatDate.format(date);
	}

	public static String toTimeString(Date date) {
		return formatTime.format(date);
	}

	public static String toTimestampString(Date date) {
		return formatTimestamp.format(date);
	}

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(TimeUtil.toDateString(now));
		System.out.println(TimeUtil.toTimeString(now));
		System.out.println(TimeUtil.toTimestampString(now));
	}

}
