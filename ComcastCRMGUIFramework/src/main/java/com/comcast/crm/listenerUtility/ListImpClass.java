package com.comcast.crm.listenerUtility;
/**
 * @author Nandhakumar J
 */


import java.util.Random;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ListImpClass implements ITestListener,ISuiteListener{
	ExtentReports report;
	ExtentTest test;
	
	@Override
		public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
			test =report.createTest(result.getName());
		}
	@Override
	public void onTestSuccess(ITestResult result) {
	// TODO Auto-generated method stub
		test.log(Status.PASS,result.getMethod().getMethodName());
		test.log(Status.PASS,result.getThrowable());
	}
	@Override
	public void onTestFailure(ITestResult result) {
	// TODO Auto-generated method stub
		test.log(Status.PASS,result.getMethod().getMethodName());
		test.log(Status.PASS,result.getThrowable());
		System.out.println("<---------I am Listening--------->");
		
		String screenshot = null;
		try {
			screenshot = WebDriverUtility.takeScreenshot(BaseClass.sdriver,result.getMethod().getMethodName());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshot);
	}
	@Override
	public void onTestSkipped(ITestResult result) {
	// TODO Auto-generated method stub
		test.log(Status.SKIP,result.getMethod().getMethodName());
		test.log(Status.SKIP,result.getThrowable());
	}
	@Override
	public void onStart(ITestContext Context) {
	// TODO Auto-generated method stub
		Random random = new Random();
		int randNum = random.nextInt(1000);
		
		ExtentSparkReporter spark = new ExtentSparkReporter("ExtentReport/Report.html"+randNum);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("ExtentReport");
		spark.config().setReportName("Nandha");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("platform","windows10");
		report.setSystemInfo("Executed by","Nandha");
		report.setSystemInfo("reviewed by","Sanjay");
	}
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
