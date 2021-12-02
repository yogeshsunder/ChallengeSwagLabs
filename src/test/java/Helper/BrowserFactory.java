/**
 * 
 */
package Helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * @author Yogesh
 *
 */
public class BrowserFactory {
	
	static WebDriver driver;
	String url;
    static JSONParser parser=new JSONParser();
    
    static String operatingSystem;
	
	public static WebDriver startBrowser(String browserName,String url) throws FileNotFoundException, IOException, ParseException
	{
		
		Object obj1 = parser.parse(new FileReader("src/test/java/JSONData/operatingSystem.json"));
		JSONObject jsonObject1 = (JSONObject) obj1;
		operatingSystem = (String) jsonObject1.get("Operating System");
		
		if(operatingSystem.equals("windows"))
			{
				if(browserName.equals("firefox"))
					{
						System.setProperty("webdriver.gecko.driver", "src/test/java/Resources/geckodriver.exe");
						driver=new FirefoxDriver();
					}
				else if(browserName.equals("chrome"))
					{
						System.setProperty("webdriver.chrome.driver", "src/test/java/Resources/chromedriver.exe");
						driver=new ChromeDriver();
						//ChromeOptions opt = new ChromeOptions();
						//opt.setHeadless(true);
						//driver = new ChromeDriver(opt);
					}		
				else if (browserName.equals("EdgeDriver"))
					{
						System.setProperty("webdriver.edge.driver", "src/test/java/Resources/MicrosoftWebDriver.exe");
						driver = new EdgeDriver();
					}
				//else if (browserName.equals("IE"))
					//{
						//System.setProperty("webdriver.IE.driver", "IEDriverServer.exe");
						//driver=new InternetExplorerDriver();
					//}
			}
		else if(operatingSystem.equals("linux"))
			{
				if(browserName.equals("firefox"))
				 	{
						System.setProperty("webdriver.gecko.driver", "src\\test\\java\\Resources\\geckodriver.exe");
						driver=new FirefoxDriver();
				 	}
				else if(browserName.equals("chrome"))
					{
						System.setProperty("webdriver.chrome.driver", "src/test/java/Resources/chromedriver");
						
						ChromeOptions ChromeOptions = new ChromeOptions();
						ChromeOptions.addArguments("--headless", "window-size=1024,768", "--no-sandbox");
						driver = new ChromeDriver(ChromeOptions);
						
						//ChromeOptions opt = new ChromeOptions();
						///opt.setHeadless(true);
						//driver = new ChromeDriver(opt);
					}		
				else if (browserName.equals("EdgeDriver"))
					{
						System.setProperty("webdriver.edge.driver", "src\\test\\java\\Resources\\MicrosoftWebDriver.exe");
						driver = new EdgeDriver();
					}
				//else if (browserName.equals("IE"))
					//{
						//System.setProperty("webdriver.IE.driver", "IEDriverServer.exe");
						//driver=new InternetExplorerDriver();
					//}			
		}
		
		driver.manage().window().maximize();
		
		Object obj = parser.parse(new FileReader("src/test/java/JSONData/urlforsite.json"));
		JSONObject jsonObject = (JSONObject) obj;
		 url = (String) jsonObject.get("URL");
		 
		driver.get(url);
		return driver;
	}
	

}