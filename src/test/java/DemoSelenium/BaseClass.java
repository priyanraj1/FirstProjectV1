package DemoSelenium;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {

	
	public void ExtentReports()
	{
	ExtentHtmlReporter reporter1 = new ExtentHtmlReporter("./Reports/AcmeTestResults.html");
	
	ExtentReports extent = new ExtentReports();
	extent.attachReporter(reporter1);
	
	ExtentTest logger = extent.createTest("MyFirstTest");
	
	logger.log(Status.INFO, "Login to Amazon");
	logger.log(Status.PASS, "Title Verified");
	
	extent.flush();
}
}