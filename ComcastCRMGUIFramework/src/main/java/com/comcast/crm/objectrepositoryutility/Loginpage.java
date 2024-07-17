package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class Loginpage extends WebDriverUtility{ //Rule-1 create a separate java class
				
	//Rule-2 Object creation
	WebDriver driver;
	public Loginpage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy (name="user_name") 
	private WebElement usernameEdt;
	
	@FindBy (name="user_password") 
	private WebElement passwordEdt;
	
	@FindBy (id="submitButton")
	private WebElement loginBtn;
						//Rule 3 : Object Initialization
	//Rule 4 : Object Encapsulation

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}
	
	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	//Rule 5 : We can provide Action
	public void loginToapp(String url,String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}


}
