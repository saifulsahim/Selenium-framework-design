package saifulsahim.SeleniumFrameworkDesign;

import io.github.bonigarcia.wdm.WebDriverManager;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.CartPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ConfirmationMessage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.LandingPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;

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

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); // Instead of setting WebDriver locally, it is managed through the
													// WebDriverManager Maven repository.

		// to prevent google password prompt popup below these two lines added
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		WebDriver driver = new ChromeDriver(options);

		// WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication("saifulsahim@gmail.com", "Sahim123#");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		
		ConfirmationMessage getConfirmationMessage= checkOutPage.submitOrder();
		String confirmMessage = getConfirmationMessage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

}
