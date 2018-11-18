package project.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.code.*;

public class AllIsLeapYearTest {
	
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
	public void testIsLeapYearM0() {
		assertEquals(true, m0.isLeapYear(2000));
		assertEquals(true, m0.isLeapYear(2008));
		assertEquals(false, m0.isLeapYear(2018));
	}
	
	@Test
	public void testIsLeapYearM1() {
		assertEquals(true, m1.isLeapYear(2000));
		assertEquals(true, m1.isLeapYear(2008));
		assertEquals(false, m1.isLeapYear(2018));
	}
	
	@Test
	public void testIsLeapYearM2() {
		assertEquals(true, m2.isLeapYear(2000));
		assertEquals(true, m2.isLeapYear(2008));
		assertEquals(false, m2.isLeapYear(2018));
	}

	@Test
	public void testIsLeapYearM3() {
		assertEquals(true, m3.isLeapYear(2000));
		assertEquals(true, m3.isLeapYear(2008));
		assertEquals(false, m3.isLeapYear(2018));
	}
	
	@Test
	public void testIsLeapYearM4() {
		assertEquals(true, m4.isLeapYear(2000));
		assertEquals(true, m4.isLeapYear(2008));
		assertEquals(false, m4.isLeapYear(2018));
	}

}
