package com.testng.parallel.demo;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * TestNG provides multiple ways to execute the tests in a multi-threaded
 * condition, one of them is executing each test method in a single thread. This
 * mode reduces the execution time significantly because more tests are executed
 * in parallel, hence reducing the total execution time.
 * 
 * @author Administrator
 *
 */
public class ParallelMethodTest {
	@BeforeMethod
	public void beforeMethod() {
		long id = Thread.currentThread().getId();
		System.out.println("Before test-method. Thread id is: " + id);
	}

	@Test
	public void testMethodsOne() {
		long id = Thread.currentThread().getId();
		System.out.println("Simple test-method One. Thread id is: " + id);
	}

	@Test
	public void testMethodsTwo() {
		long id = Thread.currentThread().getId();
		System.out.println("Simple test-method Two. Thread id is: " + id);
	}

	@AfterMethod
	public void afterMethod() {
		long id = Thread.currentThread().getId();
		System.out.println("After test-method. Thread id is: " + id);
	}
}