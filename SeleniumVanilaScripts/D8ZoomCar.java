package SeleniumVanilaScripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D8ZoomCar {
	
	public static ChromeDriver cdriver;
	
	public static void snapShot(String xpath)
	{
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		WebElement snap = cdriver.findElementByXPath(xpath);
		File source = snap.getScreenshotAs(OutputType.FILE);
		File target = new File("./screenshot/"+number+".png");
		try 
		{
			FileUtils.copyFile(source, target);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1. Go to https://www.zoomcar.com/chennai
			2. Click on Start your wonderful journey
			3. Select  any location under POPULAR PICK-UP POINTS and click next
			4. Select tomorrow's date and time as 9:00 AM as start date and time and Click Next
			5. Click on Show More and Select tomorrow's date and Select time as 6:00 PM as end date  and Click Done
			6.  Take the snapshot for PICKUP TIME and DROP OFF TIME
			7. Validate the pickup time and Drop off time are correct (as you selected) and click on Done
			8. Click on Price: High to Low and validate the sort order of the car price programmatically
			9. Print all the Car name and respective Price from High to Low ( car name -- price )
			10. Take snapshot of the details for the Highest price car
			11. Click on Know More for the Highest price car and print the rate after 45Kms
			12. Close the Browser
		*/  
		
		String pickupInfoConfirmation ="";
		String dropInfoConfirmation = ""; 
	
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://www.zoomcar.com/chennai");
		//cdriver.manage().deleteAllCookies();
		Thread.sleep(3000);
		
		cdriver.findElementByClassName("search").click();
		
		//Selecting pickup point and clicking next
		Thread.sleep(2000);
		cdriver.findElementByXPath("(//div[@class='items'])[1]").click();
		cdriver.findElementByXPath("//button[@class='proceed']").click();
		
		
		//Pickup Date and Time
		Thread.sleep(3000);
		WebElement date = cdriver.findElementByXPath("//div[@class='days']//div[@class='day low-price']");
		date.click();
		String dt = date.getText();
		String dt1 = dt.substring(4, dt.length());
		cdriver.findElementByXPath("(//span[text()='09:00'])[1]").click();
		cdriver.findElementByXPath("//button[@class='proceed']").click();
		
		//Dropp Date and Time
		Thread.sleep(3000);
		cdriver.findElementByXPath("//div[@class='show-more']").click();
		//cdriver.findElementByXPath("(//li[@class='active low-price'])[1]").click();
		cdriver.findElementByXPath("//li[text()='"+dt1+"']").click();
		cdriver.findElementByXPath("(//span[text()='18:00'])[1]").click();
		
		//storing pickup details
		String pickupInfo = cdriver.findElementByXPath("(//div[@class='label time-label'])[1]").getText();
//		System.out.println("PickUp Info: "+pickupInfo);
		String s1 = pickupInfo.replace("morning", " AM");
		String pickupDay = s1.substring(4, 10);
		String pickupTime = s1.substring(17, s1.length());
		System.out.println("Pickup Details");
		System.out.println("**************");
		System.out.println("Date: "+pickupDay);
		System.out.println("Time: "+pickupTime);
		System.out.println();
		
		
		//storing drop details
		String dropInfo = cdriver.findElementByXPath("(//div[@class='label time-label'])[2]").getText();
		String ss1 = dropInfo.replace("evening", " PM");
		String dropDay = ss1.substring(4, 10);
		String dropTime = ss1.substring(17, ss1.length());
		System.out.println("Drop Details");
		System.out.println("**************");
		System.out.println("Date: "+dropDay);
		System.out.println("Time: "+dropTime);
		
		
		//Taking snapshot of pickup and drop Info
		snapShot("//div[@class='breadcrumb']");

		
		//Getting the displayed pickup and Drop Times
		String pickupInfoConf = cdriver.findElementByXPath("(//div[@class='label time-label'])[1]").getText();
		if(pickupInfoConf.contains("evening"))
		{
			pickupInfoConfirmation = pickupInfoConf.replaceAll("evening", " PM");
		}
		else
		{
			pickupInfoConfirmation = pickupInfoConf.replaceAll("morning", " AM");
		}
		String pickupTime1 = pickupInfoConfirmation.substring(17, pickupInfoConfirmation.length());
		String dropInfoConf = cdriver.findElementByXPath("(//div[@class='label time-label'])[2]").getText();
		if(dropInfoConf.contains("evening"))
		{
			dropInfoConfirmation = dropInfoConf.replaceAll("evening", " PM");
		}
		else
		{
			dropInfoConfirmation = dropInfoConf.replaceAll("morning", " AM");
		}
		String dropTime1 = dropInfoConfirmation.substring(17, dropInfoConfirmation.length());
		
		
		//Comparing the selection times against the displayed times
		if(pickupTime.contentEquals(pickupTime1) && (dropTime.contentEquals(dropTime1)))
		{
			System.out.println("Both Pickup time & Drop Time selection matches");
			System.out.println();
			//clicking done
			cdriver.findElementByXPath("//button[@class='proceed']").click();
			
		}
		else
		{
			System.out.println("Pickup time & Drop Time selection NOT matching. Please Recheck");
			
		}
		
		
		
		//Sorting by Price - High to Low
		Thread.sleep(4000);
		cdriver.findElementByXPath("//div[text()=' Price: High to Low ']").click();
		
		
		//Getting the carnames and car prices
		System.out.println("Car Name and Price List");
		System.out.println("***********************");
		List<WebElement> price =cdriver.findElements(By.xpath("//div[@class='new-price']"));
		List<WebElement> carName = cdriver.findElementsByXPath("//div[@class='details']//h3");
		Map<String, String> map = new LinkedHashMap<String, String>();
		int  count=0;
		for(int i=0;i<=price.size()-1;i++)
		{
			WebElement carnames = carName.get(i);
			WebElement carprices=price.get(i);
			map.put(carnames.getText(), carprices.getText().replaceAll("[^0-9.]", ""));
			if((i>0)&&i<=price.size()-1)
			{
				//converting the prices to Integer to compare the Sorting function programmatically
				int first = Integer.parseInt(price.get(i-1).getText().replaceAll("[^0-9.]", ""));
				int current = Integer.parseInt(price.get(i).getText().replaceAll("[^0-9.]", ""));
				
				//condition to check the sorting order programmatically for Step8
				if(first>current)
				{
					count++;
				}
			}
				
			
		}
		//System.out.println("count: "+count);
		if(count==price.size()-1)
		{
			System.out.println("Sorting from high to Low is verified.");
			
		}
		else
		{
			System.out.println("Sorting from high to low is not proper");
			
		}

		
		//Copy from MAP to SET and printing the Car name and its equivalent price
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) 
		{
			System.out.println(entry.getKey() + " price is: " + entry.getValue());
		}
		
		//Taking snapshot of highest car price and its details
		snapShot("(//div[@class='car-item-policies'])[1]");
		
		//clicking Know More
		cdriver.findElementByXPath("(//div[@class='know-more-details'])[1]").click();
		Thread.sleep(2000);
		String pricePerKm = cdriver.findElementByXPath("(//div[@class='price-info'])[1]").getText();
		System.out.println("Rate for First 45KM: "+pricePerKm.substring(1,7));
		
		// closes the window
		cdriver.close();
		

		
	}

}

