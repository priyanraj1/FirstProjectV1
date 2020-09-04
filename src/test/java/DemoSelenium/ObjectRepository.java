package DemoSelenium;

import org.openqa.selenium.By;
public class ObjectRepository {
	
	//Object Elements for HomePageScreenshot Method
	public static By name=By.name("email");
	public static By pass=By.name("password");
	public static By submitBtn=By.id("buttonLogin");
	public static By WelcomeUserName=By.xpath("/html/body/div/div[2]/h1/strong");
	
	//Object Elements for Add Invoice Method
	public static By InvoiceBtn=By.xpath("//*[@id=\"dashmenu\"]/div[6]/button");
	public static By AddInvoiceBtn=By.xpath("//*[@id=\"dashmenu\"]/div[6]/ul/li[2]/a");
	public static By InvoiceNumber=By.id("invoiceNumber");
	public static By VendorTax=By.id("vendorTaxID");
	public static By BackToHomeBtn=By.xpath("/html/body/div/div[1]/div/ol/li[1]/a");

	//Logout Method
	public static By LogOutBtn=By.cssSelector("a[href=\'/account/logout/\']");
}
