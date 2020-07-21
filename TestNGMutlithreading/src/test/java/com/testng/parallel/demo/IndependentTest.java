package com.testng.parallel.demo;

import org.testng.annotations.Test;

/**
 * TestNG also provides the flexibility to configure a test method to be run in
 * a multi-threaded environment. This is achieved by configuring it while using
 * the @Test annotation on a method.
 * 
 * The value of the threadPoolSize is set to 3; this configures the test method
 * to be run in three different threads. The other two attributes,
 * invocationCount and timeOut, configures the test to be invoked a multiple
 * number of times and fail if the execution takes more time.
 * 
 * @author Administrator
 *
 */
public class IndependentTest {

	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
	public void testMethod() {
		Long id = Thread.currentThread().getId();
		System.out.println("Test method executing on thread with id: " + id);
	}
}
