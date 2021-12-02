package com.Pages;

import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AgentLoginPage {
	
	WebDriver driver;
	String username,password;
    JSONParser parser=new JSONParser();
	
	public AgentLoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private void waitForVisibility(WebElement element) throws Error
	{
           new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }
	
	//
	//WebElement of user name option of the login page
	//
	@FindBy(how=How.ID,using="user-name")
	WebElement uname;
	
	//
	//Function of entering username on login page
	//
	public void enterUsername(String username)
	{
		waitForVisibility(uname);
		uname.clear();
		uname.sendKeys(username);
	}
	
	//
	//WebElement of user name option of the login page
	//
	@FindBy(how=How.ID,using="password")
	WebElement pwd;
	
	//
	//Function of entering username on login page
	//
	public void enterPassword(String password)
	{
		waitForVisibility(pwd);
		pwd.clear();
		pwd.sendKeys(password);
	}
	
	//
	//WebElement of Log On button of the login page
	//
	@FindBy(how=How.ID,using="login-button")
	WebElement login;
	
	//
	//Function of clicking on Log On button on login page
	//
	public void clickLogOn()
	{
		waitForVisibility(login);
		login.click();
	}
	
	//
	//Function of Agent Login
	//
	public void agentLogin(String username, String password)
	{
		waitForVisibility(uname);
		uname.sendKeys(username);
		pwd.sendKeys(password);
		login.click();
		
	}
	
	//
	//WebElement is for validation error message on login form
	//
	@FindBy(how=How.CSS,using=".error-message-container")
	WebElement validationLogin;
	
	//
	//Function for getting validation error message on login form
	//
	public String getValidationUsername()
	{
		return validationLogin.getText();
	}
	
	//
	//WebElement is for main validation error message on login form
	//
	@FindBy(how=How.CSS,using=".error-message-container")
	WebElement validationMain;
	
	//
	//Function for getting validation error message on login form
	//
	public String getMainValidation()
	{
		return validationMain.getText();
	}
	
	//
	//WebElement to get title of the page
	//
	@FindBy(how=How.CSS,using=".title")
	WebElement titlePage;
	
	//
	//Function to get title of the page
	//
	public String getTitle()
	{
		return titlePage.getText();
	}
	
	//
	//WebElement for menu option
	//
	@FindBy(how=How.ID,using="react-burger-menu-btn")
	WebElement menu_option;
	
	//
	//Function to click on menu option
	//
	public void clickMenuOption()
	{
		waitForVisibility(menu_option);
		menu_option.click();
	}
	
	//
	//WebElement for Logout
	//
	@FindBy(how=How.ID,using="logout_sidebar_link")
	WebElement logout;
	
	//
	//Function to click on logout
	//
	public void clicklogout()
	{
		waitForVisibility(logout);
		logout.click();
	}
	
}
