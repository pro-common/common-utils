package com.kaiyun.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
/**
 * DateFormat
 * 
 * @author litaiping
 * @version  DateFormat.java, 2016-6-28 下午3:05:18 litaiping
 */
public class DateFormat {

	/**
	 * 如果只在页面上使用，用jquery.dateFormat-1.0.js里面的$.format.date(time, 'yyyy-MM-dd HH:mm:ss')方法
	 */

    /**
     * 如果只在页面上使用，用jquery.dateFormat-1.0.js里面的$.format.date(time, 'yyyy-MM-dd HH:mm:ss')方法
     */

    private static final String EMPTY = "";

    public static final String UNKNOWN_DATE = "N/A";

    public static final String PATTERN_FULL_MILI_SECOND_FORMAT = "MM-dd HH:mm:ss.SSS";

    public static final String PATTERN_FULL_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_NOT_EMPTY_FORMAT = "yyyyMMddHHmmss";

    public static final String PATTERN_COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String PATTERN_HALF_FORMAT = "yyyy-MM-dd";

    public static final String PATTERN_TIME_FORMAT = "HH:mm";

    public static final String PATTERN_SIMPLE_DATE_FORMAT = "MM-dd HH:mm";

    public static final String PATTERN_CN_FORMAT = "yyyy年MM月dd日";

    public static final String PATTERN_MONTH_FORMAT = "yyyy-MM";

    public static final String HOUR_END = " 23:59:59";

    public static final String HOUR_START = " 00:00:00";

    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static String[] TIME_FORMAT_FULL = { "yyyy-MM-dd HH:mm:ss" };

    /**
     * 时间格式 yyyyMMddHHmmss
     */
    public static String[] TIME_FORMAT_EMPTY = { "yyyyMMddHHmmss" };

    // public static final SimpleDateFormat fullMiliSecondFormat = new
    // SimpleDateFormat(PATTERN_FULL_MILI_SECOND_FORMAT);
    // public static final SimpleDateFormat fullDateFormat = new
    // SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
    // public static final SimpleDateFormat notEmptyFormat = new
    // SimpleDateFormat(PATTERN_NOT_EMPTY_FORMAT);
    // public static final SimpleDateFormat commonDateFormat = new
    // SimpleDateFormat(PATTERN_COMMON_DATE_FORMAT);
    // public static final SimpleDateFormat halfFormat = new
    // SimpleDateFormat(PATTERN_HALF_FORMAT);
    // public static final SimpleDateFormat timeFormat = new
    // SimpleDateFormat(PATTERN_TIME_FORMAT);
    // public static final SimpleDateFormat simpleDateFormat = new
    // SimpleDateFormat(PATTERN_SIMPLE_DATE_FORMAT);
    // public static final SimpleDateFormat cnFormat = new
    // SimpleDateFormat(PATTERN_CN_FORMAT);
    // public static final SimpleDateFormat monthFormat = new
    // SimpleDateFormat(PATTERN_MONTH_FORMAT);

	public static Date parse(String time) throws ParseException {

	    SimpleDateFormat fullDateFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
		return fullDateFormat.parse(time);
	}

