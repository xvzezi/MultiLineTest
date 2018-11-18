package project.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import project.code.*;

public class AllGetWeekTest {
	
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
	public void testGetWeekM0() throws Exception {
		assertEquals(3, m0.getWeek(2000, 5, 14));
		assertEquals(6, m0.getWeek(2000, 6, 29));
		assertEquals(1, m0.getWeek(2008, 6, 14));
		assertEquals(3, m0.getWeek(2008, 6, 30));
		assertEquals(5, m0.getWeek(2008, 1, 29));
		assertEquals(5, m0.getWeek(2018, 5, 29));
		assertEquals(6, m0.getWeek(2018, 5, 30));
		assertEquals(2, m0.getWeek(2018, 6, 31));
		assertEquals(3, m0.getWeek(2018, 1, 14));
	}

	@Test
	public void testGetWeekM1() throws Exception {
		assertEquals(3, m1.getWeek(2000, 5, 14));
		assertEquals(6, m1.getWeek(2000, 6, 29));
		assertEquals(1, m1.getWeek(2008, 6, 14));
		assertEquals(3, m1.getWeek(2008, 6, 30));
		assertEquals(5, m1.getWeek(2008, 1, 29));
		assertEquals(5, m1.getWeek(2018, 5, 29));
		assertEquals(6, m1.getWeek(2018, 5, 30));
		assertEquals(2, m1.getWeek(2018, 6, 31));
		assertEquals(3, m1.getWeek(2018, 1, 14));
	}
	
	@Test
	public void testGetWeekM2() throws Exception {
		assertEquals(3, m2.getWeek(2000, 5, 14));
		assertEquals(6, m2.getWeek(2000, 6, 29));
		assertEquals(1, m2.getWeek(2008, 6, 14));
		assertEquals(3, m2.getWeek(2008, 6, 30));
		assertEquals(5, m2.getWeek(2008, 1, 29));
		assertEquals(5, m2.getWeek(2018, 5, 29));
		assertEquals(6, m2.getWeek(2018, 5, 30));
		assertEquals(2, m2.getWeek(2018, 6, 31));
		assertEquals(3, m2.getWeek(2018, 1, 14));
	}
	
	@Test
	public void testGetWeekM3() throws Exception {
		assertEquals(3, m3.getWeek(2000, 5, 14));
		assertEquals(6, m3.getWeek(2000, 6, 29));
		assertEquals(1, m3.getWeek(2008, 6, 14));
		assertEquals(3, m3.getWeek(2008, 6, 30));
		assertEquals(5, m3.getWeek(2008, 1, 29));
		assertEquals(5, m3.getWeek(2018, 5, 29));
		assertEquals(6, m3.getWeek(2018, 5, 30));
		assertEquals(2, m3.getWeek(2018, 6, 31));
		assertEquals(3, m3.getWeek(2018, 1, 14));
	}
	
	@Test
	public void testGetWeekM4() throws Exception {
		assertEquals(3, m4.getWeek(2000, 5, 14));
		assertEquals(6, m4.getWeek(2000, 6, 29));
		assertEquals(1, m4.getWeek(2008, 6, 14));
		assertEquals(3, m4.getWeek(2008, 6, 30));
		assertEquals(5, m4.getWeek(2008, 1, 29));
		assertEquals(5, m4.getWeek(2018, 5, 29));
		assertEquals(6, m4.getWeek(2018, 5, 30));
		assertEquals(2, m4.getWeek(2018, 6, 31));
		assertEquals(3, m4.getWeek(2018, 1, 14));
	}

}
