package saifulsahim.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saifulsahim.AbstractComponents.AbstractComponent;

public class ConfirmationMessage extends AbstractComponent{

	WebDriver driver;

	public ConfirmationMessage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(css=".hero-primary")
	WebElement message;
	
	public String getConfirmationMessage()
	{
		return message.getText();
	}

}
