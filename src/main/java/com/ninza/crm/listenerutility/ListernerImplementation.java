package com.ninza.crm.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ninza.crm.baseclass.BaseClass;

public class ListernerImplementation implements ITestListener,ISuiteListener{
	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		Date date = new Date();
		String d = date.toString().replace(" ","_").replace(":","_");
	
		
		ExtentSparkReporter spark=new ExtentSparkReporter("./ExtentReports/Report_"+date+".html");
		spark.config().setDocumentTitle("CRM Report");
		spark.config().setReportName("Ninza CRM Report");
		spark.config().setTheme(Theme.DARK);
		
//		ExtentReports report = new ExtentReports();//declare global level
		report = new ExtentReports();
		report.attachReporter(spark);
//		report.setSystemInfo("Browser", d, "Chrome";)
//		report.setSystemInfo("OS","Mac");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
//		 System.out.println(methodName+"Execution started");
		 test=report.createTest(methodName);
		 test.log(Status.INFO, methodName+" Execution started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName+"Execution success");
		test.log(Status.PASS,methodName+" Execution success");
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		String methodName = result.getMethod().getMethodName();
//		System.out.println(methodName+"Execution failed");
		test.log(Status.FAIL, methodName+" Execution failed");
		
		Date date = new Date();
		String d = date.toString().replace(" ","_").replace(":","_");
		TakesScreenshot ts=(TakesScreenshot)BaseClass.sdriver;
		String src=ts.getScreenshotAs(OutputType.BASE64);//extent supports only base 64 type -its string format all screenshots 
		test.addScreenCaptureFromBase64String(src, methodName+"_"+date);

//		File src=ts.getScreenshotAs(OutputType.FILE);
//		File dest=new File("./Screenshots/"+methodName+date+d+".png");//if one more time we will take screenshot  for same name file then it will be overridden
//		try {
//			FileHandler.copy(src, dest);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
//		System.out.println(methodName+"Execution skipped");
		test.log(Status.SKIP, methodName+" Execution skipped");
		
	}
	 
	
}
