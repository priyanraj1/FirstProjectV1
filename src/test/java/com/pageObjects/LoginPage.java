package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
	
	WebDriver Idriver;
	
	public LoginPage(WebDriver rdriver) {

		Idriver=rdriver;
		PageFactory.initElements(rdriver, this);
	
	}
	
	public static By userName=By.name("uid");
	public static By pass=By.name("password");
	public static By clickSubmit=By.name("btnLogin");
	public static By logOut=By.xpath("/html/body/div[3]/div/ul/li[15]/a");
	
	public void setUserName(String uname) {
		
		Idriver.findElement(userName).clear();
		Idriver.findElement(userName).sendKeys(uname);
	}
	
	public void setPassword(String pwd) {
		
		Idriver.findElement(pass).clear();
		Idriver.findElement(pass).sendKeys(pwd);
	}

	public void clickSubmit() throws InterruptedException {
	
	Idriver.findElement(clickSubmit).click();
	Thread.sleep(2000);
}
	

	public void Logout() throws InterruptedException {
	
	Idriver.findElement(logOut).click();
	Thread.sleep(1000);
}
	  
}
