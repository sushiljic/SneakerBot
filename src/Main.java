import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
	static WebDriver chrome; 
	static String baseUrl = "http://www.supremenewyork.com/";

	public static void main(String[] args) throws Exception {
		ChromeOptions op = new ChromeOptions();
	    op.addExtensions(new File("Block-image_v1.1.crx"));
		chrome = new ChromeDriver(op);
		Supreme supremeStack = new Supreme(chrome);
		supremeStack.accessSite();
		/*
		 * 
		 * Categories
		 *  jackets
		 *  shirts
		 *  tops_sweaters
		 *  sweatshirts
		 *  pants
		 *  t-shirts
		 *  hats
		 *  bags
		 *  accessories
		 *  shoes
		 *  skate
		 *  
		 */
		WebElement product = supremeStack.findProduct("Royal", "tops_sweaters");
		product.click();
		/*
		 * 
		 * Small
		 * Medium
		 * Large
		 * XLarge
		 * 
		 */
		supremeStack.selectSize("XLarge");
		supremeStack.addToCart();
		supremeStack.goToCheckOut();
		//chrome.get(baseUrl);
		//login("erick.chin", "chin");

		System.exit(0);
	}

	public static void login(String username, String password) {
		chrome.findElement(By.name("ctl00$ContentPlaceHolder1$txtUsername")).sendKeys(username);
		chrome.findElement(By.name("ctl00$ContentPlaceHolder1$txtPassword")).sendKeys(password);
		chrome.findElement(By.name("ctl00$ContentPlaceHolder1$btnLogin")).click();
	}
}
