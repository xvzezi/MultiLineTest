package project.code;

public class MyCalendar {
	
	public static boolean isLeapYear(int year) {
		if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
			return true;
		else 
			return false;
	}
	
	public static int daysOfMonth(int year, int month) throws Exception {
		month += 1;
		switch(month) {
		case 1:
			return 31;
		case 2:
			if (isLeapYear(year))
				return 29;
			else
				return 28;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			throw new Exception("Invalid Month");
		}
	}
	
	public static int weekendsOfMonth(int year, int month) throws Exception {
		int days = daysOfMonth(year, month);
		int weekends = 0;
		for(int i = 1; i <= days; i ++) {
			int dayOfWeek = getWeek(year, month, i);
			if(dayOfWeek == 0 || dayOfWeek == 6)
				weekends ++;
		}
		return weekends;
	}
	
	/**
	 * 计算任意时间是星期几
	 * 利用蔡勒公式
	 * 公式：（年份代码+月份代码+日期）mod 7
	 * @param year
	 * @param month(0-11)
	 * @param day
	 * @return
	 */
	public static int getWeek(int year,int month,int day){
		month += 1;
		while(year < 1700 || year > 2099){
			if(year < 1700){
				year += 400;
			}else if(year > 2099){
				year -= 400;
			}
		}
		int x = year % 1000% 100;
		int srcYear = (x / 4 + x) % 7;
		if(year >= 1700 && year <= 1799){
			srcYear += 5;
		}else if(year >= 1800 && year <= 1899){
			srcYear += 3;
		}else if(year >= 1900 && year <= 1999){
			srcYear += 1;
		}
		int srcMonth = 0;
		switch(month){
		case 5:
			srcMonth = 0;
			break;
		case 8:
			srcMonth = 1;
			break;
		case 2:
		case 3:
		case 11:
			srcMonth = 2;
			break;
		case 6:
			srcMonth = 3;
			break;
		case 9:
		case 12:
			srcMonth = 4;
			break;
		case 4:
		case 7:
			srcMonth = 5;
			break;
		case 1:
		case 10:
			srcMonth = 6;
			break;
		}
		//如果是闰年，改变月份代码的值
		if(isLeapYear(year)){
			switch(month){
			case 1:
				srcMonth = 5;
				break;
			case 2:
				srcMonth = 1;
				break;
					
			}
		}
		int week = (srcYear + srcMonth + day) % 7;
		return week;
	}


}
