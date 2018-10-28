package project.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ComFinManagerTest.class,
	MyCalendarTest.class
	})
public class AllTests {

}