	public static final String simpleFormat(Date date) {

	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN_SIMPLE_DATE_FORMAT);
		return doFormat(simpleDateFormat, date);
	}

	public static final String halfFormat(Date date) {

	    SimpleDateFormat halfFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
		return doFormat(halfFormat, date);
	}

	public static final String fullFormat(Date date) {

	    SimpleDateFormat fullDateFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
		return doFormat(fullDateFormat, date);
	}

	public static String cnFormat(Date date) {

	    SimpleDateFormat cnFormat = new SimpleDateFormat(PATTERN_CN_FORMAT);
		return doFormat(cnFormat, date);
	}

	public static String commonFormat(Date date) {

	    SimpleDateFormat commonDateFormat = new SimpleDateFormat(PATTERN_COMMON_DATE_FORMAT);
		return doFormat(commonDateFormat, date);
	}

	public static String timeFormat(Date date) {

	    SimpleDateFormat timeFormat = new SimpleDateFormat(PATTERN_TIME_FORMAT);
		return doFormat(timeFormat, date);
	}

	public static String fullMiliSecondFormat(Date date) {

	    SimpleDateFormat fullMiliSecondFormat = new SimpleDateFormat(PATTERN_FULL_MILI_SECOND_FORMAT);
		return doFormat(fullMiliSecondFormat, date);
	}

    public static final String parseFromStr(String date) {
        if(StringUtils.isEmpty(date))
            return null;
        SimpleDateFormat fullDateFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
        try {
            return fullFormat(fullDateFormat.parse(date));
        } catch (ParseException e) {
        }
        return null;
    }
	private static String doFormat(SimpleDateFormat format, Date date) {

		if (date == null) {
			return EMPTY;
		}
		return format.format(date);
	}

	public static Date halfFormatParse(String time) throws ParseException {

	    SimpleDateFormat halfFormat = new SimpleDateFormat(PATTERN_HALF_FORMAT);
		return halfFormat.parse(time);
	}

	public static Date endOneDay(Date date) {

		try {
			String halfFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(halfFormat + HOUR_END);
		} catch (ParseException e) {
			return date;
		}
	}

	public static Date startOneDay(Date date) {

		try {
			String halfFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(halfFormat + HOUR_START);
		} catch (ParseException e) {
			return date;
		}
	}

	public static Date startOneDay(String startDate) {

		try {
			return DateFormat.parse(startDate + HOUR_START);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static Date endOneDay(String endDate) {

		try {
			return DateFormat.parse(endDate + HOUR_END);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static final String notEmptyFormat(Date date) {

	    SimpleDateFormat notEmptyFormat = new SimpleDateFormat(PATTERN_NOT_EMPTY_FORMAT);
		return doFormat(notEmptyFormat, date);
	}

	/**
	 * 取得指定日期是星期几
	 * 
	 * @param date 指定日期
	 * @return int 0=星期天 1=星期一 2=星期二 3=星期三 4=星期四 5=星期五 6=星期六 7=错误
	 */
	public static int getWeekDate(Date date) {

		try {
			java.util.Calendar calender = java.util.Calendar.getInstance();
			calender.setTime(date);

            int week = calender.get(Calendar.DAY_OF_WEEK) - 1;
			return week;
		} catch (Exception e) {
			return 7;
		}
	}

	/**
	 * 判断是否在指定时间内
	 * 
	 * @param date 指定日期
	 * @param beginHour 指定开始小时
	 * @param endHour 指定结束小时
	 * @return boolean true 在指定时间内，false不在指定时间内
	 */
	public static boolean isBetweenTime(Date date, int beginHour, int endHour) {

			java.util.Calendar calender = java.util.Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR_OF_DAY, beginHour);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);
			Date beginDate = calender.getTime();

			calender = java.util.Calendar.getInstance();
			calender.setTime(date);
			calender.set(Calendar.HOUR_OF_DAY, endHour);
			calender.set(Calendar.MINUTE, 0);
			calender.set(Calendar.SECOND, 0);

			Date endDate = calender.getTime();

        if (beginDate.before(date) && endDate.after(date)) {

				return true;
			}

			return false;
	}

 

    /**
     * 获取当月第一天
     * 
     * @return
     */
    public static Date getFirstDayOfCurrentMonth() {

        Calendar cal = Calendar.getInstance();
        // 当前月的第一天
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return startOneDay(cal.getTime());
    }

    /**
     * 获取 当月最后一天
     * 
     * @return
     */
    public static Date getEndDayOfCurrentMonth() {

        Calendar cal = Calendar.getInstance();
        // 当前月的最后一天
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        return endOneDay(cal.getTime());
    }

    /**
     * 获取 当月 yyyyMM
     * @return
     */
    public static String getCurrentMonth() {
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM");
    	 return doFormat(dateFormat, new Date());
    }
	public static void main(String[] args){
		System.out.println(getNYearAfter(1,new Date()).toLocaleString());
		
	}
    /**
     * 日期转换 传入为YYYY-MM-DD格式 返回该日最小值
     * 
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date halfFormatParseStart(String time) throws ParseException {

        SimpleDateFormat halfFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
        return halfFormat.parse(time + HOUR_START);
    }

    /**
     * 日期转换 传入为YYYY-MM-DD格式 返回该日最大值
     * 
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date halfFormatParseEnd(String time) throws ParseException {

        SimpleDateFormat halfFormat = new SimpleDateFormat(PATTERN_FULL_DATE_FORMAT);
        return halfFormat.parse(time + HOUR_END);
    }

    /**
     * 获取本周第一天
     * 
     * @return
     */
    public static Date getFirstDayOfCurrentWeek() {

        Calendar c = Calendar.getInstance();
        int weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
        c.add(Calendar.DATE, -weekday);
        return startOneDay(c.getTime());
    }

    /**
     * 获取本周最后一天
     * 
     * @return
     */
    public static Date getEndDayOfCurrentWeek() {

        Calendar c = Calendar.getInstance();
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, 7 - weekday);
        return endOneDay(c.getTime());

    }

    /**
     * 获取几个月前第一天
     * 
     * @param nMonthAgo 几个月前
     * @return
     */
    public static Date getFirstDayOfNMonthAgo(int nMonthAgo) {

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, -nMonthAgo);

        // n月前的第一天
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return startOneDay(cal.getTime());
    }

    /**
     * 获取几个月前最后一天
     * 
     * @param nMonthAgo 几个月前
     * @return
     */
    public static Date getEndDayOfNMonthAgo(int nMonthAgo) {

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, -nMonthAgo + 1);

        // n月前的最后一天
        cal.set(Calendar.DAY_OF_MONTH, 0);

        return endOneDay(cal.getTime());

    }

    /**
     * 
     * @return
     */
    public static Date getYesterdayStart() {

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, -1);

        return startOneDay(cal.getTime());

    }

    /**
     * 
     * @return
     */
    public static Date getYesterdayEnd() {

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.DAY_OF_MONTH, -1);

        return endOneDay(cal.getTime());

    }

    /**
     * 获取 上个月最后一天
     * 
     * @return
     */
    public static Date getEndDayOfLastMonth() {

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MONTH, 0);

        // n月前的最后一天
        cal.set(Calendar.DAY_OF_MONTH, 0);

        return endOneDay(cal.getTime());
    }
    
    
    
    
    /**
     * 获取n年后的某天的开始时间
     * 
     * @return
     */
    public static Date getNYearAfter(int n,Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        c.add(Calendar.YEAR, n);
        return startOneDay(c.getTime());
    }
    
    
    
    /**
     * 获取某天N天前/后的的当前时间,不是开始时间
     * 
     * @return
     */
    public static Date getNDaysAfter(int n,Date date) {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime();
    }    
    
    /**
     * 获取日期 格式 yyyyMMdd
     * @return
     */
    public static String getCurrentDate() {
    	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    	 return doFormat(dateFormat, new Date());
    }
    
    /**
     * 获取当前时间 格式 HHmmss
     * @return
     */
    public static String getCurrentTime() {
	    SimpleDateFormat timeFormat = new SimpleDateFormat("HHmmss");
		return doFormat(timeFormat, new Date());
	}
    public static String getTimeStrByFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return doFormat(dateFormat, new Date());
    }

    // public static void main(String[] args) {
    //
    //
    // System.out.println("getFirstDayOfCurrentMonth="+getFirstDayOfCurrentMonth().toLocaleString());
    // System.out.println("getEndDayOfCurrentMonth="+getEndDayOfCurrentMonth().toLocaleString());
    //
    // System.out.println("getFirstDayOfCurrentWeek="+getFirstDayOfCurrentWeek().toLocaleString());
    // System.out.println("getEndDayOfCurrentWeek="+getEndDayOfCurrentWeek().toLocaleString());
    //
    // System.out.println("getFirstDayOfNMonthAgo="+getFirstDayOfNMonthAgo(1).toLocaleString());
    // System.out.println("getEndDayOfNMonthAgo="+getEndDayOfNMonthAgo(1).toLocaleString());
    //
    // System.out.println("getYesterdayStart="+getYesterdayStart().toLocaleString());
    // System.out.println("getYesterdayEnd="+getYesterdayEnd().toLocaleString());
    //
    // System.out.println("getEndDayOfLastMonth="+getEndDayOfLastMonth().toLocaleString());
    //
    // System.out.println("getFirstDayOfCurrentWeek="+getFirstDayOfCurrentWeek().toLocaleString());
    // System.out.println("getEndDayOfCurrentWeek="+getEndDayOfCurrentWeek().toLocaleString());
    // try {
    // System.out.println(halfFormatParseStart("2014-09-18"));
    // } catch (ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    //
    // }
    //
    //

}
