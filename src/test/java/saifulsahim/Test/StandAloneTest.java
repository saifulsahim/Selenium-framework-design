package saifulsahim.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.CartPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ConfirmationMessage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.LandingPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import saifulsahim.TestComponents.BaseTest;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest extends BaseTest{
	
	@Test
	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
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
