import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Supreme {
	String baseUrl = "http://www.supremenewyork.com/shop/all";
	WebDriver chrome;

	Supreme(WebDriver driver) {
		chrome = driver;
	}
	
	public void accessSite()
	{
		chrome.get(baseUrl);
	}
	
	public WebElement findProduct(String keyword, String category)
	{
		// Find category
		WebElement categoryElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@href, '" + category + "')]")));
		categoryElement.click();
		System.out.println("Found category: " + category);
		
		// Find item in the catelog
		WebElement itemElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(keyword)));
		
		System.out.println("Found item. Product Link: " + itemElement.getAttribute("href"));
		return itemElement;
	}
	
	public void selectSize(String size) {
		Select dropDown = null;
		// Find the select element
		WebElement formElement = (new WebDriverWait(chrome, 20)).
			until(ExpectedConditions.presenceOfElementLocated(By.id("details")));
		List<WebElement> allFormChildElements = formElement.findElements(By.xpath("*"));
		for(WebElement item : allFormChildElements )
		{
			if(item.getAttribute("id").equals("cctrl"))
			{
				//System.out.println("Found cctrl");
				WebElement sizeElement = item.findElement(By.id("size"));
				dropDown = new Select(sizeElement);
				System.out.println("Product ID: " + dropDown.getFirstSelectedOption().getAttribute("value"));
				dropDown.selectByVisibleText(size);
				System.out.println("Selected size: " + size);
			}
		}
	}
	
	public boolean addToCart()
	{
		// Add to cart
		WebElement addToCart = (new WebDriverWait(chrome, 20)).
			until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@value, 'add to cart')]")));
		addToCart.click();
		System.out.println("Successfully added to cart.");
		return true;
		// TODO: Find different product
	}
	
	public void goToCheckOut() {
		// TODO: US/CANADA, what province, info from file
		String name = "KAY TEE";
		String email ="ktt@gmail.com";
		String tel = "9051112222";
		
		String address ="101 Sesame Street";
		String zip = "L4L4L4";
		String city = "Saugacity";
		String state = "ON";
		String country = "CANADA";
		
		String card = "Mastercard";
		String cardNumber = "1111222233334444";
		String cardMonth = "09";
		String cardYear = "2018";
		String cardCVV = "135";
		
		WebElement checkoutElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("checkout now")));
		checkoutElement.click();
		/*
		 * Order: Name -> Email -> Tel -> Address -> Select Country -> Zip -> Province -> City -> Credit Type -> Number -> Exp. Date - > CVV
		 */
		WebElement nameElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_billing_name')]")));
		nameElement.sendKeys(name);
		
		WebElement emailElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_email')]")));
		emailElement.sendKeys(email);
		
		WebElement telElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_tel')]")));
		telElement.sendKeys(tel);
		
		WebElement addressElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@name, 'order[billing_address]')]")));
		addressElement.sendKeys(address);
		
		Select countrySelection = new Select(chrome.findElement(By.id("order_billing_country")));
		countrySelection.selectByVisibleText(country);
		
		Select stateSelection = new Select(chrome.findElement(By.id("order_billing_state")));
		stateSelection.selectByVisibleText(state);
		
		WebElement cityElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_billing_city')]")));
		cityElement.sendKeys(city);
		
		WebElement zipElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_billing_zip')]")));
		zipElement.sendKeys(zip);
		
		Select cardSelection = new Select(chrome.findElement(By.id("credit_card_type")));
		cardSelection.selectByVisibleText(card);
		
		WebElement cardNumberElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@name, 'credit_card[cnb]')]")));
		cardNumberElement.sendKeys(cardNumber);
		
		Select cardYearSelection = new Select(chrome.findElement(By.id("credit_card_month")));
		cardYearSelection.selectByVisibleText(cardMonth);
		
		Select cardMonthSelection = new Select(chrome.findElement(By.id("credit_card_year")));
		cardMonthSelection.selectByVisibleText(cardYear);
		
		WebElement cardCVVElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@name, 'credit_card[vval]')]")));
		cardCVVElement.sendKeys(cardCVV);
		
		WebElement agreeElement = (new WebDriverWait(chrome, 20)).
		        until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@id, 'order_terms')]")));
		agreeElement.sendKeys(Keys.SPACE);
		agreeElement.sendKeys(Keys.ENTER);
	}
}
