package SeleniumVanilaScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D5Zalando {
	
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1) Go to https://www.ajio.com/shop/sale
			2) Enter Bags in the Search field and Select Bags in Women Handbags
			3) Click on five grid and Select SORT BY as "What's New"
			4) Enter Price Range Min as 2500 and Max as 5000
			5) Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
			6) Verify the Coupon code for the price above 2890 is applicable for your product, if applicable then get the Coupon Code and the discount price for the coupon
			7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
			8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
			9) Click on ADD TO BAG and then GO TO BAG
			10) Check the Order Total before apply coupon
			11) Enter Coupon Code and Click Apply
			12) Verify the discount price matches with the product price
			13) Click on Delete and Delete the item from Bag
			14) Close all the browsers
		*/  
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://www.zalando.com/");
		//cdriver.manage().deleteAllCookies();
		Thread.sleep(2000);

		TargetLocator switchTo = cdriver.switchTo();
		Alert alert1 = switchTo.alert();
		alert1.accept();
		Thread.sleep(2000);
		
		cdriver.findElementByXPath("//a[@href='https://www.zalando.co.uk/']").click();
		
		//Clicking on Women
		cdriver.findElementByXPath("//span[text()='Women']").click();
		Thread.sleep(1000);
		
		//clicking CLothing
		cdriver.findElementByXPath("//span[text()='Clothing']").click();
		
		//Selecting Coats
		cdriver.findElementByXPath("(//a[text()='Coats'])[3]").click();
		
		try
		{
			cdriver.findElementByXPath("//button[@class='uc-btn uc-btn-primary']").click();
		}
		catch(Exception e)
		{
			System.out.println("Unable to click via class xpath - Banner Message NOT displayed");
		}
		try
		{
			cdriver.findElementByXPath("//button[@id='uc-btn-accept-banner']").click();
		}
		catch(Exception e)
		{
			System.out.println("Unable to click via ID xpath - Banner Message NOT displayed");
		}
		
				
		//clicking Material
		js=(JavascriptExecutor)cdriver;
		wait = new WebDriverWait(cdriver, 30);
		builder = new Actions(cdriver);
		WebElement material = cdriver.findElementByXPath("//span[text()='Material']");
		
		if(material.isDisplayed())
		{
			builder.moveToElement(material).click(material).perform();	
			
		}
		else
		{
			js.executeScript("arguments[0].click()", material);		
		}
		
		//Selecting Cotton
		
		WebElement cotton = cdriver.findElementByXPath("//span[text()='cotton (100%)']");
		builder.moveToElement(cotton).click(cotton).perform();
		//wait.until(ExpectedConditions.visibilityOf(cotton)).click();
		cdriver.findElementByXPath("//button[text()='Save']").click();
		
		//clicking Length
		Thread.sleep(2000);
		WebElement length = cdriver.findElementByXPath("//span[@data-label='Length']");
		if(length.isDisplayed())
		{
			//js.executeScript("arguments[0].click",length);
			builder.moveToElement(length).click(length).perform();
		}
		else
		{
			System.out.println("Length is not visible");
			js.executeScript("arguments[0].click", wait.until(ExpectedConditions.elementToBeClickable(length)));
		}
		
		//Selecting Thin Length
		cdriver.findElementByXPath("//span[text()='thigh-length']").click();
		cdriver.findElementByXPath("//button[text()='Save']").click();
		
		//Selecting the product
		Thread.sleep(5000);
		WebElement product = cdriver.findElementByXPath("(//div[contains(text(),'CLASSIC TRENCH - Trenchcoat')])[1]");
		builder.moveToElement(product).click(product).perform();
		Thread.sleep(2000);
		
		//selecting Color as Black
		cdriver.findElementByXPath("(//img[@alt='black'])[2]").click();
		
		//Selecting Size	
		Thread.sleep(3000);
		cdriver.findElementByXPath("//button[@aria-label='Choose your size']").click();
		cdriver.findElementByXPath("//span[text()='10']").click();
		
		
		//Verify Standard delivery is Free and clicking Add to Bag
		String deliveryCost = cdriver.findElementByXPath("(//span[text()='Free'])[1]").getText();
		
		if(deliveryCost.contentEquals("Free"))
		{
			System.out.println("Delivery Cost is: "+deliveryCost);
			cdriver.findElementByXPath("//span[text()='Add to bag']").click();
		}
		else
		{
			System.out.println("Content Delivery is Costable: "+deliveryCost);
		}
		
		//Navigate to Bag
		Thread.sleep(2000);
		WebElement yourBag = cdriver.findElementByXPath("//span[text()='Your bag']");
		builder.moveToElement(yourBag).perform();
		cdriver.findElementByXPath("//div[text()='Go to bag']").click();
		
		//Reading the Delivery Date and TIme
		String estimatedDelivery = cdriver.findElementByXPath("(//span[@class='z-2-text z-2-text-body z-2-text-black'])[1]").getText();
		System.out.println("Delivery Details is: "+estimatedDelivery);
		
		//Checkout and error message verification
		cdriver.findElementByXPath("(//div[text()='Go to checkout'])[1]").click();
		Thread.sleep(2000);
		cdriver.findElementById("login.email").sendKeys("test@yopmail.com");
		cdriver.findElementByXPath("//span[text()='Login']").click();
		String errorText = cdriver.findElementByXPath("//span[contains(@class,'C5k6gw gQjacQ')]").getText();
		System.out.println(errorText);
		
		//closing the open browser
		cdriver.close();
		

		
	}

}
