package project.code;
// template
/***FAULT## FAILURE INDUCING CODE***/
/* INDUCING Detail: mutation 
 * ** original code **:
 * 
 * ---------------------
 * ** mutated code **:
 * 
 */

// Mutation 0

public class ComFinManagerM4 {
	/**
	 * Man Power Cost(Salary) when producing
	 * @param manWeekday : men amount work on weekdays
	 * @param manWeekend : men amount work on weekends
	 * @param month : the month want to calculate
	 * @return the total salary company have to pay for men power a month
	 * Description:
	 * 	On 2018, we can compute the man power cost each month on producing.
	 */
	public int produceManPowerCost(int manWeekday, int manWeekend, int month) throws Exception {
		if(manWeekday < 0 || manWeekday > amountLimit) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekend < 0 || manWeekend > amountLimit) {
			throw new Exception("Invalid man amount");
		}

		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		int weekends = mc.weekendsOfMonth(year, month);
		int days = mc.daysOfMonth(year, month);
		int weekdays = days - weekends;
		
		int weekdayWage = weekdayProduceWage;
		int weekendWage = weekendProduceWage;
		
		if(manWeekday > weekdayProduceManLimit) {
			weekdayWage = weekdayProduceOverWage;
		}
		if(manWeekend > weekendProduceManLimit) {
			weekendWage = weekendProduceOverWage;
		}
		
		int weekdayCost = weekdays * manWeekday * weekdayWage;
		int weekendCost = weekends * manWeekend * weekendWage;
		
		manWeekdayProduce = manWeekday;
		manWeekendProduce = manWeekend;
		
		/***FAULT## FAILURE INDUCING CODE***/
		/* INDUCING Detail: mutation 
		 * ** original code **:
		 * weekdayCost + weekendCost
		 * ---------------------
		 * ** mutated code **:
		 * weekdayCost - weekendCost
		 */
		return weekdayCost - weekendCost;
	}
	
	/**
	 * Man Power Cost(Salary) when selling
	 * @param manWeekday : men work on weekdays
	 * @param manWeekend : men work on weekends
	 * @param month : the month wants to calculate
	 * @return the total wages have to pay
	 * Description:
	 * 	In 2018, we can compute the man power cost each month on selling.
	 */
	public int sellingManPowerCost(int manWeekday, int manWeekend, int month) throws Exception{
		if(manWeekday < 0 || manWeekday > amountLimit) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekend < 0 || manWeekend > amountLimit) {
			throw new Exception("Invalid man amount");
		}
		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		int weekends = mc.weekendsOfMonth(year, month);
		int days = mc.daysOfMonth(year, month);
		int weekdays = days - weekends;
		
		int weekdayWage = weekdaySellingWage;
		int weekendWage = weekendSellingWage;
		
		if(manWeekday > weekdaySellingManLimit) {
			weekdayWage = weekdaySellingOverWage;
		}
		if(manWeekend > weekendSellingManLimit) {
			weekendWage = weekendSellingOverWage;
		}
		
		int weekdayCost = weekdays * manWeekday * weekdayWage;
		int weekendCost = weekends * manWeekend * weekendWage;
		
		manWeekdaySelling = manWeekday;
		manWeekendSelling = manWeekend;
		
		/***FAULT## FAILURE INDUCING CODE***/
		/* INDUCING Detail: mutation 
		 * ** original code **:
		 * weekdayCost + weekendCost
		 * ---------------------
		 * ** mutated code **:
		 * weekdayCost
		 */
		return weekdayCost;
	}
	
	/**
	 * Profits when self-producing and self-selling
	 * @param cost : cost of each product
	 * @param price : price of each product
	 * @param month : the target month
	 * @return the margin profits
	 */
	public double profitsSelf(double cost, double price, int month) throws Exception{
		if(cost < 0) {
			throw new Exception("Invalid cost");
		}
		if(price < 0) {
			throw new Exception("Invalid price");
		}
		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		// get the man power cost
		int manCostProduce = this.produceManPowerCost(manWeekdayProduce, manWeekendProduce, month);
		int manCostSelling = this.sellingManPowerCost(manWeekdaySelling, manWeekendSelling, month);
		
		// get days
		int weekends = mc.weekendsOfMonth(year, month);
		int days = mc.daysOfMonth(year, month);
		int weekdays = days - weekends;
		
		// get the producing cost
		int produceAmount = manWeekdayProduce * weekdayProduceAmount * weekdays
				+ manWeekendProduce * weekendProduceAmount * weekends;
		double produceCost = produceAmount * cost;
		
		// get the selling income
		int sellingAmount = manWeekdaySelling * weekdaySellingAmount * weekdays + 
				manWeekendSelling * weekendSellingAmount * weekends;
		if(sellingAmount > produceAmount) {
			sellingAmount = produceAmount;
		}

		double income = sellingAmount * price;
		
		// margin profit
		double profit = income - produceCost - manCostProduce - manCostSelling;
		/***FAULT## FAILURE INDUCING CODE***/
		/* INDUCING Detail: mutation 
		 * ** original code **:
		 * weekdayCost + weekendCost
		 * ---------------------
		 * ** mutated code **:
		 * weekdayCost
		 */
		return profit + 0.1;
	}
	
	
	private static int year = 2018;
	
	// wages
	private static int weekdayProduceWage = 100;
	private static int weekendProduceWage = 120;
	private static int weekdayProduceOverWage = 120;
	private static int weekendProduceOverWage = 150;
	private static int weekdaySellingWage = 200;
	private static int weekendSellingWage = 250;
	private static int weekdaySellingOverWage = 250;
	private static int weekendSellingOverWage = 280;
	
	// workload 
	private static int weekdayProduceAmount = 100;
	private static int weekendProduceAmount = 50;
	private static int weekdaySellingAmount = 25;
	private static int weekendSellingAmount = 75;
	
	// man power limit
	//	private static int manPowerLimitOnProduce = 200;
	//	private static int manPowerLimitOnSelling = 500;
	private static int weekdayProduceManLimit = 300;
	private static int weekendProduceManLimit = 100;
	private static int weekdaySellingManLimit = 400;
	private static int weekendSellingManLimit = 250;
	private static int amountLimit = 500;
		
	// runtime input
	private int manWeekdayProduce = 0;
	private int manWeekendProduce = 0;
	private int manWeekdaySelling = 0;
	private int manWeekendSelling = 0;

	private MyCalendar mc;
	public void setMc(MyCalendar myCalendar) {
		this.mc = myCalendar;
	}
	
	public void setManWeekdayProduce(int manWeekdayProduce) {
		this.manWeekdayProduce = manWeekdayProduce;
	}

	public void setManWeekendProduce(int manWeekendProduce) {
		this.manWeekendProduce = manWeekendProduce;
	}

	public void setManWeekdaySelling(int manWeekdaySelling) {
		this.manWeekdaySelling = manWeekdaySelling;
	}

	public void setManWeekendSelling(int manWeekendSelling) {
		this.manWeekendSelling = manWeekendSelling;
	}
}
