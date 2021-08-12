package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {

	public WebDriver driver;
	
	public WaitHelper(WebDriver driver)
	{
		this.driver =  driver;
	}
	
	public void waitForElement(WebElement element,int i)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(i));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementBy(By locator,int i)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(i));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
}
