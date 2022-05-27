package test.SampleProj;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class TestListener implements ITestListener {
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReportManager.startTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		ExtentReportManager.getTest().log(LogStatus.PASS, "Test Case Passed", result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReportManager.getTest().log(LogStatus.FAIL, "Test Case Failed", result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentReportManager.getTest().log(LogStatus.SKIP, "Test Case Skipped", result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		ExtentReportManager.endTest();
	}

}
