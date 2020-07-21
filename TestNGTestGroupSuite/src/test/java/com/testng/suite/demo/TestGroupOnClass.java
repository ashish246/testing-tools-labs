package com.testng.suite.demo;

import org.testng.annotations.Test;

@Test(groups = "selenium-test")
public class TestGroupOnClass {

	public void runSelenium() {
		System.out.println("runSelenium()");
	}

	public void runSelenium1() {
		System.out.println("runSelenium()1");
	}

}