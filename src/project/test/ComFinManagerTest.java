package project.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import project.code.ComFinManager;
import project.code.MyCalendar;

public class ComFinManagerTest {
	
	MyCalendar mock_mc;
	ComFinManager cfm;
	
	@Before
	public void setUp() throws Exception {
		// because cfm will use MyCalendar to get info,
		// we have to mock a MyCalendar to make a stub.
		mock_mc = Mockito.mock(MyCalendar.class);
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int month = invocation.getArgumentAt(1, Integer.class);
				int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
				return months[month];
			}
		}).when(mock_mc).daysOfMonth(Mockito.anyInt(), Mockito.anyInt());
		
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				int month = invocation.getArgumentAt(1, Integer.class);
				int[] months = {8, 8, 9, 9, 8, 9, 9, 8, 10, 8, 8, 10};
				return months[month];
			}
		}).when(mock_mc).weekendsOfMonth(Mockito.anyInt(), Mockito.anyInt());
		
		// stub it into cfm
		cfm = Mockito.mock(ComFinManager.class);
		Mockito.doCallRealMethod().when(cfm).setMc(Mockito.any(MyCalendar.class));
		cfm.setMc(mock_mc);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProduceManPowerCost() throws Exception {
		// set
		Mockito.doCallRealMethod().when(cfm).produceManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		// 用例文件
//		String ucPath = "PMPC_UC.uc";
		String ucPath = "PMPC_UC_pw.uc";
		
		// 准备测试用例 PMPC_UC.uc
		Integer i = -1;
		FileReader fr = new FileReader(ucPath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null) {
			// process the line
			String[] tmp = line.split("\t");
			int manWeekday = Integer.parseInt(tmp[1]);
			int manWeekend = Integer.parseInt(tmp[2]);
			int month = Integer.parseInt(tmp[3]);
			i++;
			if("ex".equals(tmp[0])) {
				// wrong output will cause an Exception
				try {
					this.cfm.produceManPowerCost(manWeekday, manWeekend, month);
					fail("Expect Exception at index "+i);
				} catch(Exception e) {
					// succeed to generate an Exception
				}
			} else {
				// expect a number output
				int expect = Integer.parseInt(tmp[0]);
				// test
				assertEquals("Expect "+expect+" at index "+i, 
						expect, this.cfm.produceManPowerCost(manWeekday, manWeekend, month));
			}
			
			// next line 
			line = br.readLine();
		}
		System.out.println("Test ProduceManPowerCost "+(i+1)+" cases succeed!");
	}

	@Test
	public void testSellingManPowerCost() throws Exception {
		// set
		Mockito.doCallRealMethod().when(cfm).sellingManPowerCost(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		// 用例文件
		String ucPath = "SMPC_UC.uc";
//		String ucPath = "SMPC_UC_pw.uc";
		
		// 准备测试用例 PMPC_UC.uc
		Integer i = -1;
		FileReader fr = new FileReader(ucPath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null) {
			// process the line
			String[] tmp = line.split(" ");
			int manWeekday = Integer.parseInt(tmp[1]);
			int manWeekend = Integer.parseInt(tmp[2]);
			int month = Integer.parseInt(tmp[3]);
			i++;
			if("ex".equals(tmp[0])) {
				// wrong output will cause an Exception
				try {
					this.cfm.sellingManPowerCost(manWeekday, manWeekend, month);
					fail("Expect Exception at index "+i);
				} catch(Exception e) {
					// succeed to generate an Exception
				}
			} else {
				// expect a number output
				int expect = Integer.parseInt(tmp[0]);
				// test
				assertEquals("Expect "+expect+" at index "+i, 
						expect, this.cfm.sellingManPowerCost(manWeekday, manWeekend, month));
			}
			
			// next line 
			line = br.readLine();
		}
		System.out.println("Test SellingManPowerCost "+(i+1)+" cases succeed!");
	}

	@Test
	public void testProfitsSelf() throws Exception {
		// 用例文件
		String ucPath = "PS_UC.uc";
//		String ucPath = "PS_UC_pw.uc";
		
		// 准备子函数stub
		// TODO
		Mockito.doCallRealMethod().when(cfm).profitsSelf(Mockito.anyDouble(), Mockito.anyDouble(), Mockito.anyInt());
		
		
		// 准备测试用例 PS_UC.uc
		Integer i = -1;
		FileReader fr = new FileReader(ucPath);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while(line != null) {
			// process the line
			String[] tmp = line.split(" ");
			double cost = Double.parseDouble(tmp[1]);
			double price = Double.parseDouble(tmp[2]);
			int month = Integer.parseInt(tmp[3]);
			i++;
			if("ex".equals(tmp[0])) {
				// wrong output will cause an Exception
				try {
					this.cfm.profitsSelf(cost, price, month);
					fail("Expect Exception at index "+i);
				} catch(Exception e) {
					// succeed to generate an Exception
				}
			} else {
				// expect a number output
				double expect = Double.parseDouble(tmp[0]);
				// test
				assertEquals("Expect "+expect+" at index "+i, 
						expect, this.cfm.profitsSelf(cost, price, month), 1e-8);
			}
			
			// next line 
			line = br.readLine();
		}
	}

}
