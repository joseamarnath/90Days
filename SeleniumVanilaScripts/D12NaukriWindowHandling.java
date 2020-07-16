package SeleniumVanilaScripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class D12NaukriWindowHandling 
{
	/*  1) Go to https://www.naukri.com/
		2) Three popup windows will get opened
		3) Capture the Company name from each window using the attribute value which holds the company name
		4) print the company name
		5) close each window		
	*/  
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		cdriver.get("https://www.naukri.com/");
		cdriver.manage().window().maximize();
		cdriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		//Window Handling
		Set<String> windowHandles = cdriver.getWindowHandles();
		List<String> lwh = new ArrayList<String>(windowHandles);
		int size = lwh.size();
		//System.out.println("Windows count is: "+size);

		//Iterating through each window and getting the Company Name
		for (int i = 0; i <size-1; i++) 
		{
			cdriver.switchTo().window(lwh.get(i+1));
			String cName = cdriver.findElementByXPath("//img[contains(@src,'gif')]").getAttribute("alt");
			System.out.println("Company Name: " + cName);
			System.out.println("Window Title is: "+cdriver.getTitle());
			cdriver.close();
		}
		
		//closing the parent main window
		cdriver.switchTo().window(lwh.get(0));
		cdriver.close();
		
	}
	

}
