package com.junit.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Often it is desired that one should be able to run only certain testcases
 * whenever a new feature is introduced. This can be achieved using JUnitCore.
 * Alternate way is to make a suite of testcases with Junit provided annotations
 * : @RunWith and @SuiteClasses
 * 
 * @author Administrator
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ TestFeatureOne.class, TestFeatureTwo.class })
public class TestFeatureSuite {

}