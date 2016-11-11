package com.redrunner.beans;

import junit.framework.TestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

public class TestMe extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public TestMe(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TestMe.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		String test = "T";
		assertTrue("T".equals(test));
	}
}
