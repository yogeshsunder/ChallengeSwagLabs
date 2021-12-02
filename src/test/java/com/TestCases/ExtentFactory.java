/**
 * 
 */
package com.TestCases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.relevantcodes.extentreports.ExtentReports;

/**
 * @author Yogesh
 *
 */
public class ExtentFactory {
	
	static JSONParser parser=new JSONParser();
	static String operatingSystem;
	
	public static ExtentReports getInstance() throws Exception, IOException, ParseException
	{
		ExtentReports extent;
		//String Path = "Report//InsuranceGuru_AutomationJuly17.html";
		String Path = "C://Users//dell//Yogesh//challenge_swaglabs.html";
		//extent = new ExtentReports(Path, true);
		extent = new ExtentReports(Path, false);
		
		Object obj1 = parser.parse(new FileReader("src/test/java/JSONData/operatingSystem.json"));
		JSONObject jsonObject1 = (JSONObject) obj1;
		operatingSystem = (String) jsonObject1.get("Operating System");
		
		if(operatingSystem.equals("windows"))
		{
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		}
		else if(operatingSystem.equals("linux"))
		{
			extent.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		}
		
		extent.addSystemInfo("User Name", "Yogesh Kumar");
		extent.addSystemInfo("Host Name", "Agile Soft Systems QC Team");
		extent.addSystemInfo("Version of InsuranceGuru", "0.0.1");
		
		return extent;
		
	}

}