package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AddCustomerPage {

	
	WebDriver Idriver;
	
	public AddCustomerPage(WebDriver rdriver) {

		Idriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	public static By NewCustomer=By.xpath("/html/body/div[3]/div/ul/li[2]/a");
	
	public static By customerName=By.name("name");
	public static By genderM=By.cssSelector("input[value='m']");
	public static By genderF=By.cssSelector("input[value='f']");
	public static By DOB=By.name("dob");
	public static By address=By.name("addr");
	
	public static By city=By.name("city");
	public static By state=By.name("state");
	public static By pin=By.name("pinno");
	public static By mobile=By.name("telephoneno");
	public static By email=By.name("emailid");
	public static By password=By.name("password");
	
	public static By submit=By.name("sub");
	
	
	public void clickNewCUstomer() {
	    Idriver.findElement(NewCustomer).click();
	}
		
	public void setCustName(String custName) {
	    Idriver.findElement(customerName).clear();
		Idriver.findElement(customerName).sendKeys(custName);
	}
	
	public void setGender(String gender) {
		
		if(gender.equals("Male"))
		{
			Idriver.findElement(genderM).click();
		}
		else
		{
			Idriver.findElement(genderF).click();
		}
	   
	}
	
	
	public void setDOBB(String mm, String dd, String yy) {
	    Idriver.findElement(DOB).clear();
		Idriver.findElement(DOB).sendKeys(mm);
		Idriver.findElement(DOB).sendKeys(dd);
		Idriver.findElement(DOB).sendKeys(yy);
	}
	
	public void setAddress(String addre) {
	    Idriver.findElement(address).clear();
		Idriver.findElement(address).sendKeys(addre);
	}
	
	public void setCity(String city1) {
	    Idriver.findElement(city).clear();
		Idriver.findElement(city).sendKeys(city1);
	}
	
	public void setState(String state1) {
	    Idriver.findElement(state).clear();
		Idriver.findElement(state).sendKeys(state1);
	}
	
	public void setPin(String pin1) {
	    Idriver.findElement(pin).clear();
		Idriver.findElement(pin).sendKeys(String.valueOf(pin1));
	}
	
	public void setMobile(String mobile1) {
	    Idriver.findElement(mobile).clear();
		Idriver.findElement(mobile).sendKeys(mobile1);
	}
	
	public void setMail(String email1) {
	    Idriver.findElement(email).clear();
		Idriver.findElement(email).sendKeys(email1);
	}
	
	public void setPass(String password1) {
	    Idriver.findElement(password).clear();
		Idriver.findElement(password).sendKeys(password1);
	}
	
	public void clickSubmit() {
	    Idriver.findElement(submit).click();
	}
}
