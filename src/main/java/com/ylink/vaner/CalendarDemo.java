package com.ylink.vaner;

import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
//		calendar.set(2018, 5, 19);
		System.out.println("现在时间是：" + new Date());
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		String week = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		System.out.println("现在时间是：" + year + "年" + month + "月" + day + "日，星期" + week);
		
		//这年中的第几天
		System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
		//这个月中的第几个星期
		System.out.println(calendar.get(Calendar.WEEK_OF_MONTH));
		//这个月中的第几天
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
		
		
		long now = calendar.getTimeInMillis();
		calendar.set(2016, 6, 19);// 这里与真实的月份之间相差1
		long old = calendar.getTimeInMillis();
		long days = (now - old) / (1000 * 60 * 60 * 24);
		System.out.println("今天和那一天相隔" + days + "天，" + "也就是说我在这个美丽的星球上已经幸福的生活了" + days + "天。");

	}
}