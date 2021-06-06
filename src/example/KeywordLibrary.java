package example;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KeywordLibrary {
	
	public static WebDriver browser;
	
	public static void processor(String methodName, String arg1, String arg2, String arg3) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class cls = KeywordLibrary.class;
		Method m = cls.getDeclaredMethod(methodName, String.class, String.class, String.class);
		m.invoke(cls, arg1, arg2, arg3);		
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

}
