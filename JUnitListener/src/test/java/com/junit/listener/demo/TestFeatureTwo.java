package com.junit.listener.demo;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class TestFeatureTwo {

	@Test
	public void testSecondFeature() {
		Assert.assertTrue(true);
	}

	@Test
	@Ignore
	public void testSecondFeatureIngored() {
		Assert.assertTrue(true);
	}
}