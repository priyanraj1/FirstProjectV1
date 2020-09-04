package com.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readsconfig=new ReadConfig();
	
	//public String baseURL="http://demo.guru99.com/v4/";
	public String baseURL=readsconfig.getApplicationURL();
	public String user=readsconfig.getUsername();
	public String pass=readsconfig.getPassword();	
	
	public static WebDriver driver;
	public static Logger logger;

	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger=Logger.getLogger("eBankings");
		PropertyConfigurator.configure("log4j.properties");
	
		if (br.equals("chrome"))		
			{
			System.setProperty("webdriver.chrome.driver", readsconfig.getChromePath());	
			driver=new ChromeDriver();
			}
		
		driver.get(baseURL);
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public static String randomString()
	{
		String randomName=RandomStringUtils.randomAlphanumeric(8);
		return randomName;
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	
	
}
