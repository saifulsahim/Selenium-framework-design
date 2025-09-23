package saifulsahim.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import saifulsahim.TestComponents.BaseTest;

public class ErrorValidation extends BaseTest{
	
	@Test
	public void submitOrder() throws IOException, InterruptedException {

		String productName = "ZARA COAT 3";
		//LandingPage landingPage = launchApplication();
		landingPage.loginApplication("saifulsahim@gmail.com", "aahim123#");
		//div[@aria-label='Incorrect email or password.']
		Assert.assertEquals("Incorrect email or passsword.", landingPage.getErrorMessage());
		
	}

}
