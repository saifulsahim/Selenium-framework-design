package saifulsahim.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class StandAloneTest {

	public static void main(String[] args) {
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); // Instead of setting WebDriver locally, it is managed through the WebDriverManager Maven repository.
		
		// to prevent google password prompt popup below these two lines added
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito"); 

		WebDriver driver = new ChromeDriver(options);
		
		//WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("saifulsahim@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sahim123#");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		// wait for the products list to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 


		// get all products list
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		// Using java 8 stream i.e kind of loop 
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		// click last button out of 2 buttons (View, add to cart)
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); 
		
		// waiting to load Toast "added to cart"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); 
		/*
		 * Waiting for the animation class to disappear by passing the complete
		 * WebElement. Using invisibilityOf instead of visibilityOfElementLocated to improve performance.
		 */	
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); 
		// Using custom css selector [attribute = 'value'] and also using regular expression
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); // xpath using css class //*[@class='cartSection']
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		// Just trying actions instead of sendKeys
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		// After searched items, click 2nd which is india
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		 
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
	
	

}
