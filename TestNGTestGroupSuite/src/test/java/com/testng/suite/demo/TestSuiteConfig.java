package com.testng.suite.demo;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//show the use of @BeforeSuite and @BeforeTest
public class TestSuiteConfig {

	@BeforeSuite
	public void testBeforeSuite() {
		System.out.println("testBeforeSuite()");
	}

	@AfterSuite
	public void testAfterSuite() {
		System.out.println("testAfterSuite()");
	}

	@BeforeTest
	public void testBeforeTest() {
		System.out.println("testBeforeTest()");
	}

	@AfterTest
	public void testAfterTest() {
		System.out.println("testAfterTest()");
	}

}
