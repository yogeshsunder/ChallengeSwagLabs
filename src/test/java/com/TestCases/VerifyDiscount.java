package com.TestCases;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.Pages.AgentLoginPage;

import Helper.BrowserFactory;
import Helper.Utility;

public class VerifyDiscount {
	
	WebDriver driver;
	String wUserName, cpassword, cUsername, wPassword;
	
	JSONParser parser=new JSONParser();
	
	
	//--------------------------------Verify Discount------------------------------------------
	@Test
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