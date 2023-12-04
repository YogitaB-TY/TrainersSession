package com.testScripts;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pom.repo.LoginPage;
import com.pom.repo.WelcomePage;

import genericLibrary.BaseTest;



public class LoginTest extends BaseTest {
	
	@Test(dataProvider = "login")
	public void loginTest(String userName, String password) {
		
		//driver.findElement(By.partialLinkText("Log in")).click();
		WelcomePage welcomePage=new WelcomePage(driver);
		//welcomePage.registerLink.click();
		welcomePage.clickLoginLink();
		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmail(userName);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		try {
			if(driver.findElement(By.partialLinkText("Log out")).isDisplayed()) {
				System.out.println("PASS: User is logged in");
			}
		}catch (Exception e) {
			System.out.println("FAIL: User is not logged in");
		}
		driver.findElement(By.partialLinkText("Log out")).click();
	}
	
	@DataProvider(name = "login")
	public Object[][] testData() {
		
		int row_size=2;
		int col_size=2;
		
		Object[][] data=new Object[row_size][col_size];
		data[0][0]="yogbelavanaki@gmail.com";
		data[0][1]="Password@123";
		
		data[1][0]="yogita.b@testyantra.com";
		data[1][1]="Password@123";				
		return data;
	}
}
