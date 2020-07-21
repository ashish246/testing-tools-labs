package com.junit.demo;

import org.junit.Ignore;
import org.junit.Test;

public class TestIgnore {

	@Ignore("Not Ready to Run")
	@Test
	public void divisionWithException() {
		System.out.println("Method is not ready yet");
	}

}