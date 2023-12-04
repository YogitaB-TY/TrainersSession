package genericLibrary;
/**
 * 
 * @author Yogita
 * @implNote This class is responsible for the preconditions that is common throughout 
 * the application
 * Ex: Browser open and close
 *
 */

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericLibrary.UtilityMethods;

public class BaseTest extends UtilityMethods{
	
	public WebDriver driver;
	
	@Parameters("browserName")
	@BeforeClass(alwaysRun = true )
	public void browserSetup(@Optional("chrome") String browser) throws IOException {
		if(browser.equalsIgnoreCase("chrome")) {
		driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver=new FirefoxDriver();
		}else {
			System.out.println("Browser name is not valid");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(getPropertyValue("url"));
	}
	
	@AfterClass(alwaysRun = true)
	public void browserTearDown() {
		driver.quit();
	}
}
