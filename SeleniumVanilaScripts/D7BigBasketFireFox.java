package SeleniumVanilaScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D7BigBasketFireFox 
{
	
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		FirefoxDriver driver = new FirefoxDriver();
		
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		ChromeDriver driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);

		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//2) mouse over on  Shop by Category
		WebElement catg =  driver.findElementByXPath("//a[@qa='categoryDD']");
		action.moveToElement(catg).pause(2000).build().perform();


		//3) Go to Beverages and Fruit juices & Drinks		
		action.moveToElement(driver.findElementByXPath("(//a[text()='Beverages'])[2]")).pause(2000).build().perform();
		action.moveToElement(driver.findElementByXPath("(//a[@href='/pc/beverages/fruit-juices-drinks/?nc=nb'])[2]")).pause(2000).build().perform();
				
		//4) Click on JUICES
		//wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[@qa='catL3'])[5]")));
		action.moveToElement(driver.findElementByXPath("(//div[contains(@class,'col-md-4 col-sm-6')])[2]")).build().perform();
		action.moveToElement(driver.findElementByXPath("(//a[@ng-href='/pc/beverages/fruit-juices-drinks/juices-sweetened/?nc=nb'])[2]")).click().perform();
		
		
		
		
	}

}
