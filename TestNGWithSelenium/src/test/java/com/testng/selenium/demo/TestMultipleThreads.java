package com.testng.selenium.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Administrator
 *
 */
public class TestMultipleThreads {

	@Test(invocationCount = 5)
	public void loadTestThisWebsite() {

		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");
		System.out.println("Page Title is " + driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();

	}

	@Test(enabled = true, invocationCount = 100, threadPoolSize = 5)
	public void loadTest() {

		System.out.printf("%n[START] Thread Id : %s is started!", Thread
				.currentThread().getId());

		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.google.com");

		// perform whatever actions, like login, submit form or navigation

		System.out.printf("%n[END] Thread Id : %s", Thread.currentThread()
				.getId());

		driver.quit();

	}
}