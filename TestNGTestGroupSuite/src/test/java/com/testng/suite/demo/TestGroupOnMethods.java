package com.testng.suite.demo;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

/**
 * Groups on Methods Review a test group example.
 * 
 * runSelenium() and runSelenium1() are belong to group selenium-test.
 * testConnectOracle() and testConnectMsSQL() are belong to group database.
 * runFinal() will be executed if groups selenium-test and database are passed.
 * 
 * @author Administrator
 *
 */
public class TestGroupOnMethods {

	@BeforeGroups("database")
	public void setupDB() {
		System.out.println("setupDB()");
	}

	@AfterGroups("database")
	public void cleanDB() {
		System.out.println("cleanDB()");
	}

	@Test(groups = "selenium-test")
	public void runSelenium() {
		System.out.println("runSelenium()");
	}

	@Test(groups = "selenium-test")
	public void runSelenium1() {
		System.out.println("runSelenium()1");
	}

	@Test(groups = "database")
	public void testConnectOracle() {
		System.out.println("testConnectOracle()");
	}

	/**
	 * A test method can belong to multiple groups.
	 */
	@Test(groups = { "mysql", "database" })
	public void testConnectMsSQL() {
		System.out.println("testConnectMsSQL");
	}

	@Test(dependsOnGroups = { "database", "selenium-test" })
	public void runFinal() {
		System.out.println("runFinal");
	}

}