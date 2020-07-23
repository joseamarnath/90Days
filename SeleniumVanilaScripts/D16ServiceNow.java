package SeleniumVanilaScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D16ServiceNow {

	public static void main(String[] args) throws InterruptedException 
	{
		String requestID;
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		cdriver.manage().window().maximize();
		cdriver.get("https://dev92834.service-now.com/");
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		cdriver.findElementByXPath("//iframe[@title='Main Content']");

		cdriver.switchTo().frame(0);

		
		cdriver.findElementById("user_name").sendKeys("admin");
		
		cdriver.findElementById("user_password").sendKeys("India@123");
		cdriver.findElementById("sysverb_login").click();

		String title = cdriver.getTitle();
		System.out.println(title);

		// Entering change in filter texbox
		Thread.sleep(3000);
		cdriver.findElementByXPath("//input[@id='filter']").sendKeys("Service Catalog",Keys.ENTER);
		cdriver.findElementByXPath("(//div[@class='sn-widget-list-title'])[4]").click();

		
		// explicit wait is particularly for one webelement when there is a delay
		WebDriverWait ewait = new WebDriverWait(cdriver, 50);

		Thread.sleep(3000);
		// cdriver.switchTo().frame("gsft_main");
		ewait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gsft_main"));

		
		// clicking the Mobiles link
		//ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='homepage_category_only'])[8]"))).click();
		WebElement mobiles = cdriver.findElementByXPath("//img[@alt='Mobiles']");
		ewait.until(ExpectedConditions.elementToBeClickable(mobiles)).click();

		// clicking the Apple Iphone 6s
		ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()='Apple iPhone 6s']"))).click();
		
		//field Data Allowance selection
		WebElement dataAllowance = cdriver.findElementByXPath("(//select[@class='form-control cat_item_option '])[1]");
		Select dataAllowanceDropDown = new Select(dataAllowance);
		dataAllowanceDropDown.selectByValue("unlimited");
		
		//field Color selection
		WebElement color = cdriver.findElementByXPath("(//select[@class='form-control cat_item_option '])[2]");
		Select colorDropDown = new Select(color);
		colorDropDown.selectByValue("rose");
		
		//field Storage selection
		WebElement storage = cdriver.findElementByXPath("(//select[@class='form-control cat_item_option '])[3]");
		Select storageDropDown = new Select(storage);
		storageDropDown.selectByValue("onetwentyeight");
		
			
		//clicking ordernow
		cdriver.findElementById("oi_order_now_button").click();
		Thread.sleep(3000);
		
		
		//Verify Notification message
		String verificationMsg = "Thank you, your request has been submitted";
		String notification = cdriver.findElementByXPath("(//div[@class='notification notification-success']//span)[1]").getText();
		if(notification.contentEquals(verificationMsg))
		{
			System.out.println("Notification Message Verified");
		}
		else
		{
			System.out.println("Notification Message is not matching: "+notification);
		}
		
		requestID = cdriver.findElementByXPath("//a[@class='linked requestItemUrl']//b[1]").getText();
		System.out.println("Request Number for your Order is: "+requestID);
		
		
		cdriver.switchTo().defaultContent();
		
		//clicking Service Catalog --> Requests
		cdriver.findElementByXPath("//div[text()='Requests']").click();
		Thread.sleep(3000);
		
		//searching the request no
		ewait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("gsft_main"));
		cdriver.findElementByXPath("(//input[@placeholder='Search'])[1]").sendKeys(requestID, Keys.ENTER);
		Thread.sleep(2000);
		
		//clicking on Request no
		cdriver.findElementByXPath("//a[@class='linked formlink']").click();
		
		
		//Changing approval to rejected
		WebElement approval = cdriver.findElementById("sc_request.approval");
		Select approvalDropDown = new Select(approval);
		approvalDropDown.selectByValue("rejected");
		
		//Clicking update button
		cdriver.findElementById("sysverb_update").click();
		Thread.sleep(2000);
		
		//clicking the All in filter
		cdriver.findElementByXPath("//b[text()='All']").click();
		
		
		//searching the request no
		cdriver.findElementByXPath("(//input[@placeholder='Search'])[1]").sendKeys(requestID, Keys.ENTER);
		Thread.sleep(2000);
				
		//getting the status of the request
		String reqStatus = cdriver.findElementByXPath("(//td[@class='vt'])[4]").getText();
		String statusMsg = "Closed Rejected";
		if(reqStatus.contentEquals(statusMsg))
		{
			System.out.println("Request Status is verified");
		}
		else
		{
			System.out.println("Request Status is not matching: "+reqStatus);
		}
		
		// cdriver.switchTo().defaultContent();
		cdriver.close();
	}

}
