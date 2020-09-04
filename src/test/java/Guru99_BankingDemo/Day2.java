package Guru99_BankingDemo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Day2 {

		WebDriver driver;
		String usering="mngr263770";
		@BeforeTest
		public void start() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Priyan\\Documents\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get(" http://www.demo.guru99.com/V4/index.php");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		 }
		
		
		@Test(priority=0)
		public void LogIn() {
	    
		WebElement UserName=driver.findElement(By.cssSelector("input[name='uid']"));
		UserName.sendKeys("mngr263770");
		//String usering="mngr263770";
			
		WebElement Pass=driver.findElement(By.cssSelector("input[name='password']"));
		Pass.sendKeys("mEpYqAm");
			
		WebElement SubmitBtn=driver.findElement(By.cssSelector("input[name='btnLogin']"));
		SubmitBtn.click();
			
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Hang On");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//String welcomeMsg=driver.findElement(By.cssSelector(".heading3")).getText();
		//Not working with CLass Name, so using XPath
		String welcomeMsg=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[2]/td/marquee")).getText();
		System.out.println(welcomeMsg);
		System.out.println("Must see above");
		
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", welcomeMsg);
		 
		}
		 
		@Test(priority=1)
		public void CheckUser() {
		
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Hang On");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String welcomeUser=driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[3]/td")).getText();
			System.out.println(welcomeUser);
			System.out.println("Must see above");
			Assert.assertEquals("Manger Id 6567: "+usering, welcomeUser);
						
		}
		//this.takeSnapShot(driver, "c://test.png") ;   

		@Test(priority=2)
		public void TakeScreenShot() throws IOException {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 //Convert web driver object to TakeScreenshot
	        TakesScreenshot scrShot =(TakesScreenshot)driver;

	        //Call getScreenshotAs method to create image file
	         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	        //Move image file to new destination
	         //C:\Users\Priyan\Downloads\commons-io-2.7
            File DestFile=new File("./Screenshots/"+"screenshot.png");

	         //Copy file at destination
             FileUtils.copyFile(SrcFile, DestFile);
	              
             System.out.println("Screenshot taken");
             			
		}
       
                
		@AfterMethod
			public void teardown(ITestResult result) throws IOException
			{
			 
			// Here will compare if test is failing then only it will enter into if condition
			if(ITestResult.FAILURE==result.getStatus())
			{
			
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				 //Convert web driver object to TakeScreenshot
		        TakesScreenshot scrShot =(TakesScreenshot)driver;

		        //Call getScreenshotAs method to create image file
		         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

		        //Move image file to new destination
		         //C:\Users\Priyan\Downloads\commons-io-2.7
	            File DestFile=new File("./Screenshots/"+result.getName()+".png");

		        //Copy file at destination
	            FileUtils.copyFile(SrcFile, DestFile);
		              
	            System.out.println("DEFECT Screenshot taken");
	            
			} 
			}
		
		@AfterTest
		public void close() {
		WebElement LogOutBtn=driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a	"));
		LogOutBtn.click();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.switchTo().alert().accept();
			
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.quit();
		
	

	}
}