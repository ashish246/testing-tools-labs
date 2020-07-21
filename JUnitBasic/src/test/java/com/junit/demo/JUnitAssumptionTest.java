package com.junit.demo;

import org.junit.Assume;
import org.junit.Test;

/**
 * If you want to ignore all testcases in a single java class then it can be
 * used in @Before annotated method. All testcases will be ignored in such way.
 * 
 * @author Administrator
 *
 */
public class JUnitAssumptionTest {

	/**
	 * Below testcase will execute only when the application version is greater
	 * than 7. Its actually a great feature which enables us to write feature
	 * specific test cases without much worry.
	 * 
	 * When above testcase is executed, if application version is greater than 7
	 * or less than 7, testcase is ignored just it is using @Ignore annotation.
	 * Some IDEs might show like they executed the testcase but they actually
	 * didn’t and just ignored. You can verify by watching logs.
	 */
	@Test
	public void testIfVersioonGreaterThan7() {
		String versionNumber = "7"; // Get it from configuration on runtime
		Assume.assumeTrue(Integer.valueOf(versionNumber) == 7);
		System.out.println("Test executed");
	}

}