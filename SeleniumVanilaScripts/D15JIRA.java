package SeleniumVanilaScripts;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class D15JIRA {
	
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	public static Actions builder;
	public static ChromeDriver cdriver;
	
	//function to upload files using Robot Class
	public void fileUpload (String path) throws AWTException {
        StringSelection strSelection = new StringSelection(path);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(strSelection, null);
 
        Robot robot = new Robot();
  
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(500);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
	
	
	
	public static void main(String[] args) throws InterruptedException, AWTException 
	{
	
		/*  1. Go to "https://id.atlassian.com/login"
			2. Enter email "hari.radhakrishnan@testleaf.com" and click continue
			3. Enter password "India@123" and click sign in
			4. Select the project "rest-api"
			5. Get the total number of issues and print the count alone (only number)
			6. Click on Create with Issue type as "Story" and Summary as "Created by <your name>" 
			7. Attach a image from local directory and Click Create
			8. Verify the newly created story by Search your Story and Confirm
			9. Open the newly created story 
			10. Modify the assignee as "Vivek"
			11. Change the Priority as Low
			12. Navigate to the Backlog
			13. Click on Recently Updated
			14. Validate the Assignee and Priority values for the newly created Story as expected
			15. Click on the Story 
			16. Click on Actions and Delete the Story
			17. Confirm the Delete
			18. Recently Updated section in Backlog should have any items
		*/  
		
		
		
		
//		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
//		cdriver = new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		FirefoxDriver cdriver = new FirefoxDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.get("https://id.atlassian.com/login");
		//cdriver.manage().deleteAllCookies();
		Thread.sleep(2000);

		//enter email
		cdriver.findElementById("username").sendKeys("hari.radhakrishnan@testleaf.com",Keys.ENTER);
		
		//enter password
		Thread.sleep(3000);
		cdriver.findElementById("password").sendKeys("India@123",Keys.ENTER);
		
		//clicking on rest-api
		wait = new WebDriverWait(cdriver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(cdriver.findElementByXPath("//h5[text()='rest-api']"))).click();
		
		//getting the count of issues 
		//Thread.sleep(5000);
		String issueCount = cdriver.findElementByXPath("//div[@class='ghx-issue-count']").getText();
		issueCount = issueCount.replaceAll("[^0-9]", "");
		System.out.println("Total Issues is: "+issueCount);
		
		
		//clicking on create
		cdriver.findElementByXPath("//span[text()='Create']").click();
		
		//confirming on issuetype as Story
		cdriver.findElementById("issuetype-field").sendKeys("Story",Keys.TAB);
//		String issueTypeExp = "Story";
//		String issueTypeAct = cdriver.findElementByXPath("(//input[@id='issuetype-field']/following::div)[1]").getText();
//		System.out.println("Selected value is: "+issueTypeAct);
//		System.out.println("preceding: "+cdriver.findElementByXPath("//div/preceding::input[@id='issuetype-field']").getText());
//		//Assert.assertEquals(issueTypeAct, issueTypeExp);
//		System.out.println("css: "+cdriver.findElementByXPath("//div/preceding::input[@id='issuetype-field']").getCssValue(issueTypeAct));
//		
		//entering Summary
		String summary = "Issue Created by Amarnath";
		Thread.sleep(2000);
		cdriver.findElementById("summary").sendKeys(summary);
		
		//clicking on Browse
		cdriver.findElementByXPath("//button[@class='issue-drop-zone__button']").click();
		
		//clicking upload a file
		Thread.sleep(3000);
		//cdriver.switchTo().frame(0);  // locating iframe by index
		cdriver.switchTo().frame(cdriver.findElement(By.tagName("iframe"))); //locating iframe using webelement
		Thread.sleep(3000);
		//WebElement upload = cdriver.findElementByXPath("//span[text()='Upload a file']");
		//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(cdriver.findElementByXPath("//div[@class='dropzone']//button")));
		
		cdriver.findElementByXPath("(//div[@class='dropzoneWrapper']//div)[1]");
		WebElement upload = cdriver.findElementByXPath("//div[@class='dropzone']//button");
		System.out.println("uploade text: "+upload.getText());
		wait.until(ExpectedConditions.elementToBeClickable(upload)).click();
		Thread.sleep(3000);
		
		//File upload process
		
		//WebElement addFile = cdriver.findElement(By.xpath(".//input[@type='file']"));
		//addFile.sendKeys("C:\\Users\\HP\\Pictures\\RM1.jpg",Keys.ENTER);
			
		D15JIRA obj = new D15JIRA();
		obj.fileUpload("C:\\Users\\HP\\Pictures\\RM1.jpg");
		
		//clicking on Insert button
		WebElement insert = cdriver.findElementByXPath("(//div[@class='hKfynL']//button)[2]");
		System.out.println("Insert text: "+insert.getText());
		wait.until(ExpectedConditions.elementToBeClickable(insert)).click();
		Thread.sleep(5000);
		cdriver.switchTo().defaultContent();
		
		//clicking on create submit button
		WebElement create = cdriver.findElementById("create-issue-submit");
		wait.until(ExpectedConditions.elementToBeClickable(create)).click();
		
		//switching back to default content
		cdriver.switchTo().defaultContent();
		Thread.sleep(2000);
		
		//searching the story
		cdriver.findElementById("search-field-input").sendKeys(summary,Keys.ENTER);
		Thread.sleep(3000);
		
		//span[text()='Issue Created by Amarnath']"
		cdriver.findElementByXPath("//span[text()='"+summary+"']").click();
		
		//page scroll for visibility of the elements Assignee and Priority
		js=(JavascriptExecutor)cdriver;
		//wait = new WebDriverWait(cdriver, 30);
		js.executeScript("window.scrollBy(0,55)");
		
		//clicking on the Assignee
		WebElement assignee = cdriver.findElementByXPath("//div[text()='Unassigned']");
		wait.until(ExpectedConditions.elementToBeClickable(assignee)).click();
		Thread.sleep(5000);
		
		//selecting Assignee as Vivek
		cdriver.findElementByXPath("//div[@id='react-select-assignee-option-1']").click();
		
		//Setting Priority to Low
		//WebElement priority = cdriver.findElementByXPath("//div[@class='sc-eNNmBn hwwbJs']//span[1]'");
		WebElement priority = cdriver.findElementByXPath("//span[text()='Medium']");
		priority.click();
		Thread.sleep(3000);
		cdriver.findElementByXPath("//div[@id='react-select-2-option-3']").click();
		
		
		//navigating to recently updated
		cdriver.findElementByXPath("(//div[text()='Backlog'])[1]").click();
		Thread.sleep(4000);
		
		String assignedTo = cdriver.findElementByXPath("(//div[@class='SingleLineTextInput__ReadView-sc-4hfvq0-0 epXzqq'])[1]").getText();
		System.out.println("Assigned to: "+assignedTo);
		if(assignedTo.contentEquals("Vivek"))
		{
			System.out.println("Assignee Verification Passed");
		}
		
		String prioritized = cdriver.findElementByXPath("//span[text()='Low']").getText();
		System.out.println("priority set to: "+prioritized);
		if(prioritized.contentEquals("Low"))
		{
			System.out.println("Priority Verification Passed");
		}
		
		
		//clicking on Actions
		cdriver.findElementByXPath("//span[@aria-label='Actions']").click();
		
		//clicking on delete
		Thread.sleep(2000);
		cdriver.findElementByXPath("//span[text()='Delete']").click();
		
		//confirming on delete
		cdriver.findElementByXPath("//span[text()='Delete']").click();
		Thread.sleep(3000);
		
		//Verify zero matches in recently update
		String matches = cdriver.findElementByXPath("//div[@class='ghx-description']").getText();
		System.out.println("Description:" +matches);
		if(matches.contains("There are no issues"))
		{
			System.out.println("Verification passed and no issues matching your search filter");
		}
		else
		{
			System.out.println("verification failed and your story was not deleted");
		}
		
		//closing the open browser
		cdriver.close();
		

		
	}

}
