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

import com.paulhammant.ngwebdriver.NgWebDriver;

public class D9SuperCalculator {
	
	public static ChromeDriver cdriver;
	public static JavascriptExecutor js;
	public static NgWebDriver ngWeb;

	
	
	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1. Go to https://juliemr.github.io/protractor-demo/
			2. Input a number
			3. Select Multiplication
			4. Input second number
			5. Click GO
			6. Wait for the output to load and print the results
			Condition:
			* Should not use Thread.sleep
		*/  
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		cdriver = new ChromeDriver();
		js = (JavascriptExecutor) cdriver;
		
		//NGWebDriver is a class which takes JavaScriptExecutor as input while initializing and is used to handle waits in angluar js applications.
		ngWeb = new NgWebDriver(js);
		
		//Get URL
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://juliemr.github.io/protractor-demo/");
		
		cdriver.findElementByXPath("//input[@ng-model='first']").sendKeys("5",Keys.TAB);
		cdriver.findElementByXPath("//button[@class='btn']").click();
		
		WebElement operat = cdriver.findElementByXPath("//select[@ng-model='operator']");
		operat.click();
		Select operatorDD = new Select(operat);
		operatorDD.selectByVisibleText("*");
		
		cdriver.findElementByXPath("//input[@ng-model='second']").sendKeys("4",Keys.TAB);
		

		WebElement output = cdriver.findElementByXPath("//h2[@class='ng-binding']");
		//WebDriverWait wait = new WebDriverWait(cdriver, 30);
		//wait.until(ExpectedConditions.textToBePresentInElement(output, "20"));
		
		
		//With help of ngDriver variable we can handle angular specific waiting issues.  
		//The below code will wait for angular requests to finish.
		ngWeb.waitForAngularRequestsToFinish();
		System.out.println(output.getText());
		
		
		
	}

}

