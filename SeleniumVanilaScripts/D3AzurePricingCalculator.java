package SeleniumVanilaScripts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D3AzurePricingCalculator {

	private static void veriyFile(String fname)
    {
		File f = new File("C:\\Users\\HP\\Downloads\\"+fname);

		if(f.exists())
		{
		    System.out.println("File "+fname+ " has been downloaded");
		}
		else
		{
		    System.out.println("File "+fname+ " was NOT downloaded!");
		}
    }

	
	public static void main(String[] args) throws InterruptedException 
	{
		
		
		/*  
			1) Go to https://azure.microsoft.com/en-in/
			2) Click on Pricing
			3) Click on Pricing Calculator
			4) Click on Containers
			5) Select Container Instances
			6) Click on Container Instance Added View
			7) Select Region as "South India"
			8) Set the Duration as 180000 seconds
			9) Select the Memory as 4GB
			10) Enable SHOW DEV/TEST PRICING
			11) Select Indian Rupee  as currency
			12) Print the Estimated monthly price
			13) Click on Export to download the estimate as excel
			14) Verify the downloded file in the local folder
			15) Navigate to Example Scenarios and Select CI/CD for Containers
			16) Click Add to Estimate
			17) Change the Currency as Indian Rupee
			18) Enable SHOW DEV/TEST PRICING
			19) Export the Estimate
			20) Verify the downloded file in the local folder
		*/  
	
	
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		cdriver.get("https://azure.microsoft.com/en-in/");
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		WebDriverWait ewait = new WebDriverWait(cdriver, 30);
		
		//clicking Pricing
		cdriver.findElementById("navigation-pricing").click();
		
		//clicking pricing calculator
		ewait.until(ExpectedConditions.elementToBeClickable(By.linkText("Pricing calculator"))).click();
		
		//clicking contaniers using javascript for page
		WebElement conatiner = cdriver.findElementByXPath("//button[text()='Containers']");
		JavascriptExecutor js = (JavascriptExecutor) cdriver; 
		//js.executeScript("arguments[0].scrollIntoView();", conatiner); //Scrolling the web page until the visibility of the element.
		js.executeScript("window.scrollBy(0,10)"); // scrolling down by 10 pixels
		conatiner.click();
		
		//cdriver.findElementByXPath("//button[@data-event-property='containers']").click();
		cdriver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		 
		//clicking on the popup notications view button link
		ewait.until(ExpectedConditions.elementToBeClickable(By.id("new-module-loc"))).click();

		//selecting region
		WebElement selectRegion = cdriver.findElementByXPath("(//select[@class='select'])[1]");
		Select region = new Select(selectRegion);
		region.selectByVisibleText("South India");
		
		//setting duration
		WebElement durationTxtBox = cdriver.findElementByXPath("(//input[@class='text-input numeric'])[2]");
		durationTxtBox.sendKeys(Keys.ARROW_LEFT);
		durationTxtBox.sendKeys("8000");
		
		//setting memory
		WebElement memory = cdriver.findElementByXPath("(//select[@class='select'])[3]");
		Select memoryDropDown = new Select(memory);
		memoryDropDown.selectByVisibleText("4 GB");
		
		
		//Enable show  Dev/Test Pricing toggle button
		js.executeScript("window.scrollBy(0,30)"); // scrolling down by 30 pixels
		Thread.sleep(2000);
//		WebElement toggleSlide = cdriver.findElementByXPath("//div[@class='toggler-slide ']");
//		toggleSlide.click();
		WebElement togglebutton = cdriver.findElementById("devtest-toggler");
		togglebutton.click();
		
		//selecting Indian Currency
		WebElement currency = cdriver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currencyDropDown = new Select(currency);
		currencyDropDown.selectByValue("INR");
		
		//printing currency
		String estimatedMonthyCost = cdriver.findElementByXPath("(//span[@class='numeric']//span)[6]").getText();
		String monthlyEstimate = estimatedMonthyCost.substring(1);
		System.out.println("Container Estimated Monthly cost: "+monthlyEstimate);
		
		
		//clicking export button
		cdriver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		//verifying the file is downloaded
		Thread.sleep(2000);
		veriyFile("ExportedEstimate.xlsx");
		
		//clicking expample scenraios
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-5000)"); // scrolling up by 30 pixels
		cdriver.findElementById("solution-architectures-picker").click();
		
		//clicking CI/CD for Containers
		cdriver.findElementByXPath("(//button[@class='solution-architecture-item false'])[2]").click();
		
		//clicking add to estimate
		cdriver.findElementByXPath("//button[@class='button button--secondary01 pull-right']").click();
		Thread.sleep(6000);		
		WebElement notificationMsg = cdriver.findElementByXPath("//span[text()='Estimate added!']");
		String msg = ewait.until(ExpectedConditions.visibilityOf(notificationMsg)).getText();
		System.out.println(msg);
		
		//Enable show  Dev/Test Pricing toggle button
		js.executeScript("window.scrollBy(0,1000)"); // scrolling down by 1000 pixels
		
		//selecting Indian Currency
		WebElement currency1 = cdriver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select currencyDropDown1 = new Select(currency1);
		currencyDropDown1.selectByValue("INR");
		
		//ewait.until(ExpectedConditions.elementToBeClickable(togglebutton)).click();
		WebElement togglebutton1 = cdriver.findElementById("devtest-toggler");
		ewait.until(ExpectedConditions.elementToBeClickable(togglebutton1)).click();		
		
		//printing currency
		String estimatedMonthyCost1 = cdriver.findElementByXPath("(//span[@class='numeric']//span)[2]").getText();
		String monthlyEstimate1 = estimatedMonthyCost1.substring(1);
		System.out.println("CI/CD Estimated Monthly cost: "+monthlyEstimate1);
		
		//clicking export button
		cdriver.findElementByXPath("//button[@class='calculator-button button-transparent export-button']").click();
		
		 
		//verify file downloaded
		Thread.sleep(2000);
		veriyFile("ExportedEstimate (1).xlsx");
		
		
		//closing the open browser windows
		cdriver.close();
		
	
		
	}


	
}
	
	
