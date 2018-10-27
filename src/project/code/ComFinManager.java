/**
 * Problem Description(In Chinese):
 * 	2018年，公司要求制作一个月毛利计算工具。能够计算2018年内的月
 * 毛利。
 * 	在公司中，分为生产与销售两个部分。
 * 	生产部分的成本由两部分决定。第一部分是人力资源，分为在工作日
 * 工作的一批与周末工作的一批。在工作日工作日薪为70，在周末为90；
 * 而由于公司规划限制，两批人次总数不得超过200人。工作日，每个熟
 * 练工人可以生产100产品；而周末为了保养，每人每天生产量降低至50.
 * 第二部分是生产成本，由市场临时决定。
 * 	销售部分的成本来自于销售人员。工作日日薪为100，周末为150；同
 * 样由于规模限制，人数不得超过500人。工作人员在工作日的销量为平
 * 均25，在周末为75.
 * 	销售模式方面分为两种。一种是销售自己生产的物品；另一种是代理
 * 其他工厂产品，而本公司不生产。销售的价格由市场临时决定。
 */

package project.code;


/**
 * Company Financial Manager
 * @author Xu Zezi
 * @version 0.1
 * Description:
 * 	1. Compute the producing man power cost
 * 	2. Compute the selling man power cost
 * 	3. Compute the self-sales margin profits
 * 	4. Compute the agent-sales margin profits
 */
public class ComFinManager {
	private static int year = 2018;
	
	// wages
	private static int weekdayProduceWage = 70;
	private static int weekendProduceWage = 90;
	private static int weekdaySellingWage = 100;
	private static int weekendSellingWage = 150;
	
	// workload 
	private static int weekdayProduceAmount = 100;
	private static int weekendProduceAmount = 50;
	private static int weekdaySellingAmount = 25;
	private static int weekendSellingAmount = 75;
	
	// man power limit
	private static int manPowerLimitOnProduce = 200;
	private static int manPowerLimitOnSelling = 500;
	
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
		if(manWeekday < 0) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekend < 0) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekday + manWeekend > manPowerLimitOnProduce) {
			throw new Exception("Too much man power");
		}
		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		int weekdays = weekdays_stub(month);
		int days = days_stub(month);
		int weekends = days - weekdays;
		
		int weekdayCost = weekdays * manWeekday * weekdayProduceWage;
		int weekendCost = weekends * manWeekend * weekendProduceWage;
		
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
		if(manWeekday < 0) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekend < 0) {
			throw new Exception("Invalid man amount");
		}
		if(manWeekday + manWeekend > manPowerLimitOnSelling) {
			throw new Exception("Too much man power");
		}
		if(month < 0 || month > 11) {
			throw new Exception("Month out of range");
		}
		
		int weekdays = weekdays_stub(month);
		int days = days_stub(month);
		int weekends = days - weekdays;
		
		int weekdayCost = weekdays * manWeekday * weekdaySellingWage;
		int weekendCost = weekends * manWeekend * weekendSellingWage;
		
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
