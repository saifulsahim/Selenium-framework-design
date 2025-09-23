package saifulsahim.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import saifulsahim.SeleniumFrameworkDesign.pageobjects.CartPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ConfirmationMessage;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import saifulsahim.TestComponents.BaseTest;

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
