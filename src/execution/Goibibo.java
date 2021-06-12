package execution;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Goibibo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Data\\Jars-Exe\\chromedriver_win32\\chromedriver.exe");
		WebDriver browser = new ChromeDriver();
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.manage().window().maximize();
		browser.get("https://www.goibibo.com/");
		
		browser.findElement(By.id("gosuggest_inputSrc")).sendKeys("Mumbai");
		browser.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li")).click();
		
		browser.findElement(By.id("gosuggest_inputDest")).sendKeys("Delhi");
		browser.findElement(By.xpath("//ul[@id='react-autosuggest-1']/li")).click();
		
		String inputMonthYear = "August 2021";
		String inputDate = "15";
		
		String monthYear = browser.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")).getText();


		
		while(! monthYear.equals(inputMonthYear)) {
			browser.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			monthYear = browser.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")).getText();
		}
		
		List<WebElement> allRows = browser.findElements(By.xpath("//div[@class='DayPicker-Week']"));
		System.out.println(allRows.size());
		boolean found = false;
		for (WebElement row : allRows) {
			List<WebElement> allCells = row.findElements(By.xpath("./div"));
			
			for (WebElement cell : allCells) {
				String dateVal = cell.findElement(By.xpath("./div[@class='calDate']")).getText();
				String[] values = dateVal.split("\n");
				
				if(values[0].equals(inputDate)) {
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

}
