package SeleniumVanilaScripts;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D10AutoCar {
	
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1) Go to https://autoportal.com/
			2) Update the City as Chennai
			3) Select the Brand as "Renault"
			4) Select the Model as "Arkana"
			5) Print the Price			
		*/  
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://autoportal.com/");
		cdriver.manage().deleteAllCookies();
		
		//Clicking on Location
		Thread.sleep(2000);
		WebElement location = cdriver.findElementByXPath("//div[@class='maincity-in field']");
		location.click();
		
		//Entering new City Value	
		Thread.sleep(5000);
		WebDriverWait wait=new WebDriverWait(cdriver,15);
		WebElement city = cdriver.findElementById("ac_user_city");
		wait.until(ExpectedConditions.visibilityOf(city)).click();
		city.sendKeys("Chennai");
		Thread.sleep(3000);
		city.sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		
		//Clicking Confirm Button
		WebElement confrmBtn = cdriver.findElementByXPath("(//span[@class='red_but'])[1]");
		confrmBtn.click();
		
		//Selecting Brand
		Thread.sleep(5000); 
		WebElement brand = cdriver.findElementByXPath("(//select[@class='field m_b-15'])[1]");
		wait.until(ExpectedConditions.visibilityOf(brand));
		Select brandDropDown = new Select(brand);
		brandDropDown.selectByVisibleText("Renault");
		
		
		//Selecting Model 
		Thread.sleep(3000);
		WebElement model = cdriver.findElementByXPath("(//select[@class='field m_b-15'])[2]");
		wait.until(ExpectedConditions.visibilityOf(model));
		Select modelDropDown = new Select(model);
		modelDropDown.selectByVisibleText("Arkana");
		
		
		//clicking on Find New Cars Button
		WebElement findNewCarsBtn = cdriver.findElementByXPath("(//input[@class='red_but'])[1]");
		findNewCarsBtn.click();
		
		Thread.sleep(3000);
		WebElement price = cdriver.findElementByXPath("//span[@class='WebRupee']");
		
		
		//Printing the Price
		System.out.println("Expected Miniumum Price is: "+price.getAttribute("data-gtm-item-price-min"));
		System.out.println("Expected Maxiumum Price is: "+price.getAttribute("data-gtm-item-price-max"));
		
		// closes the window
		cdriver.close();
		

		
	}

}
