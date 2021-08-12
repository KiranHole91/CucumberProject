package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver ;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Email")
	@CacheLookup
	WebElement email;
	
	@FindBy(xpath="//*[@id='Password']")
	@CacheLookup
	WebElement password;
	
	@FindBy(css="button[type='submit']")
	@CacheLookup
	WebElement login;
	
	public void setEmail(String username) {
		email.clear();
		email.sendKeys(username);
	}
	
	public void setPassword(String pass) {
		password.clear();
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		login.click();
	}
	
			
}
