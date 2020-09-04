package Guru99_BankingDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Day1 {

	public static void main(String[] args) {


		System.out.println("Welcome Mate");
		
		WebDriver driver;
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Priyan\\Documents\\chromedriver.exe");
		driver=new ChromeDriver();
		 
		// Open the website
		driver.get(" http://www.demo.guru99.com/V4/index.php");
		 
		//Enter UserName
		WebElement UserName=driver.findElement(By.cssSelector("input[name='uid']"));
		UserName.sendKeys("mngr263770");
		
		WebElement Pass=driver.findElement(By.cssSelector("input[name='password']"));
		Pass.sendKeys("mEpYqAm");
		
		WebElement SubmitBtn=driver.findElement(By.cssSelector("input[name='btnLogin']"));
		SubmitBtn.click();
		
		String welcomeMsg=driver.findElement(By.className("heading3")).getText();
		System.out.println(welcomeMsg);
		
		Assert.assertEquals("Welcome To Manager's Page of Guru99 Bank", welcomeMsg);
		
		// Close browser
		//driver.close();
		}

	}