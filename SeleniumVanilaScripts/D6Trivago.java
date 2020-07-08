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

public class D6Trivago {
	
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1) Go to https://www.trivago.com/
			2) Type Agra in Destination and select Agra, Uttar Pradesh.
			3) Choose May 15 as check in and May 30 as check out
			4) Select Room as Family Room
			5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
			6) Click Confirm button and click Search
			7) Select Accommodation type as Hotels only and choose 4 stars
			8) Select Guest rating as Very Good
			9) Set Hotel Location as Agra Fort and click Done
			10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
			11) Sort the result as Rating & Recommended
			12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
			13) Print the URL of the Page
			14) Print the Price of the Room and click Choose Your Room
			15) Click Reserve and I'll Reserve
			16) Close the browser
		*/  
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://www.trivago.com");
		cdriver.manage().deleteAllCookies();
		Thread.sleep(2000);

		try {
			cdriver.findElementById("onetrust-accept-btn-handler").click();
		} catch (Exception e) {
			System.out.println("findBYID worked to click on accept cookies");
		}
		
		try {
			cdriver.findElementByXPath("//button[text()='OK']").click();
			
		} catch (Exception e) {
			System.out.println("xpath worked to click on accept cookies");
		}

		cdriver.navigate().refresh();
		//Thread.sleep(5000);
		cdriver.findElementById("querytext").sendKeys("Agra");
		Thread.sleep(3000);
		cdriver.findElementByXPath("//span[contains(text(),'Uttar ')]").click();
		//WebElement city = cdriver.findElementByXPath("//span[contains(text(),'Uttar ')]");
		//wait.until(ExpectedConditions.elementToBeClickable(city)).click();
		Thread.sleep(2000);
	//	js.executeScript("window.scrollBy(0,30)");
				
		//selecting check in checkout dates
		WebElement checkinDate = cdriver.findElementByXPath("(//time[@class='dealform-button__label'])[1]");
		WebElement checkoutDate = cdriver.findElementByXPath("(//time[@class='dealform-button__label'])[2]");
		
		cdriver.findElementByXPath("//time[@datetime='2020-07-15']").click();
		Thread.sleep(1000);
		cdriver.findElementByXPath("//time[@datetime='2020-07-30']").click();
		
		//selecting family rooms
			
		//cdriver.findElementByXPath("(//span[text()='30'])[2]").click();
//		try 
//		{
//			WebElement familyRoom = cdriver.findElementByXPath("//span[text()='Family rooms']");	
//			WebElement noofChild = cdriver.findElementById("select-num-children-2");
//			noofChild.click();
//			Select childDropDown = new Select(noofChild);
//			childDropDown.selectByVisibleText("1");
//			
//			WebElement childAge = cdriver.findElementById("select-ages-children-2-3");
//			childAge.click();
//			Select childAgeDropDown = new Select(childAge);
//			childAgeDropDown.selectByVisibleText("4");
//			
//			//clicking confirm button
//			cdriver.findElementByXPath("//span[text()='Confirm']").click();
//		} 
//		catch (Exception e) 
//		{
			//System.out.println("Family Rooms option was not displayed");
			cdriver.findElementById("children-input").clear();
			cdriver.findElementById("children-input").sendKeys("1", Keys.TAB);
			
			WebElement childAge1 = cdriver.findElementById("child-0");
			childAge1.click();
			Select childAgeDropDown1 = new Select(childAge1);
			childAgeDropDown1.selectByVisibleText("4");
			
			//clicking Apply button
			cdriver.findElementByXPath("(//button[text()='Apply'])[1]").click();
			
			
			//clicking search
			cdriver.findElementByXPath("//span[text()='Search']").click();
//		}
		
		Thread.sleep(8000);
		
		//Selecting Accomodation Type
		//WebElement accomodation = cdriver.findElementByXPath("(//button[@class='filter-item filter-item--select js-toolbar-hover-buttn'])[1]");
		WebElement accomodation = cdriver.findElement(By.xpath("//strong[text()='Accommodation']"));
		accomodation.click();
		//builder.moveToElement(accomodation).perform();
		//cdriver.findElementByXPath("//label[text()='Hotels only']").click();  //getting element interactable and not clickable error.
		cdriver.findElementById("acc-type-filter-1").click();
		//cdriver.findElementByXPath("//title[text()='4-star hotels']").click();
		cdriver.findElementByXPath("//button[@value='1320/105']//span[1]").click();
		cdriver.findElementByXPath("//button[text()='Done']").click();
		
		
		//Filter by Rating
		WebElement guestRating = cdriver.findElementByXPath("(//button[@class='filter-item filter-item--select js-toolbar-hover-button'])[2]");
		guestRating.click();
		//builder.moveToElement(guestRating).perform();
		cdriver.findElementByXPath("(//li[@class='range__item']//button)[2]").click();
		
		
		//filter by location
		WebElement location = cdriver.findElementByXPath("(//button[@class='filter-item filter-item--select js-toolbar-hover-button'])[3]");
		//builder.moveToElement(location).perform();
		location.click();
		WebElement city = cdriver.findElementById("pois");
		city.click();
		Select cityDropDown = new Select(city);
		cityDropDown.selectByVisibleText("Agra Fort");
		cdriver.findElementByXPath("//button[text()='Done']").click();
		
		//Sort By
		WebElement sortBy = cdriver.findElementById("mf-select-sortby");
		sortBy.click();
		Select sortDropDown = new Select(sortBy);
		sortDropDown.selectByVisibleText("Rating & Recommended");
		
		
		//Printing the list of hotels displayed
		List<WebElement> hotelNames = cdriver.findElements(By.xpath("//h3[@class='m-0']//span"));	
		int size = hotelNames.size();
		System.out.println("Total no of Hotels Fetched is: "+size);
		System.out.println("List of Hotels:");
		System.out.println("***************");
		Thread.sleep(10000);
		for(int i=0;i<size;i++)
		{
			System.out.println((i+1)+". " +hotelNames.get(i).getText());
		}
		
		Thread.sleep(5000);
		String currentURL = cdriver.getCurrentUrl();
		System.out.println("Current URL is: "+currentURL);
		
		//clicking on the deal of first resulting hotel
		cdriver.findElementByXPath("(//span[text()='View Deal'])[1]").click();
		
		
		Set<String> setWindowHandles = cdriver.getWindowHandles();
		List<String> listWindowHandles = new ArrayList<String>(setWindowHandles);
		
		// getting the second window
		String secondWindow = listWindowHandles.get(1);		
		cdriver.switchTo().window(secondWindow);
		cdriver.manage().deleteAllCookies();
		cdriver.navigate().refresh();
		
		//printing price and clicking choose your room
		String price = cdriver.findElementByXPath("(//div[contains(@class,'bui-price-display__value prco-text-nowrap-helper')])[1]").getText();
		System.out.println("Price of Hotel: "+price.substring(2));
		cdriver.findElementByXPath("(//span[@class='bui-button__text'])[1]").click();
		
		//Selecting Reseve and I will Reserve
		cdriver.findElementByXPath("(//span[@class='bui-button__text'])[1]").click();
		Thread.sleep(2000);
		cdriver.findElementByXPath("(//button[@type='submit'])[3]").click();
		
		// closes the second window
		cdriver.close();

		// going back to first window again
		cdriver.switchTo().window(listWindowHandles.get(0));

		// closes the first window
		cdriver.close();
		

		
	}

}
