/**
 * Problem Description(In Chinese):
 * 	2018年，公司要求制作一个月毛利计算工具。能够计算2018年内的月
 * 毛利。
 * 	在公司中，分为生产与销售两个部分。
 * 	生产部分的成本由两部分决定。第一部分是人力资源，分为在工作日
 * 工作的一批与周末工作的一批。在工作日工作日薪为100，在周末为120；
 * 当工作日工作人人数超过300人时，根据相关规定需要涨薪，日薪为120，
 * 当周末工作人数超过100人时，日薪涨为150。工作日，每个熟练工人可
 * 以生产100产品；而周末为了保养，每人每天生产量降低至50.
 * 	第二部分是生产成本，由市场临时决定。
 * 	销售部分的成本来自于销售人员。工作日日薪为200，周末为250；同
 * 样，工作日工作人数超过400人，日薪升为250，周末工作人数超过250，
 * 日薪升为280。工作人员在工作日的销量为平均25，在周末为75.
 * 	销售模式方面分为两种。一种是销售自己生产的物品；另一种是代理
 * 其他工厂产品，而本公司不生产。销售的价格由市场临时决定。
 * 	每种岗位的雇佣人数不得超过500.
 */

package project.code;


/**
 * Company Financial Manager
 * @author Xu Zezi
 * @version 0.5
 * Description:
 * 	1. Compute the producing man power cost
 * 	2. Compute the selling man power cost
 * 	3. Compute the self-sales margin profits
 * 	4. Compute the agent-sales margin profits
 */
public class ComFinManager {
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
	
	private int days_stub(int month) {
		return 31;
	}
	
	private int weekdays_stub(int month) {
		return 15;
	}
	
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
		
		int weekdays = weekdays_stub(month);
		int days = days_stub(month);
		int weekends = days - weekdays;
		
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
		
		return weekdayCost + weekendCost;
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
		
		int weekdays = weekdays_stub(month);
		int days = days_stub(month);
		int weekends = days - weekdays;
		
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
		
		return weekdayCost + weekendCost;
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
		
		// get the producing cost
		int produceAmount = manWeekdayProduce * weekdayProduceAmount + manWeekendProduce * weekendProduceAmount;
		double produceCost = produceAmount * cost;
		
		// get the selling income
		int sellingAmount = manWeekdaySelling * weekdaySellingAmount + manWeekendSelling * weekendSellingAmount;
		double income = sellingAmount * price;
		
		// margin profit
		double profit = income - produceCost - manCostProduce - manCostSelling;
		return profit;
	}
	
	/**
	 * Profits when act as an agent
	 * @param amount : amount of goods to store
	 * @param cost : cost per products
	 * @param price : price per products
	 * @param month : the target month
	 * @return the margin profits
	 */
	public double profitsAgent(int amount, double cost, double price, int month) throws Exception{
		if(amount < 0) {
			throw new Exception("Invalid amount");
		}
		if(cost < 0) {
			throw new Exception("Invalid cost");
		}
		if(price < 0) {
			throw new Exception("Invalid price");
		}
		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		// get the man power cost, we don't have to produce
		int manCostSelling = this.sellingManPowerCost(manWeekdaySelling, manWeekendSelling, month);
		
		// get the purchasing cost
		double stockCost = amount * cost;
		
		// get the selling income
		int sellingAmount = manWeekdaySelling * weekdaySellingAmount + manWeekendSelling * weekendSellingAmount;
		double income = sellingAmount * price;
		
		// margin profit
		double profit = income - stockCost - manCostSelling;
		return profit;
	}
}
