package com.TestCases;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Pages.AgentLoginPage;

import Helper.BrowserFactory;
import Helper.Utility;

public class VerifyLogin {
	
	WebDriver driver;
	String wUserName, cpassword, cUsername, wPassword;
	
	JSONParser parser=new JSONParser();
	
	
	//--------------------------------Check Agent Login with User name blank------------------------------------------------------------
	@Test (priority=1)
	public void verifyLogin_usernameBlank() throws Exception
	{
		driver=BrowserFactory.startBrowser("chrome", "url");
		
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		
		agentlogin.clickLogOn();
		
		String userNameValidation = agentlogin.getValidationUsername();
		
		if(userNameValidation.contains("Username is required"))
			{
				System.out.println("Agent is able to get the correct validation error message when click on Logon with keeping user name blank.");
			}
		else
			{
				System.out.println("Agent is not getting correct validation error message when click on Logon with keeping user name blank.");
			}
	}
		
		
	//--------------------------------Check Agent Login with password field blank--------------------------------------------------------------
	@Test (priority=2)
	public void verifyLogin_pwdBlank() throws Exception
	{	
		driver=BrowserFactory.startBrowser("chrome", "url");
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
		JSONObject jsonObject = (JSONObject) obj;
		wUserName = (String) jsonObject.get("Wrong UserName");
		
		agentlogin.enterUsername(wUserName);
		
		agentlogin.clickLogOn();
		
		String passwordValidation = agentlogin.getValidationUsername();
		
		if(passwordValidation.contains("Password is required"))
			{
				System.out.println("Agent is able to get the correct validation error message when click on Logon with keeping password blank.");
			}
		else
			{
				System.out.println("Agent is not getting correct validation error message when click on Logon with keeping password blank.");
			}

		
	}
		
		//--------------------------------Check Agent Login with wrong User name--------------------------------------------------------------
	@Test (priority=3)
	public void verifyLogin_wronguser() throws Exception
	{
		driver=BrowserFactory.startBrowser("chrome", "url");
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
		JSONObject jsonObject = (JSONObject) obj;
		cpassword = (String) jsonObject.get("Correct Password");
		
		agentlogin.enterPassword(cpassword);

		agentlogin.clickLogOn();
		
		String wrongUsernameValidation = agentlogin.getMainValidation();
		
		if(wrongUsernameValidation.contains("Username and password do not match"))
			{
				System.out.println("Agent is able to get the correct validation error message when click on Logon with entering wrong username.");
			}
		else
			{
				System.out.println("Agent is not getting correct validation error message when click on Logon with entering wrong username.");
			}
	}
		
		//--------------------------------Check Agent Login with wrong password--------------------------------------------------------------
	@Test (priority=4)
	public void verifyLogin_wrongpwd() throws Exception
	{	
		driver=BrowserFactory.startBrowser("chrome", "url");
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
		JSONObject jsonObject = (JSONObject) obj;
		
		cUsername = (String) jsonObject.get("Correct UserName");
		wPassword = (String) jsonObject.get("Wrong Password");
		
		agentlogin.enterUsername(cUsername);
		
		agentlogin.enterPassword(wPassword);		
		
		agentlogin.clickLogOn();
		
		String wrongPasswordValidation = agentlogin.getValidationUsername();
		
		if(wrongPasswordValidation.contains("Username and password do not match"))
			{
				System.out.println("Agent is able to get the correct validation error message when click on Logon with entering wrong password.");
			}
		else
			{
				System.out.println("Agent is not getting correct validation error message when click on Logon with entering wrong password.");
			}
	}
		
		//--------------------------------Check Agent Login with correct username and password--------------------------------------------------------------
	@Test (priority=5)
	public void verifyLogin_correct() throws Exception
	{	
		driver=BrowserFactory.startBrowser("chrome", "url");
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
		JSONObject jsonObject = (JSONObject) obj;	
		
		cUsername = (String) jsonObject.get("Correct UserName");
		cpassword = (String) jsonObject.get("Correct Password");
		
		agentlogin.enterUsername(cUsername);
		
		agentlogin.enterPassword(cpassword);
		
		agentlogin.clickLogOn();
		
		Thread.sleep(5000);
		
		String loggedIn_title = agentlogin.getTitle();
		
		if(loggedIn_title.contains("Products"))
			{
				System.out.println("Agent is not able to login.");
			}
		else
			{
				System.out.println("Agent is able to login successfully.");
			}
	}
		
	//--------------------------------Failing login to get the screen shot with wrong username and password------------------------------------------
	@Test (priority=6)
	public void verifyLogin_invalid() throws Exception
	{	
		driver=BrowserFactory.startBrowser("chrome", "url");
		AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
		JSONObject jsonObject = (JSONObject) obj;
		
		cUsername = (String) jsonObject.get("Correct UserName");
		wPassword = (String) jsonObject.get("Wrong Password");
		
		agentlogin.enterUsername(cUsername);
		
		agentlogin.enterPassword(wPassword);
		
		agentlogin.clickLogOn();
		
		String loggedIn_title = agentlogin.getTitle();
		
		String ExpectedTitle = "Products";
		
		Assert.assertEquals(ExpectedTitle, loggedIn_title);
	}
	
	//--------------------------------Verify Discount------------------------------------------
		@Test (priority=6)
		public void verifyDiscount() throws Exception
		{	
			driver=BrowserFactory.startBrowser("chrome", "url");
			AgentLoginPage agentlogin=PageFactory.initElements(driver, AgentLoginPage.class);
			
			Object obj = parser.parse(new FileReader("src/test/java/JSONData/loginInputData.json"));
			JSONObject jsonObject = (JSONObject) obj;
			
			cUsername = (String) jsonObject.get("Correct UserName");
			cpassword = (String) jsonObject.get("Correct Password");
			
			agentlogin.enterUsername(cUsername);
			
			agentlogin.enterPassword(cpassword);
			
			agentlogin.clickLogOn();
		}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		//
		//If test is getting failed then take the screen shot and put it in Automation Report.
		//
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path=Utility.captureScreenshot(driver, result.getName());
		}
		//
		//Close the browser
		//
		driver.close();
	}
}