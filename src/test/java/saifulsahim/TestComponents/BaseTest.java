package saifulsahim.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import saifulsahim.SeleniumFrameworkDesign.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;

	public WebDriver initializeDriver() throws IOException
	{
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\saifulsahim\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			/*
			 *Instead of setting WebDriver locally, it is managed through the WebDriverManager Maven repository.
			 */			
			WebDriverManager.chromedriver().setup();
			// to prevent google password prompt popup below these two lines added
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
}
