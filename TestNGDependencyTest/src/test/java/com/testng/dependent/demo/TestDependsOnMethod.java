package com.testng.dependent.demo;

import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 * 
 * In TestNG, we use dependOnMethods and dependsOnGroups to implement dependency
 * testing. If a dependent method is fail, all the subsequent test methods will
 * be skipped, NOT failed.
 * 
 */
public class TestDependsOnMethod {

	@Test
	public void method1() {
		System.out.println("This is method 1");
		throw new RuntimeException();
	}

	@Test(dependsOnMethods = { "method1" })
	public void method2() {
		System.out.println("This is method 2");
	}

}
