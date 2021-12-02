/**
 * 
 */
package Helper;

import java.io.File;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Yogesh
 *
 */
public class Utility {
	
	public static String captureScreenshot(WebDriver driver, String screenshotName)
	{
		try 
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			File source=ts.getScreenshotAs(OutputType.FILE);
			
			//String dest="C:\\Users\\dell\\Yogesh\\Projects\\challenge_swaglabs\\BugError\\"+screenshotName+".png";
			String dest="BugError\\"+screenshotName+".png";
			
			File destination=new File(dest);
			
			FileUtils.copyFile(source, destination);
			
			System.out.println("Screenshot taken");
			
			return dest;
		} 
		catch (Exception e) 
		{
			
			System.out.println("Exception while taking screen shot "+e.getMessage());
			return e.getMessage(); 
		}
		
		
	}

}