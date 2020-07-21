package com.junit.demo;

import org.junit.Test;

public class TestTimeout {
	
	@Test
	public void testcaseFirst() {
		System.out.println("First testcase executed");
	}
	
	@Test(timeout = 5000)
	public void testInfiniteTametakingLoop() throws InterruptedException {
		while (true)
		{
			Thread.sleep(1000);
		}
	}

}
