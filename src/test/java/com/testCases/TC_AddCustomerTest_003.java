package com.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pageObjects.AddCustomerPage;
import com.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	
	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		
		lp.setUserName(user);
		lp.setPassword(pass);
		lp.clickSubmit();
		
		AddCustomerPage addCust=new AddCustomerPage(driver);
		
		addCust.clickNewCUstomer();
		Thread.sleep(1500);
		
		addCust.setCustName("Priyan");
		addCust.setGender("Male");
		addCust.setDOBB("10", "19", "1989");
		addCust.setAddress("55 Gerrard Street");
		addCust.setCity("Toronto");
		addCust.setState("Ontario");
		addCust.setPin("4532322");
		addCust.setMobile("988433274732");
		addCust.setPass("Arsenal");
		addCust.setMail(randomString()+"@gmail.com");
		
		addCust.clickSubmit();
		Thread.sleep(7500);
		
		boolean result=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(result==true)
		{
			Assert.assertTrue(true);
		}
		else
		{
			captureScreen(driver, "AddNewCustomer");
			Assert.assertTrue(false);
		}
	}
	

}
