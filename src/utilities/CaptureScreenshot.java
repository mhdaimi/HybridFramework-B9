package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {
	
	public static void asFile(WebDriver browser, String fileName) {
		TakesScreenshot ss = (TakesScreenshot) browser;
		File temp = ss.getScreenshotAs(OutputType.FILE);
		String filePath = "C:\\Data\\Java-Selenium\\HybridFramework\\src\\screenShots\\" + fileName + ".jpg";
		try {
			FileUtils.copyFile(temp, new File(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
