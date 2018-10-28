package project.test;

import project.code.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class MyCalendarTest {
	
	MyCalendar mock_mc;
	MyCalendar mc;

	@Before
	public void setUp() throws Exception {
		//mc = new MyCalendar();
		mock_mc = Mockito.mock(MyCalendar.class);
		

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsLeapYear() {
		Mockito.doCallRealMethod().when(mock_mc).isLeapYear(Mockito.anyInt());
		assertEquals(true, mock_mc.isLeapYear(2000));
		assertEquals(true, mock_mc.isLeapYear(2008));
		assertEquals(false, mock_mc.isLeapYear(2018));
	}

	@Test
	public void testDaysOfMonth() throws Exception {
		Mockito.doCallRealMethod().when(mock_mc).daysOfMonth(Mockito.anyInt(), Mockito.anyInt());
//		Mockito.doCallRealMethod().when(mock_mc).isLeapYear(Mockito.anyInt());
		Mockito.doAnswer(new Answer<Boolean>() {
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				int year = invocation.getArgumentAt(0, Integer.class);
				if(year == 2000 || year == 2008)
					return true;
				else
					return false;
			}		
		}).when(mock_mc.isLeapYear(Mockito.anyInt()));
		
		assertEquals(30, mock_mc.daysOfMonth(2000, 5));
		assertEquals(31, mock_mc.daysOfMonth(2000, 6));
		assertEquals(29, mock_mc.daysOfMonth(2000, 1));
		assertEquals(30, mock_mc.daysOfMonth(2008, 5));
		assertEquals(31, mock_mc.daysOfMonth(2008, 6));
		assertEquals(29, mock_mc.daysOfMonth(2008, 1));
		assertEquals(30, mock_mc.daysOfMonth(2018, 5));
		assertEquals(31, mock_mc.daysOfMonth(2018, 6));
		assertEquals(28, mock_mc.daysOfMonth(2018, 1));
	}

	@Test
	public void testWeekendsOfMonth() throws Exception {
		Mockito.doCallRealMethod().when(mock_mc).weekendsOfMonth(Mockito.anyInt(), Mockito.anyInt());
//		Mockito.doCallRealMethod().when(mock_mc).isLeapYear(Mockito.anyInt());
//		Mockito.doCallRealMethod().when(mock_mc).getWeek(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		Mockito.doAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				int year = invocation.getArgumentAt(0, Integer.class);
				int month = invocation.getArgumentAt(1, Integer.class);
				int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
				if(year == 2000 || year == 2008)
					if(month == 1)
						return 29;
				return months[month];
			}		
		}).when(mock_mc.daysOfMonth(Mockito.anyInt(), Mockito.anyInt()));
		assertEquals(8, mock_mc.weekendsOfMonth(2000, 5));
		assertEquals(10, mock_mc.weekendsOfMonth(2000, 6));
		assertEquals(8, mock_mc.weekendsOfMonth(2000, 1));
		assertEquals(9, mock_mc.weekendsOfMonth(2008, 5));
		assertEquals(8, mock_mc.weekendsOfMonth(2008, 6));
		assertEquals(8, mock_mc.weekendsOfMonth(2008, 1));
		assertEquals(9, mock_mc.weekendsOfMonth(2018, 5));
		assertEquals(9, mock_mc.weekendsOfMonth(2018, 6));
		assertEquals(8, mock_mc.weekendsOfMonth(2018, 1));
	}

	@Test
	public void testGetWeek() {
		Mockito.doCallRealMethod().when(mock_mc).getWeek(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
		assertEquals(3, mock_mc.getWeek(2000, 5, 14));
		assertEquals(4, mock_mc.getWeek(2000, 5, 29));
		assertEquals(5, mock_mc.getWeek(2000, 5, 30));
		assertEquals(5, mock_mc.getWeek(2000, 6, 14));
		assertEquals(6, mock_mc.getWeek(2000, 6, 29));
		assertEquals(0, mock_mc.getWeek(2000, 6, 30));
		assertEquals(1, mock_mc.getWeek(2000, 6, 31));
		assertEquals(1, mock_mc.getWeek(2000, 1, 14));
		assertEquals(2, mock_mc.getWeek(2000, 1, 29));
		assertEquals(6, mock_mc.getWeek(2008, 5, 14));
		assertEquals(0, mock_mc.getWeek(2008, 5, 29));
		assertEquals(1, mock_mc.getWeek(2008, 5, 30));
		assertEquals(1, mock_mc.getWeek(2008, 6, 14));
		assertEquals(2, mock_mc.getWeek(2008, 6, 29));
		assertEquals(3, mock_mc.getWeek(2008, 6, 30));
		assertEquals(4, mock_mc.getWeek(2008, 6, 31));
		assertEquals(4, mock_mc.getWeek(2008, 1, 14));
		assertEquals(5, mock_mc.getWeek(2008, 1, 29));
		assertEquals(4, mock_mc.getWeek(2018, 5, 14));
		assertEquals(5, mock_mc.getWeek(2018, 5, 29));
		assertEquals(6, mock_mc.getWeek(2018, 5, 30));
		assertEquals(6, mock_mc.getWeek(2018, 6, 14));
		assertEquals(0, mock_mc.getWeek(2018, 6, 29));
		assertEquals(1, mock_mc.getWeek(2018, 6, 30));
		assertEquals(2, mock_mc.getWeek(2018, 6, 31));
		assertEquals(3, mock_mc.getWeek(2018, 1, 14));
	}

}
