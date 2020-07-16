package SeleniumVanilaScripts;

import java.awt.PageAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.functors.ExceptionFactory;
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

public class D11HPLaptop {
	
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  1) Go to https://store.hp.com/in-en/
			2) Mouse over on Laptops menu and click on Pavilion
			3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i5
			4) Hard Drive Capacity -->500GB to 1TB
			5) Identify the first product which is having in Stock
			6) Print the Product Name and Price
			7) Click on Add to Cart
			8) Click on Shopping Cart icon --> Click on View and Edit Cart
			9) Check the Shipping Option --> Check availability at Pincode
			10) Verify the order Total against the product price
			11) Proceed to Checkout if Order Total and Product Price matches
			12) Click on Place Order
			13) Verify that all the mandatory fields are getting error message and confirm
			14) Close Browser
		*/  
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://store.hp.com/in-en/default");
		cdriver.manage().deleteAllCookies();
		Thread.sleep(6000);
		try 
		{
			if(cdriver.findElementByXPath("//button[text()='Accept Cookies']").isDisplayed());
			{
				cdriver.findElementByXPath("//button[text()='Accept Cookies']").click();
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//mouse hover on Laptop
		builder = new Actions(cdriver);
		WebElement laptop = cdriver.findElementByXPath("(//a[@id='ui-id-2'])[2]");
		builder.moveToElement(laptop).perform();

		//mouse hover on pavilion
		WebElement pavilionLaptop = cdriver.findElementByXPath("(//span[text()='Pavilion'])[1]");
		builder.moveToElement(pavilionLaptop).click(pavilionLaptop).perform();
		Thread.sleep(5000);
	
//		WebElement processor = cdriver.findElementByXPath("(//span[text()='Processor'])[1]");
//		wait.until(ExpectedConditions.elementToBeClickable(processor)).click();
		//cdriver.findElementByXPath("(//span[text()='Processor'])[1]").click();
		//Thread.sleep(2000);
		
		cdriver.navigate().refresh();
		Thread.sleep(5000);
		wait=new WebDriverWait(cdriver,30);
		
		if(cdriver.findElementByXPath("(//span[text()='Processor'])[2]").isDisplayed())
		{
			WebElement processor2 = cdriver.findElementByXPath("(//span[text()='Processor'])[2]");
			wait.until(ExpectedConditions.elementToBeClickable(processor2)).click();
			cdriver.findElementByXPath("(//span[text()='Intel Core i5'])[1]").click();
			Thread.sleep(2000);			
		}
		else 
		{
			//System.out.println("Shrinked display");
			WebElement processor = cdriver.findElementByXPath("(//span[text()='Processor'])[1]");
			wait.until(ExpectedConditions.elementToBeClickable(processor)).click();
			Thread.sleep(2000);
			
			WebElement processor2 = cdriver.findElementByXPath("(//span[text()='Processor'])[2]");
			wait.until(ExpectedConditions.elementToBeClickable(processor2)).click();
			cdriver.findElementByXPath("(//span[text()='Intel Core i5'])[1]").click();
			Thread.sleep(5000);
			
		}
		
		//setting up harddrive capacity filter
		//cdriver.navigate().refresh();
		Thread.sleep(5000);
		try 
		{
			if(cdriver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").isDisplayed())
			{
				cdriver.findElementByXPath("//div[@class='inside_closeButton fonticon icon-hclose']").click();
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		WebElement HardDrive =cdriver.findElementByXPath("(//span[text()='Hard Drive Capacity'])[1]");
		wait.until(ExpectedConditions.elementToBeClickable(HardDrive)).click();
		//Thread.sleep(2000	;
		wait.until(ExpectedConditions.elementToBeClickable(cdriver.findElementByXPath("(//span[text()='500 GB to 1 TB'])[1]"))).click();
		Thread.sleep(8000);
		
		//identify the first in stock product and adding to cart.
		//Also Getting price of 1st In Stock item
		
		WebElement price = cdriver.findElementByXPath("//div//span[text()='In Stock']/preceding::span[@id='product-price-10511']");
		String priceValue = price.getText().replaceAll("[^0-9.]", "");
		
		WebElement product = cdriver.findElementByXPath("//div//span[text()='In Stock']/preceding::a[@class='product-item-link']");
		String productName = product.getText();
		
		System.out.println("First In Stock Product details:");
		System.out.println("*******************************");
		System.out.println(productName+" - "+priceValue);
		
		
		//clicking on Add the Cart
		cdriver.findElementByXPath("//div//span[text()='In Stock']/following::span[text()='Add To Cart']").click();
		Thread.sleep(7000);
		
		//clicking shopping cart icon
		wait.until(ExpectedConditions.elementToBeClickable(cdriver.findElementByXPath("//a[@class='action showcart']"))).click();
		
		//click view and edit cart
		cdriver.findElementByXPath("//a[@class='action primary viewcart']").click();
		Thread.sleep(10000);
		
		//Checking Pincode Availability
		cdriver.findElementByXPath("//input[@name='pincode']").sendKeys("605008",Keys.ENTER);
//		Thread.sleep(2000);
//		cdriver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(5000);
			
		String msg = cdriver.findElementByXPath("//div[@class='standard-deliver-message']").getText();
		if(msg.contentEquals(""))
		{
			String available = cdriver.findElementByXPath("//div[@class='delivery-days']").getText();
			System.out.println(available);
		}
		else
		{
			System.out.println(msg);
		}
		
		
		//Verify product price and total price
		String totalCartPrice = cdriver.findElementByXPath("//td[@data-th='Order Total']//span[1]").getText().replaceAll("[^0-9.]", "");
		if(priceValue.contentEquals(totalCartPrice)) 
		{
			System.out.println("Both Product Price and Total Price are matching");
			
			//clicking on checkout
			cdriver.findElementByXPath("(//button[@id='sendIsCAC'])[1]").click();
			Thread.sleep(20000);
			WebElement placeOrder = cdriver.findElementByXPath("(//span[text()='Place Order'])[3]");
			//System.out.println(placeOrder.getText());
			wait.until(ExpectedConditions.elementToBeClickable(placeOrder)).click();			
			
		}
		else
		{
			System.out.println("Product and Total price not matching");
		}
		
		//Validating Mandatory fields and printing the error message thrown.
		
		//email error Validation
		String emailError = cdriver.findElementByXPath("//label[@for='customer-email']/following-sibling::div[1]").getText();
		if(emailError.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("Email Mandatory Field is verified");
			System.out.println(emailError);
		}
		
		//FirstName error validation 
		String firstNameErr = cdriver.findElementByXPath("(//span[text()='First Name']/following::div/span)[1]").getText();
		if(firstNameErr.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("FirstName Mandatory Field is verified");
			System.out.println(firstNameErr);
		}
		
		//LastName error validation 
		String lastNameErr = cdriver.findElementByXPath("(//span[text()='Last Name']/following::div/span)[1]").getText();
			if(lastNameErr.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("LastName Mandatory Field is verified");
			System.out.println(lastNameErr);
		}
		
		//Phone Number error validation
		String phonenoErr = cdriver.findElementByXPath("(//span[text()='Phone Number']/following::div/span)[2]").getText();
		if(phonenoErr.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("Phone Number Mandatory Field is verified");
			System.out.println(phonenoErr);
		}
		
		//Street Address error Validation
		String addressErr = cdriver.findElementByXPath("(//span[text()='Street Address']/following::div/span)[1]").getText();
		if(addressErr.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("Address Mandatory Field is verified");
			System.out.println(addressErr);
		}
		
		//Terms and condition Error Validation
		String termsErr = cdriver.findElementByXPath("(//a[text()='Privacy Policy']/following::div)[1]").getText();
		if(termsErr.isEmpty())
		{
			System.out.println("No error message displayed");
		}
		else
		{
			System.out.println("Terms & Conditions Mandatory Field is verified");
			System.out.println(termsErr);
		}
		
		
		//closing the open browser
		cdriver.close();
		

		
	}

}
