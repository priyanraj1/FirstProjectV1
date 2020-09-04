package DemoSelenium;


import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

//@Listeners(TestNGListeners.class)
public class LoginTest {

	Resusable resusable=new Resusable();
	WebDriver driver;
	Boolean check=false;
	
	ExtentHtmlReporter reporter1;
	ExtentReports extent;
	ExtentTest logger;
	
	@DataProvider(name="AcmeTest")
	public Object[][] data()
	{
		Object[][] credentials=new Object[3][2];
		
		credentials[0][0]="user1";
		credentials[0][1]="pass1";
		

		credentials[1][0]=Utilities.user;
		credentials[1][1]=Utilities.pass;

		credentials[2][0]="user2";
		credentials[2][1]="pass2";
		
		return credentials;
	}
	
	@BeforeTest
	public void start() 
	{
		
	System.setProperty("webdriver.chrome.driver",Utilities.chromePath);
	driver=new ChromeDriver();
	driver.get(Utilities.baseURL);
	driver.manage().window().maximize();
	
	String ExtentReportPath=System.getProperty("user.dir")+"./Reports/AcmeTestResults"+System.currentTimeMillis()+".html";
	reporter1= new ExtentHtmlReporter(ExtentReportPath);
	extent= new ExtentReports();
	extent.attachReporter(reporter1);
	
	 }
	
	
	@BeforeMethod
	public void ExtentReports(Method method) throws Exception 
	{
		    String testName = method.getName(); 
		    System.out.println("Executing test: " + testName);
		
		    logger = extent.createTest(testName);
		
		    
	}
	
	@Test(enabled=false,priority=0, dataProvider="AcmeTest")
	public void LogInCheck(String userName, String passWord) throws Exception 
	{
	System.out.println(userName+" "+passWord);
   WebElement name=driver.findElement(By.name("email"));
   name.clear();name.sendKeys(userName);
   
   WebElement pass=driver.findElement(By.name("password"));
   pass.clear();pass.sendKeys(passWord);
	
   WebElement submitBtn=driver.findElement(By.id("buttonLogin"));
   submitBtn.click();
   Thread.sleep(3000);
   
   try
   { 
		Alert alt = driver.switchTo().alert();
		String actualBoxMsg = alt.getText(); // get content of the Alter Message
		alt.accept();
		System.out.println(actualBoxMsg);
		 // Compare Error Text with Expected Error Value					
		Assert.assertEquals(actualBoxMsg,Utilities.InCorrectUser);	
	}    
   catch (NoAlertPresentException Ex){ 

	check=true; 
	   
   String actual=driver.getTitle();
   System.out.println(actual);
	
   Assert.assertEquals(Utilities.expectedTitle, actual);
   
	System.out.println(Ex.getMessage().toString());
	
	if(check)
	{
		Thread.sleep(2000);	
		WebElement LogOutBtn=driver.findElement(By.cssSelector("a[href=\'/account/logout/\']"));
		LogOutBtn.click();		
	} 
   }
   }
	
	
	//@Test(enabled=true,priority=2,dependsOnMethods= {"LogInCheck"})
	@Test(enabled=true,priority=1)
	public void CorrectCredentails() throws InterruptedException {
		
		driver.findElement(ObjectRepository.name).clear();
		driver.findElement(ObjectRepository.name).sendKeys(Utilities.user);
		   
		driver.findElement(ObjectRepository.pass).clear();
		driver.findElement(ObjectRepository.pass).sendKeys(Utilities.pass);
		
		driver.findElement(ObjectRepository.submitBtn).click();
	
	    Thread.sleep(3000);
	}
	
	//@Test(enabled=true,priority=2,dependsOnMethods= {"LogInCheck"})
		@Test(enabled=true,priority=2, dependsOnMethods= {"CorrectCredentails"},retryAnalyzer=DemoSelenium.RetryAnalyzer.class)
		public void HomePageTakeScreenShot() throws Exception {
					
			String welcomeUser=driver.findElement(ObjectRepository.WelcomeUserName).getText();
			System.out.println(welcomeUser);

			Assert.assertEquals(Utilities.expecteduser1, welcomeUser);
			logger.log(Status.INFO,"Login to Acme Website");
			
	        resusable.ScreenshotDuringMethod(driver);    
	        System.out.println("Screenshot taken");   			
		}
	
	//@Test(enabled=true,priority=3,dependsOnMethods= {"HomePageTakeScreenShot"})
	@Test(enabled=true,priority=3,description="Adding Invoice")
	public void AddInvoice() throws Exception {
	
		System.out.println("Invoice Button Try");
		Thread.sleep(3000);
		
		driver.findElement(ObjectRepository.InvoiceBtn).click();;
		driver.findElement(ObjectRepository.AddInvoiceBtn).click();;
				
		System.out.println("Enter Invoice");
		 
		driver.findElement(ObjectRepository.InvoiceNumber).sendKeys("434564");
			
		driver.findElement(ObjectRepository.VendorTax).sendKeys("GDY6438");;
			
		Thread.sleep(2000);
		driver.findElement(ObjectRepository.BackToHomeBtn).click(); 
	}
            

	@AfterMethod
	public void teardown(ITestResult result) throws IOException
	{
		 
	// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus())
		{				
		/*	 //Convert web driver object to TakeScreenshot
	        TakesScreenshot scrShot =(TakesScreenshot)driver;

	        //Call getScreenshotAs method to create image file
	         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	        //Move image file to new destination
	        File DestFile=new File("./Screenshots/"+result.getName()+System.currentTimeMillis()+".png");

	        //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);  */
			String path1=Resusable.ReportsScreenshotAfterMethod(driver, result);
			System.out.println(path1);
			logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
            System.out.println("DEFECT Screenshot taken");   
            logger.log(Status.FAIL, "Test Case Failed :"+result.getName());
		} 
		else if(ITestResult.SUCCESS==result.getStatus())
		{
			//Resusable.ReportsScreenshotAfterMethod(driver, result);
			String path1=Resusable.ReportsScreenshotAfterMethod(driver, result);
			System.out.println(path1);
			System.out.println("NOTE THIS "+result.getStatus());
			logger.pass("Success",MediaEntityBuilder.createScreenCaptureFromPath(path1).build());
			System.out.println("SUCCESS Screenshot taken");
			logger.log(Status.PASS, "Test Case Passed :"+result.getName());
		}
		
		extent.flush();
		}	

	
	@AfterTest
	public void close() throws Exception {
		
	Thread.sleep(2000);	
	
	//resusable.sendEMail();
	driver.findElement(ObjectRepository.LogOutBtn).click();	

	driver.quit();
	
}}