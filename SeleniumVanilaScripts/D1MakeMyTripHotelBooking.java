package SeleniumVanilaScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class D1MakeMyTripHotelBooking {

	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  1) Go to https://www.makemytrip.com/
			2) Click Hotels
			3) Enter city as Goa, and choose Goa, India
			4) Enter Check in date as Next month 15th (July 15) and Check out as start date+4
			5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
			6) Click Search button
			7) Select locality as Baga
			8) Select user rating as 3&above(good) under Select Filters
			9) Select Sort By: Price-Low to High
			10) Click on the first resulting hotel and go to the new window
			11) Print the Hotel Name 
			12) Click VIEW THIS COMBO button under Recommended Combo
			13) Click on BOOK THIS COMBO button
			14) Print the Total Payable amount
			15) Close the browser 
		*/  

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		cdriver.get("https://www.makemytrip.com/");
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		cdriver.manage().deleteAllCookies();
		
		//closing the model dialog pop which comes on page load
		
		//cdriver.findElementByXPath("//p[text()=' Login or Create Account']").click();
		cdriver.findElementByXPath("//div[@data-cy='outsideModal']").click();
		Thread.sleep(2000);
		
		//clicking on Hotels
		WebElement hotels = cdriver.findElementByXPath("//span[text()='Hotels']");
		hotels.click();
		
		//Entering City
		WebElement city = cdriver.findElementByXPath("//input[@data-cy='city']");
		city.click();
		cdriver.findElementByXPath("//input[contains(@placeholder,'Enter')]").sendKeys("Goa");
		Thread.sleep(1000);
		cdriver.findElementByXPath("//p[text()='Goa, India']").click();
		
		//Selecting Checkin and Checkout Dates
		cdriver.findElementByXPath("//input[@data-cy='checkin']").click();
		cdriver.findElementByXPath("(//div[text()='15'])[1]").click();
		cdriver.findElementByXPath("(//div[text()='19'])[1]").click();

		//Selecting Rooms and Guests
		cdriver.findElementByXPath("//input[@id='guest']").click();
		cdriver.findElementByXPath("(//li[text()='2'])[1]").click();
		cdriver.findElementByXPath("(//li[text()='1'])[2]").click();
		
		WebElement childAge = cdriver.findElementByXPath("//select[@class='ageSelectBox']");
		Select dropDown = new Select(childAge);
		dropDown.selectByIndex(11);
		
		//clicking apply button
		cdriver.findElementByXPath("//button[@data-cy='submitGuest']").click();
		
		//searching as per filter
		cdriver.findElementById("hsw_search_button").click();
		
		
		//Opening the map and closing it as it gets overlayed on page post search action.
		cdriver.findElementByClassName("mapCont").click();
		//xpath = //span[@class='mapClose']
		cdriver.findElementByClassName("mapClose").click();
		
		//applying the fitler options
		cdriver.findElementByXPath("//label[text()='Baga']").click();
		Thread.sleep(1000);
		cdriver.findElementByXPath("//label[contains(text(),'3 &')]").click();
		cdriver.findElementByXPath("//span[text()='Popularity']").click();
		cdriver.findElementByXPath("//li[contains(text(),'Low to High')]").click();
	
		//clicking on the second resulting hotel
		cdriver.findElementByXPath("(//p[text()='Ratings'])[2]").click();
		
		//Switching to next window
		String windowHandle = cdriver.getWindowHandle();
		Set<String> windowHandles = cdriver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		
		// Getting the second window
		String secondWindow = listHandles.get(1);
		
		// moving the control to second Window
		cdriver.switchTo().window(secondWindow);
		
		//Inorder to avoid the access denied error we are clearing the cookies and refreshing the browser
		cdriver.manage().deleteAllCookies();
		cdriver.navigate().refresh();
		
		//Getting the Hotel Name to be printed	
		WebElement hotelName = cdriver.findElementByXPath("(//ul[@id='detpg_bread_crumbs']//li)[3]");
		
		System.out.println("Hotel Name is: "+hotelName.getText());
		
		//clicking View this combo and book this combo buttons
		cdriver.findElementById("detpg_multi_view_combo").click();
		Thread.sleep(1000);
		cdriver.findElementById("detpg_book_combo_btn").click();
				
		Thread.sleep(3000); // sleeping inorder to get the discounted price
		
		//getting the total payable amount and pri
		WebElement totalPay = cdriver.findElementById("revpg_total_payable_amt");
		System.out.println("Total Amount is: "+totalPay.getText());
		
		//closing the open browser windows
		cdriver.close();
		cdriver.switchTo().window(listHandles.get(0));
		cdriver.close();

		
	}

}
