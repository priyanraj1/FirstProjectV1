package com.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void loginTest() throws InterruptedException, IOException 
	{
		
		
		logger.info("URL Opened");
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("Entered Username");
		
		lp.setPassword(pass);
		logger.info("Entered password");
		
		lp.clickSubmit();
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
		}
	
	
	}

}
