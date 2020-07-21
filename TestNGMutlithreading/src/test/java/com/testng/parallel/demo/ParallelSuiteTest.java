package com.testng.parallel.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Let’s learn about executing each test inside a suite in parallel, that is,
 * each test that is part of the test suite execution will be executed in its
 * own separate respective thread.
 * 
 * @author Administrator
 *
 */
public class ParallelSuiteTest {
	String testName = "";

	@BeforeTest
	@Parameters({ "test-name" })
	public void beforeTest(String testName) {
		this.testName = testName;
		long id = Thread.currentThread().getId();
		System.out.println("Before test " + testName + ". Thread id is: " + id);
	}

	@BeforeClass
	public void beforeClass() {
		long id = Thread.currentThread().getId();
		System.out.println("Before test-class " + testName + ". Thread id is: "
				+ id);
	}

	@Test
	public void testMethodOne() {
		long id = Thread.currentThread().getId();
		System.out.println("Sample test-method " + testName
				+ ". Thread id is: " + id);
	}

	@AfterClass
	public void afterClass() {
		long id = Thread.currentThread().getId();
		System.out.println("After test-method  " + testName
				+ ". Thread id is: " + id);
	}

	@AfterTest
	public void afterTest() {
		long id = Thread.currentThread().getId();
		System.out.println("After test  " + testName + ". Thread id is: " + id);
	}
}