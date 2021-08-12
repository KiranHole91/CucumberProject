package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class DashboardPage {

	public WebDriver driver;
	WaitHelper waitHelper;
	
	public DashboardPage(WebDriver rdriver)
	{
		this.driver  = rdriver;
		waitHelper = new WaitHelper(rdriver);
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath="//*[text()='Logout']")
	@CacheLookup
	WebElement logout;
	
	@FindBy(css=".content-header h1")
	@CacheLookup
	WebElement dashboard;
	
	@FindBy(xpath="//a[@href='#'] //p[contains(text(),'Customers')]")
	@CacheLookup
	WebElement customersMenu;
	
	public void clickLogout() {
		waitHelper.waitForElement(logout, 10);
		logout.click();
		
	}

	
	public void clickcustomers() {
		waitHelper.waitForElement(customersMenu, 10);
		customersMenu.click();
		
	}
	
	public boolean getDashboard() {
		waitHelper.waitForElement(customersMenu, 10);
		return dashboard.isDisplayed();
	}
	
			
}
