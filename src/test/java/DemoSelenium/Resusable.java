package DemoSelenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Resusable {
	
	
	
	public static String ReportsScreenshotAfterMethod(WebDriver driver, ITestResult result) throws IOException
	{
		 TakesScreenshot scrShot =(TakesScreenshot)driver;

	        //Call getScreenshotAs method to create image file
	         File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	         
	         String path=System.getProperty("user.dir")+"/Screenshots/"+result.getName()+System.currentTimeMillis()+".png";
	        //Move image file to new destination
	         System.out.println(System.getProperty("user.dir"));
	         System.out.println(path);
	        File DestFile=new File(path);

	        //Copy file at destination
         FileUtils.copyFile(SrcFile, DestFile);
		return path;
	}

	public void ScreenshotDuringMethod(WebDriver driver) throws IOException
	{
	 TakesScreenshot scrShot =(TakesScreenshot)driver;
     File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
     
     String actualn=driver.getTitle();
     File DestFile=new File("./Screenshots/"+actualn+System.currentTimeMillis()+".png");
     	        		
     //Copy file at destination
     FileUtils.copyFile(SrcFile, DestFile);  
	}
	
	public void sendEMail() throws EmailException 
	{
		/*	
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("priyanmad@gmail.com", "India345"));
		email.setSSLOnConnect(true);
		email.setFrom("priyanmad@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail SELENIUM");
		email.addTo("priyanrece@gmail.com");
		email.send();	*/
		
		 // Create the attachment
		  EmailAttachment attachment = new EmailAttachment();
		//  attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
		  attachment.setPath("C:\\Users\\Priyan\\Documents\\Selenium_Project\\Guru_Bank_Selenium\\test-output\\emailable-report.html");
		  attachment.setDisposition(EmailAttachment.ATTACHMENT);
		//  attachment.setDescription("Selenium Description");
		//  attachment.setName("Selenium SetName field");

		  // Create the email message
		  MultiPartEmail email = new MultiPartEmail();
		  email.setHostName("smtp.gmail.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("priyandtm@gmail.com", "India789"));
		  email.setSSLOnConnect(true);
			
		  email.addTo("priyandtm@gmail.com");
		  email.setFrom("priyandtm@gmail.com");
		  email.setSubject("Test Results");
		  email.setMsg("Please find attached EOD Selenium Results");
		  
		  // add the attachment
		  email.attach(attachment);

		  // send the email
		  email.send();
		}

	
}
