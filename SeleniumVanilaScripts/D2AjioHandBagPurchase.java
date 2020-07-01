package SeleniumVanilaScripts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D2AjioHandBagPurchase {

	public static void main(String[] args) throws InterruptedException 
	{
	
		/*  
			1) Go to https://www.ajio.com/shop/sale
			2) Enter Bags in the Search field and Select Bags in Women Handbags
			3) Click on five grid and Select SORT BY as "What's New"
			4) Enter Price Range Min as 2500 and Max as 5000
			5) Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
			6) Verify the Coupon code for the price above 2890 is applicable for your product, if applicable then get the Coupon Code and the discount price for the coupon
			7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
			8) Click on Other Informations under Product Details and Print the Customer Care address, phone and email
			9) Click on ADD TO BAG and then GO TO BAG
			10) Check the Order Total before apply coupon
			11) Enter Coupon Code and Click Apply
			12) Verify the discount price matches with the product price
			13) Click on Delete and Delete the item from Bag
			14) Close all the browsers
		*/  
		
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver cdriver = new ChromeDriver();
		
		// disable notifications
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//Get URL
		cdriver.get("https://www.ajio.com/shop/sale");
		cdriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		cdriver.manage().window().maximize();
		//cdriver.manage().deleteAllCookies();
		//Thread.sleep(2000);
		String couponCode="";
		String discountPrice = "";
		cdriver.findElementByXPath("//input[@name='searchVal']").clear();
		cdriver.findElementByXPath("//input[@name='searchVal']").sendKeys("Bags");
		cdriver.findElementByXPath("(//span[text()='Women Handbags'])[1]").click();
		cdriver.findElementByClassName("five-grid").click();
		
		WebElement sortBy = cdriver.findElementByXPath("//div[@class='filter-dropdown']//select[1]");
		
		Select sortByDropDown = new Select(sortBy);
		sortByDropDown.selectByVisibleText("What's New");
		cdriver.navigate().refresh();
		
//		WebDriverWait ewait = new WebDriverWait(cdriver, 30);
//		ewait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='price']"))).click();
		
		Thread.sleep(2000);
		//cdriver.findElementByXPath("//span[text()='price']").click();
		//cdriver.findElementByClassName("facet-head ").click();
		cdriver.findElementByXPath("(//div[@class='facet-head '])[1]").click();
		cdriver.findElementById("minPrice").sendKeys("2500");
		cdriver.findElementById("maxPrice").sendKeys("5000");
		cdriver.findElementByXPath("//button[@class='rilrtl-button ic-toparw']").click();
		
		Thread.sleep(2000);
		cdriver.findElementByXPath("//div[text()='Sling Bag with Chain Strap']").click();
		Thread.sleep(1000);
		//Switching to next window
		String windowHandle = cdriver.getWindowHandle();
		Set<String> windowHandles = cdriver.getWindowHandles();
		List<String> listHandles = new ArrayList<String>(windowHandles);
		
		// Getting the second window
		String secondWindow = listHandles.get(1);
				
		// moving the control to second Window
		cdriver.switchTo().window(secondWindow);
				
		
		String promoCheck = cdriver.findElementByClassName("promo-desc").getText();
		System.out.println("Promo Check text: "+promoCheck);
		
		if(promoCheck.contains("2890"))
		{
			String promoCode = cdriver.findElementByClassName("promo-title").getText();
			System.out.println(promoCode);
			
			//Getting the last word from the promoCode String
			//int lastSpacePosition = promoCode.lastIndexOf(" ");
			couponCode = promoCode.substring(promoCode.lastIndexOf("e")+1);
			System.out.println("Coupon Code is: "+couponCode);
			
			String str = cdriver.findElementByXPath("//div[@class='promo-discounted-price']//span[1]").getText();
			discountPrice = str.substring(1);
			System.out.println("Displayed Discount Price is:"+discountPrice);
			
		}
		else
		{
			System.out.println("This item values is not above 2890 and so no promocode option is avaiblale.");
		}
		
		Thread.sleep(1000);
		//Clicking other information to check pincode delivery status
		String pincode= "560043";
		cdriver.findElementByXPath("(//span[contains(@class,'edd-pinco')])[2]").click();
		cdriver.findElementByClassName("edd-pincode-modal-text").sendKeys(pincode);
		cdriver.findElementByClassName("edd-pincode-modal-submit-btn").click();
		if(cdriver.findElementByClassName("edd-message-success").isDisplayed())
		{
			System.out.println("Delivery is available for the pincode: "+pincode);
		}
		else
		{
			System.out.println("Delivery is NOT AVAILABLE for the pincode: "+pincode);
		}
		
		
		cdriver.findElementByClassName("other-info-toggle").click();
		String customerCareAddress= cdriver.findElementByXPath("(//span[@class='other-info'])[7]").getText();
		System.out.println("Communication Address is: "+customerCareAddress);
		
		cdriver.findElementByClassName("ic-pdp-add-cart").click();
		Thread.sleep(9000);
		
		//Getting into the Cart
		//pdp-addtocart-button
		WebElement goToCart = cdriver.findElementByXPath("//div[@class='btn-cart']");
//		WebDriverWait wait=new WebDriverWait(cdriver,15);
//		wait.until(ExpectedConditions.;
		goToCart.click();
		
		Thread.sleep(2000);
		String nonDiscountedPrice = cdriver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
		System.out.println("Non Discounted Price is: "+nonDiscountedPrice);
		
		//Entering Coupon code and CLicking Apply
		cdriver.findElementById("couponCodeInput").sendKeys(couponCode);
		cdriver.findElementByXPath("//button[text()='Apply']").click();
	
		Thread.sleep(3000);
		String string = cdriver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
//		System.out.println("Discounted Price is: "+string);
		String string1 = string.replace(",", "");
		String discountedPrice = string1.substring(4,8);
		System.out.println("Discounted Price is:"+discountedPrice);
		
		
		if(discountPrice.contentEquals(discountedPrice))
		{
			System.out.println("Initial discount price matches with the final discount product price");
		}
		else
		{
			System.out.println("Initial discount price DOES NOT with the final discount product price");
		}
	
		//closing the open browser windows
		cdriver.close();
		cdriver.switchTo().window(listHandles.get(0));
		cdriver.close();

		
	}

}
