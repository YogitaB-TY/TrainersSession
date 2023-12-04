package com.pom.repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage {
	
	public WelcomePage(WebDriver driver) {
		/*
		 * initElements() is responsible to initialize the webElements that are identified 
		 * in a page
		 * (searchContext, Object page)
		 * to point to the current reference that is the current page, we use this keyword
		 * 
		 */
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(partialLinkText = "Register")
	public WebElement registerLink;
	
	public void clickRegisterLink() {
		registerLink.click();
	}

	@FindBy(partialLinkText = "Log in")
	WebElement loginLink;
	
	public void clickLoginLink() {
		loginLink.click();
	}
}
