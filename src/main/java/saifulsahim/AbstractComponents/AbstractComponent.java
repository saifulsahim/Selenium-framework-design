package saifulsahim.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import saifulsahim.SeleniumFrameworkDesign.pageobjects.CartPage;

public class AbstractComponent {  
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		// wait for the products list to load
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 
	}
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		// wait for the products list to load
		wait.until(ExpectedConditions.visibilityOf(findBy)); 
	}
	
	public CartPage goToCartPage()
	{
		// Using custom css selector [attribute = 'value'] and also using regular expression
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		// 4 seconds 
		Thread.sleep(1000);
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(ele));
		 */

	}


}
