package project.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import project.code.ComFinManager;
import project.code.ComFinManagerM0;
import project.code.ComFinManagerM1;
import project.code.ComFinManagerM2;
import project.code.ComFinManagerM3;
import project.code.ComFinManagerM4;
import project.code.MyCalendar;
import project.util.FunctionPathMonitor;


/* 11.17 update:
	modify the failure code. now we allow 
all the tests run down even if one test case
fails during the test. we will show all the 
info in the end.
*/
/* 1.5 update
	Add the function path monitor

 */
public class ComFinManagerTest {
	
	static MyCalendar mock_mc;
	static ComFinManager cfm;
//	static ComFinManagerM0 cfm;
	static boolean detailed = false;
	static FunctionPathMonitor fpm;
	@BeforeClass
	public static void setUp() throws Exception {
		// first set up the function path monitor 
		fpm = new FunctionPathMonitor("UC_ALL.uc");
		
		// because cfm will use MyCalendar to get info,
		// we have to mock a MyCalendar to make a stub.
		mock_mc = Mockito.mock(MyCalendar.class);
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				fpm.addCurTestCaseFuncPath("MC.DOM");
				int month = invocation.getArgumentAt(1, Integer.class);
				int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
				if(month < 0 || month > 11) {
					return 0;
				}
				return months[month];
			}
		}).when(mock_mc).daysOfMonth(Mockito.anyInt(), Mockito.anyInt());
		
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				fpm.addCurTestCaseFuncPath("MC.WOM");
				int month = invocation.getArgumentAt(1, Integer.class);
				int[] months = {8, 8, 9, 9, 8, 9, 9, 8, 10, 8, 8, 10};
				if(month < 0 || month > 11) {
					return 0;
				}
				return months[month];
			}
		}).when(mock_mc).weekendsOfMonth(Mockito.anyInt(), Mockito.anyInt());
		
		// stub it into cfm
		cfm = Mockito.mock(ComFinManager.class);
