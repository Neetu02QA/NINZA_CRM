package com.ninza.crm.campaignstest;

import org.testng.Assert;
import org.testng.annotations.Test;


public class Demo {
	@Test(retryAnalyzer = com.ninza.crm.listenerutility.IRetryAnalyzerImplementation.class)
	public void add() {
		System.out.println("Hi");
		Assert.assertEquals("hdfc", "hfdc");
	}
}

///Users/rajnishkhatri/Downloads/apache-maven-3.9.11/bin
///Users/rajnishkhatri/Downloads/jdk-21.0.8.jdk/Contents/Home
	