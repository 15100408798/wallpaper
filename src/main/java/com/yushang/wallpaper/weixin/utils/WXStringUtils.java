package com.yushang.wallpaper.weixin.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WXStringUtils {
	
	private static final String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	
	/**
	 * 判断手机号是否是11位
	 */
	public static boolean phoneIs11(String phone) {
		return phone.length() == 11 ? true : false;
	}
	
	/**
	 * 验证手机号是否合法
	 */
	public static boolean isPhone(String phone) {
		Matcher m = Pattern.compile(regex).matcher(phone);
	    return m.matches();
	}

	/**
	 * 获取现在距离下一天00：00：00的秒值
	 */
	public static long secondTime() throws ParseException {
		//获得日历类
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH,1);
		//获得下一天时间
		Date date = calendar.getTime();
		//格式化时间
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String nextTime = simpleDateFormat.format(date);
		//得到下一天零点的毫秒值
		long nextSecondTime = simpleDateFormat.parse(nextTime).getTime();
		//今天的毫秒值
		long nowSecondTime = new Date().getTime();
		//得到相差的毫秒值
		return  (nextSecondTime-nowSecondTime)/1000;
	}


	/**
	 * 获取一定长度的随机字符串
	 * @param length 指定字符串长度
	 * @return 一定长度的字符串
	 */
	public static String getRandomStringByLength(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}


}
