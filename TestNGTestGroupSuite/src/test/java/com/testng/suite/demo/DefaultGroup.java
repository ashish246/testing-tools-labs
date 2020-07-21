package com.testng.suite.demo;

import org.testng.annotations.Test;

/**
 * Sometimes we may need to assign a default group to a set of test methods that
 * belong to a class. This way all the public methods that belong to the said
 * class will automatically become TestNG test methods and become part of the
 * said group.
 * 
 * This can be achieved by using the @Test annotation at class level and
 * defining the default group in the said @Test annotation.
 * 
 * @author Administrator
 *
 */
@Test(groups = { "default-group" })
public class DefaultGroup {

	public void testMethodOne() {
		System.out.println("Test method one.");
	}

	public void testMethodTwo() {
		System.out.println("Test method two.");
	}

	@Test(groups = { "test-group" })
	public void testMethodThree() {
		System.out.println("Test method three.");
	}
}