//		cfm = Mockito.mock(ComFinManagerM0.class);
		Mockito.doCallRealMethod().when(cfm).setMc(Mockito.any(MyCalendar.class));
		Mockito.doCallRealMethod().when(cfm).setManWeekdayProduce(Mockito.anyInt());
		Mockito.doCallRealMethod().when(cfm).setManWeekendProduce(Mockito.anyInt());
		Mockito.doCallRealMethod().when(cfm).setManWeekdaySelling(Mockito.anyInt());
		Mockito.doCallRealMethod().when(cfm).setManWeekendSelling(Mockito.anyInt());
		cfm.setMc(mock_mc);
			
	}

	@AfterClass
	public static void tearDown() throws Exception {
//		fpm.generateTestCases("UC_ALL_PATH.uc");
	}

	@Test
	public void testProduceManPowerCost() throws Exception {
		// set
		Mockito.doCallRealMethod().when(cfm).produceManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		// 用例文件
//		String ucPath = "PMPC_UC.uc";
////		String ucPath = "PMPC_UC_pw.uc";
//		
//		// 准备测试用例 PMPC_UC.uc
//		Integer i = -1;
//		FileReader fr = new FileReader(ucPath);
//		BufferedReader br = new BufferedReader(fr);
//		String line = br.readLine();
//		while(line != null) {
		// 准备用例计数11.17mod
		int bad_uc = 0;
		int[] uc_fail = new int[141];
		int amount = fpm.prepareTestCases("CFM.PMPC");
		for(int i = 0; i < amount; i++) {
			// process the line
//			String[] tmp = line.split("\t");
			ArrayList<String> tmp = fpm.fetchNextTestCase();
			int manWeekday = Integer.parseInt(tmp.get(1));
			int manWeekend = Integer.parseInt(tmp.get(2));
			int month = Integer.parseInt(tmp.get(3));
			try {
				if("ex".equals(tmp.get(0))) {
					// wrong output will cause an Exception
					try {
						this.cfm.produceManPowerCost(manWeekday, manWeekend, month);
//						System.out.println("ready"+i);
						fail("Expect Exception at index "+i); // 11.17mod
					} catch(Exception e) {
						// succeed to generate an Exception
//						System.out.println("catch"+i+e.getMessage());
					}
				} else {
					// expect a number output
					int expect = Integer.parseInt(tmp.get(0));
					// test
					assertEquals("Expect "+expect+" at index "+i, 
					 		expect, this.cfm.produceManPowerCost(manWeekday, manWeekend, month));//11.17mod
				}
				uc_fail[i] = 0;
			} catch(Throwable e) {
				//e.printStackTrace();
				uc_fail[i] = 1;
				bad_uc++;
			}
			
		}
		// System.out.println("Test ProduceManPowerCost "+(i+1)+" cases succeed!");//11.17mod
//		br.close();
		System.out.println("Test ProduceManPowerCost - Fail "+bad_uc+", Succ "+(amount-bad_uc));
		if(detailed) {
			for(int uc_i = 0; uc_i < uc_fail.length; uc_i++) {
				System.out.println(uc_fail[uc_i]);
			}
		}
		if(bad_uc != 0) {
			fail("Fail with "+bad_uc+" test cases");
		}
	}

	@Test
	public void testSellingManPowerCost() throws Exception {
		// set
		Mockito.doCallRealMethod().when(cfm).sellingManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		// 用例文件
		String ucPath = "SMPC_UC.uc";
//		String ucPath = "SMPC_UC_pw.uc";
		
		// 准备测试用例 PMPC_UC.uc
//		Integer i = -1;
//		FileReader fr = new FileReader(ucPath);
//		BufferedReader br = new BufferedReader(fr);
//		String line = br.readLine();
//		while(line != null) {
		// 准备用例计数11.17mod
		int bad_uc = 0;
		int[] uc_fail = new int[141];
		int amount = fpm.prepareTestCases("CFM.SMPC");
		for(int i = 0; i < amount; i++) {
			// process the line
			ArrayList<String> tmp = fpm.fetchNextTestCase();
			int manWeekday = Integer.parseInt(tmp.get(1));
			int manWeekend = Integer.parseInt(tmp.get(2));
			int month = Integer.parseInt(tmp.get(3));
			try {
				if("ex".equals(tmp.get(0))) {
					// wrong output will cause an Exception
					try {
						this.cfm.sellingManPowerCost(manWeekday, manWeekend, month);
						fail("Expect Exception at index "+i);//11.17
					} catch(Exception e) {
						// succeed to generate an Exception
					}
				} else {
					// expect a number output
					int expect = Integer.parseInt(tmp.get(0));
					// test
					assertEquals("Expect "+expect+" at index "+i, 
							expect, this.cfm.sellingManPowerCost(manWeekday, manWeekend, month));//11.17
				}
				uc_fail[i] = 0;
			} catch(Throwable e) {
				uc_fail[i] = 1;
				bad_uc++;
			}
		}
		//System.out.println("Test SellingManPowerCost "+(i+1)+" cases succeed!");//11.17
//		br.close();
		System.out.println("Test SellingManPowerCost - Fail "+bad_uc+", Succ "+(amount-bad_uc));
		if(detailed) {
			for(int uc_i = 0; uc_i < uc_fail.length; uc_i++) {
				System.out.println(uc_fail[uc_i]);
			}
		}
		if(bad_uc != 0) {
			fail("Fail with "+bad_uc+" test cases");
		}//11.17
	}

	@Test
	public void testProfitsSelf() throws Exception {
		// 用例文件
		String ucPath = "PS_UC.uc";
//		String ucPath = "PS_UC_pw.uc";
		
		// 准备子函数stub
		// 假设在1，0，8月，300:100，400:250
		Mockito.doCallRealMethod().when(cfm).profitsSelf(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());
		cfm.setManWeekdayProduce(300);
		cfm.setManWeekendProduce(100);
		cfm.setManWeekdaySelling(400);
		cfm.setManWeekendSelling(250);
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				fpm.addCurTestCaseFuncPath("CFM.PMPC");
				int month = invocation.getArgumentAt(2, Integer.class);
				if(month == 0) {
					return 786000;
				} else if(month == 1) {
					return 696000;
				} else if(month == 8) {
					return 720000;
				}
				return 0;
			}
		}).when(cfm).produceManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				fpm.addCurTestCaseFuncPath("CFM.SMPC");
				int month = invocation.getArgumentAt(2, Integer.class);
				if(month == 0) {
					return 2340000;
				} else if(month == 1) {
					return 2100000;
				} else if(month == 8) {
					return 2225000;
				}
				return 0;
			}
		}).when(cfm).sellingManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		
		// 准备测试用例 PS_UC.uc
//		Integer i = -1;
//		FileReader fr = new FileReader(ucPath);
//		BufferedReader br = new BufferedReader(fr);
//		String line = br.readLine();
//		while(line != null) {
		// 准备用例计数11.17
		int bad_uc = 0;
		int[] uc_fail = new int[45];
		int amount = fpm.prepareTestCases("CFM.PS");
		for(int i = 0; i < amount; i++) {
			// process the line
			ArrayList<String> tmp = fpm.fetchNextTestCase();
			double cost = Double.parseDouble(tmp.get(1));
			double price = Double.parseDouble(tmp.get(2));
			int month = Integer.parseInt(tmp.get(3));
			try {
				if("ex".equals(tmp.get(0))) {
					// wrong output will cause an Exception
					try {
						this.cfm.profitsSelf(cost, price, month);
						fail("Expect Exception at index "+i);//11.17
					} catch(Exception e) {
						// succeed to generate an Exception
					}
				} else {
					// expect a number output
					double expect = Double.parseDouble(tmp.get(0));
					// test
					assertEquals("Expect "+expect+" at index "+i, 
							expect, this.cfm.profitsSelf(cost, price, month), 1e-8);//11.17
				}
				uc_fail[i] = 0;
			} catch(Throwable e) {
				uc_fail[i] = 1;
				bad_uc++;
			}
			
		}
		//System.out.println("Test ProfitsSelf "+(i+1)+" cases succeed!");//11.17
		System.out.println("Test ProfitsSelf - Fail "+bad_uc+", Succ "+(amount-bad_uc));
		if(detailed) {
			for(int uc_i = 0; uc_i < uc_fail.length; uc_i++) {
				System.out.println(uc_fail[uc_i]);
			}
		}
		if(bad_uc != 0) {
			fail("Fail with "+bad_uc+" test cases");
		}//11.17
	}

}
