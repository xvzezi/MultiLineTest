package project.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.code.*;

public class AllWeekendsOfMonthTest {
	
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
	public void testWeekendsOfMonthM0() throws Exception {
		assertEquals(8, m0.weekendsOfMonth(2000, 5));
		assertEquals(10, m0.weekendsOfMonth(2000, 6));
		assertEquals(8, m0.weekendsOfMonth(2000, 1));
		assertEquals(9, m0.weekendsOfMonth(2008, 5));
		assertEquals(8, m0.weekendsOfMonth(2008, 6));
		assertEquals(8, m0.weekendsOfMonth(2008, 1));
		assertEquals(9, m0.weekendsOfMonth(2018, 5));
		assertEquals(9, m0.weekendsOfMonth(2018, 6));
		assertEquals(8, m0.weekendsOfMonth(2018, 1));
	}
	
	@Test
	public void testWeekendsOfMonthM1() throws Exception {
		assertEquals(8, m1.weekendsOfMonth(2000, 5));
		assertEquals(10, m1.weekendsOfMonth(2000, 6));
		assertEquals(8, m1.weekendsOfMonth(2000, 1));
		assertEquals(9, m1.weekendsOfMonth(2008, 5));
		assertEquals(8, m1.weekendsOfMonth(2008, 6));
		assertEquals(8, m1.weekendsOfMonth(2008, 1));
		assertEquals(9, m1.weekendsOfMonth(2018, 5));
		assertEquals(9, m1.weekendsOfMonth(2018, 6));
		assertEquals(8, m1.weekendsOfMonth(2018, 1));
	}
	
	@Test
	public void testWeekendsOfMonthM2() throws Exception {
		assertEquals(8, m2.weekendsOfMonth(2000, 5));
		assertEquals(10, m2.weekendsOfMonth(2000, 6));
		assertEquals(8, m2.weekendsOfMonth(2000, 1));
		assertEquals(9, m2.weekendsOfMonth(2008, 5));
		assertEquals(8, m2.weekendsOfMonth(2008, 6));
		assertEquals(8, m2.weekendsOfMonth(2008, 1));
		assertEquals(9, m2.weekendsOfMonth(2018, 5));
		assertEquals(9, m2.weekendsOfMonth(2018, 6));
		assertEquals(8, m2.weekendsOfMonth(2018, 1));
	}
	
	@Test
	public void testWeekendsOfMonthM3() throws Exception {
		assertEquals(8, m3.weekendsOfMonth(2000, 5));
		assertEquals(10, m3.weekendsOfMonth(2000, 6));
		assertEquals(8, m3.weekendsOfMonth(2000, 1));
		assertEquals(9, m3.weekendsOfMonth(2008, 5));
		assertEquals(8, m3.weekendsOfMonth(2008, 6));
		assertEquals(8, m3.weekendsOfMonth(2008, 1));
		assertEquals(9, m3.weekendsOfMonth(2018, 5));
		assertEquals(9, m3.weekendsOfMonth(2018, 6));
		assertEquals(8, m3.weekendsOfMonth(2018, 1));
	}
	
	@Test
	public void testWeekendsOfMonthM4() throws Exception {
		assertEquals(8, m4.weekendsOfMonth(2000, 5));
		assertEquals(10, m4.weekendsOfMonth(2000, 6));
		assertEquals(8, m4.weekendsOfMonth(2000, 1));
		assertEquals(9, m4.weekendsOfMonth(2008, 5));
		assertEquals(8, m4.weekendsOfMonth(2008, 6));
		assertEquals(8, m4.weekendsOfMonth(2008, 1));
		assertEquals(9, m4.weekendsOfMonth(2018, 5));
		assertEquals(9, m4.weekendsOfMonth(2018, 6));
		assertEquals(8, m4.weekendsOfMonth(2018, 1));
	}


}
