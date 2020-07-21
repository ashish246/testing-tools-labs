package com.junit.demo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * In JUnit 4, you have to declare “@BeforeClass” and “@AfterClass” method as
 * static method.
 * 
 * @author Administrator
 *
 */
public class TestJUnitBasicMethods {

	private Collection collection;

	@BeforeClass
	public static void oneTimeSetUp() {
		// one-time initialization code
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public static void oneTimeTearDown() {
		// one-time cleanup code
		System.out.println("@AfterClass - oneTimeTearDown");
	}

	@Before
	public void setUp() {
		collection = new ArrayList();
		System.out.println("@Before - setUp");
	}

	@After
	public void tearDown() {
		collection.clear();
		System.out.println("@After - tearDown");
	}

	@Test
	public void testEmptyCollection() {
		Assert.assertTrue(collection.isEmpty());
		System.out.println("@Test - testEmptyCollection");
	}

	@Test
	public void testOneItemCollection() {
		collection.add("itemA");
		Assert.assertEquals(1, collection.size());
		System.out.println("@Test - testOneItemCollection");
	}
}