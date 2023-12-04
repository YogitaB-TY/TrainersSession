package com.testScripts;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pom.repo.LoginPage;
import com.pom.repo.WelcomePage;

public class LearningExtentReports  {

	static WebDriver driver ;
	public static void main(String[] args) {
		/* ExtentSparkReporter: in object creation, give path of where report must be stored*/
		ExtentSparkReporter reporter=new ExtentSparkReporter("./Report/ExtentReport.html");
		/*responsible to start reporting in the html file 
		 * attachReporter() is responsible to attach whatever details to the file*/
		ExtentReports report=new ExtentReports();
		report.attachReporter(reporter);
		ExtentTest test= report.createTest("Login");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://demowebshop.tricentis.com/");
		test.log(Status.INFO, "Browser is open");
		WelcomePage welcomePage = new WelcomePage(driver);
		welcomePage.clickLoginLink();
		test.log(Status.INFO, "Login page is displayed");
		LoginPage loginPage = new LoginPage(driver); String userName = "yogbelavanaki@gmail.com";
		loginPage.enterEmail(userName); String password = "Password@13";
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		try {
			if (driver.findElement(By.partialLinkText("Log out")).isDisplayed()) {
				System.out.println("PASS: User is logged in");
				test.log(Status.PASS, "User is logged in");
			}
		} catch (Exception e) {
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshot()).build());
			System.out.println("FAIL: User is not logged in");
			test.log(Status.FAIL, "User is not logged in");		
		}
		//driver.findElement(By.partialLinkText("Log out")).click();
		/* flush() is responsible to create new report. If not used, report wont be generated
		 * */
		report.flush();
	}
	public static String screenshot() {
		LocalDateTime dateTime=LocalDateTime.now();
		String name=dateTime.toString().replaceAll(":", "-");
		TakesScreenshot ts=(TakesScreenshot) driver;
		String path="./Screenshots/"+name+".png";
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(path);
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//../Screenshots/name.png
		return "."+path; 
	}

}
