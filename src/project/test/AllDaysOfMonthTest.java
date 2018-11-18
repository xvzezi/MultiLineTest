package project.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.code.*;

public class AllDaysOfMonthTest {
	
	private MyCalendarM0 m0;
	private MyCalendarM1 m1;
	private MyCalendarM2 m2;
	private MyCalendarM3 m3;
	private MyCalendarM4 m4;

	@Before
	public void setUp() throws Exception {
		m0 = new MyCalendarM0();
		m1 = new MyCalendarM1();
		m2 = new MyCalendarM2();
		m3 = new MyCalendarM3();
		m4 = new MyCalendarM4();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDaysOfMonthM0() throws Exception {
		
		assertEquals(30, m0.daysOfMonth(2000, 5));
		assertEquals(31, m0.daysOfMonth(2000, 6));
		assertEquals(29, m0.daysOfMonth(2000, 1));
		assertEquals(30, m0.daysOfMonth(2008, 5));
		assertEquals(31, m0.daysOfMonth(2008, 6));
		assertEquals(29, m0.daysOfMonth(2008, 1));
		assertEquals(30, m0.daysOfMonth(2018, 5));
		assertEquals(31, m0.daysOfMonth(2018, 6));
		assertEquals(28, m0.daysOfMonth(2018, 1));
	}
	
	@Test
	public void testDaysOfMonthM1() throws Exception {
		
		assertEquals(30, m1.daysOfMonth(2000, 5));
		assertEquals(31, m1.daysOfMonth(2000, 6));
		assertEquals(29, m1.daysOfMonth(2000, 1));
		assertEquals(30, m1.daysOfMonth(2008, 5));
		assertEquals(31, m1.daysOfMonth(2008, 6));
		assertEquals(29, m1.daysOfMonth(2008, 1));
		assertEquals(30, m1.daysOfMonth(2018, 5));
		assertEquals(31, m1.daysOfMonth(2018, 6));
		assertEquals(28, m1.daysOfMonth(2018, 1));
	}
	
	@Test
	public void testDaysOfMonthM2() throws Exception {
		
		assertEquals(30, m2.daysOfMonth(2000, 5));
		assertEquals(31, m2.daysOfMonth(2000, 6));
		assertEquals(29, m2.daysOfMonth(2000, 1));
		assertEquals(30, m2.daysOfMonth(2008, 5));
		assertEquals(31, m2.daysOfMonth(2008, 6));
		assertEquals(29, m2.daysOfMonth(2008, 1));
		assertEquals(30, m2.daysOfMonth(2018, 5));
		assertEquals(31, m2.daysOfMonth(2018, 6));
		assertEquals(28, m2.daysOfMonth(2018, 1));
	}
	
	@Test
	public void testDaysOfMonthM3() throws Exception {
		
		assertEquals(30, m3.daysOfMonth(2000, 5));
		assertEquals(31, m3.daysOfMonth(2000, 6));
		assertEquals(29, m3.daysOfMonth(2000, 1));
		assertEquals(30, m3.daysOfMonth(2008, 5));
		assertEquals(31, m3.daysOfMonth(2008, 6));
		assertEquals(29, m3.daysOfMonth(2008, 1));
		assertEquals(30, m3.daysOfMonth(2018, 5));
		assertEquals(31, m3.daysOfMonth(2018, 6));
		assertEquals(28, m3.daysOfMonth(2018, 1));
	}
	
	@Test
	public void testDaysOfMonthM4() throws Exception {
		
		assertEquals(30, m4.daysOfMonth(2000, 5));
		assertEquals(31, m4.daysOfMonth(2000, 6));
		assertEquals(29, m4.daysOfMonth(2000, 1));
		assertEquals(30, m4.daysOfMonth(2008, 5));
		assertEquals(31, m4.daysOfMonth(2008, 6));
		assertEquals(29, m4.daysOfMonth(2008, 1));
		assertEquals(30, m4.daysOfMonth(2018, 5));
		assertEquals(31, m4.daysOfMonth(2018, 6));
		assertEquals(28, m4.daysOfMonth(2018, 1));
	}

}
