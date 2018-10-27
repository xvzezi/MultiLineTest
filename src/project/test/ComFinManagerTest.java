package project.test;

import static org.junit.Assert.*;

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
		this.cfm.setMc(mock_mc);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testProduceManPowerCost() throws Exception {
		fail("Not yet implemented");
	}

	@Test
	public void testSellingManPowerCost() {
		fail("Not yet implemented");
	}

	@Test
	public void testProfitsSelf() {
		fail("Not yet implemented");
	}

}
