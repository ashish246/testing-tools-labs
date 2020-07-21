package com.testng.demo;

import org.testng.annotations.Test;

public class TestRuntimeException {

	@Test(expectedExceptions = ArithmeticException.class)
	public void divisionWithException() {
		int i = 1 / 0;
	}

}
