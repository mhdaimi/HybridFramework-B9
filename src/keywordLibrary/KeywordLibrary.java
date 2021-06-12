package keywordLibrary;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilities.CaptureScreenshot;

public class KeywordLibrary {
	
	public static WebDriver browser;
	public static String parentWindow;
	
	public static void processor(String methodName, String arg1, String arg2, String arg3, String testCaseName, 
			int rowNumber) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class cls = KeywordLibrary.class;
		Method m = cls.getDeclaredMethod(methodName, String.class, String.class, String.class);
		try {
			m.invoke(cls, arg1, arg2, arg3);
		}
		catch (Exception e) {
			CaptureScreenshot.asFile(browser, testCaseName+"_"+rowNumber);
			System.exit(1);
		}
	}
	
	
	public static void launchBrowser(String arg1, String arg2, String arg3) throws Exception {
		if(arg1.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Data\\Jars-Exe\\chromedriver_win32\\chromedriver.exe");
			browser = new ChromeDriver();	
		}
		else if(arg1.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Data\\Jars-Exe\\geckodriver-v0.29.1-win64\\geckodriver.exe");
			browser = new FirefoxDriver();
		}
		else {
			throw new Exception("Invalid Browser Name");
		}
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.get(arg2);
		
	}
	
	
	public static void enterText(String arg1, String arg2, String arg3) {
		if(arg1.equals("id")) {
			browser.findElement(By.id(arg2)).sendKeys(arg3);
		} 
		else if(arg1.equals("name")) {
			browser.findElement(By.name(arg2)).sendKeys(arg3);
		}
		else if(arg1.equals("xpath")) {
			browser.findElement(By.xpath(arg2)).sendKeys(arg3);
		}
	}
	
	
	public static void click(String arg1, String arg2, String arg3) {
		if(arg1.equals("xpath")) {
			browser.findElement(By.xpath(arg2)).click();
		}
		else if(arg1.equals("name")) {
			browser.findElement(By.name(arg2)).click();
		}
		else if(arg1.equals("id")) {
			browser.findElement(By.id(arg2)).click();
		}
		
	}
	
	
	public static void selectFirstValueFromList(String arg1, String arg2, String arg3) {
		if(arg1.equals("xpath")) {
			browser.findElement(By.xpath(arg2)).click();
		}
		else if(arg1.equals("name")) {
			browser.findElement(By.name(arg2)).click();
		}
		else if(arg1.equals("id")) {
			browser.findElement(By.id(arg2)).click();
		}
	}
	
	
	public static void selectDateGoibibo(String arg1, String arg2, String arg3) {
		String monthYear = browser.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")).getText();
		while(! monthYear.equals(arg2)) {
			browser.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			monthYear = browser.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")).getText();
		}
		
		List<WebElement> allRows = browser.findElements(By.xpath("//div[@class='DayPicker-Week']"));

		boolean found = false;
		for (WebElement row : allRows) {
			List<WebElement> allCells = row.findElements(By.xpath("./div"));
			
			for (WebElement cell : allCells) {
				String dateVal;
				String[] values = {"",""};
				try {
					dateVal = cell.findElement(By.xpath("./div[@class='calDate']")).getText();
					values = dateVal.split("\n");
				} catch (Exception e) {
					System.out.println("Caught exception: " + e);
				}
				
				if(values[0].equals(arg1)) {
					cell.click();
					found=true;
					break;
				}
			}
			if(found) {
				break;
			}
		}
	}
	
	
	public static void clickLink(String arg1, String arg2, String arg3) {
		if(arg1.equals("partialLinkText")) {
			browser.findElement(By.partialLinkText(arg2)).click();
		}
		else if(arg1.equals("linkText")) {
			browser.findElement(By.linkText(arg2)).click();
		}
		
	}
	
	
	public static void switchWindow(String arg1, String arg2, String arg3) {
		parentWindow = browser.getWindowHandle();
		Set<String> allWindows = browser.getWindowHandles();
		for (String window : allWindows) {
			if(! window.equals(parentWindow)) {
				browser.switchTo().window(window);
				if(browser.getTitle().contains(arg1)) {
					System.out.println(browser.getTitle());
					break;
				}
			}
		}
		
	}
	
	public static void switchToParentWindow(String arg1, String arg2, String arg3) {
		browser.switchTo().window(parentWindow);
	}

}
