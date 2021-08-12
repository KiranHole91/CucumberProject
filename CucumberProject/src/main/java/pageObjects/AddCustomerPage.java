package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.WaitHelper;

public class AddCustomerPage {

	public WebDriver ldriver;
	WaitHelper waitHelper;
	
	public AddCustomerPage(WebDriver rdriver)
	{
		this.ldriver  = rdriver;
		waitHelper = new WaitHelper(rdriver);
		PageFactory.initElements(rdriver, this);
	}
	
	By lnkCustomers_menu=By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("//*[@href='/Admin/Customer/List']/p");
	
	By btnAddnew=By.xpath("//*[@href='/Admin/Customer/Create']"); //Add new
		
	By txtEmail=By.xpath("//input[@id='Email']");
	By txtPassword=By.xpath("//input[@id='Password']");
	
	By txtcustomerRoles=By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	
	By lstitemAdministrators=By.xpath("//li[contains(text(),'Administrators')]");
	By lstitemRegistered=By.xpath("//li[contains(text(),'Registered')]");
	By lstitemGuests=By.xpath("//li[contains(text(),'Guests')]");
	By lstitemVendors=By.xpath("//li[contains(text(),'Vendors')]");
	
	
	By drpmgrOfVendor=By.xpath("//*[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFeMaleGender=By.id("Gender_Female");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
		
	By txtAdminContent=By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave=By.xpath("//button[@name='save']");
	
	//Actions Methods
			
	public String getPageTitle()
	{
		return ldriver.getTitle();
	}
	
	public void clickOnCustomersMenu() {
		ldriver.findElement(lnkCustomers_menu).click();
		waitHelper.waitForElementBy(lnkCustomers_menu, 10);
	}

	public void clickOnCustomersMenuItem() {
		ldriver.findElement(lnkCustomers_menuitem).click();
		waitHelper.waitForElementBy(lnkCustomers_menuitem, 10);
	}
	
	public void clickOnAddnew() {
		waitHelper.waitForElementBy(btnAddnew, 10);
		ldriver.findElement(btnAddnew).click();
		
	}
	
	public void setEmail(String email)
	{
		ldriver.findElement(txtEmail).sendKeys(email);
		waitHelper.waitForElementBy(txtEmail, 10);
	}
	
	public void setPassword(String password)
	{
		ldriver.findElement(txtPassword).sendKeys(password);
		waitHelper.waitForElementBy(txtPassword, 10);
	}
	
	
	public void setCustomerRoles(String role) throws InterruptedException 
		{
			if(!role.equals("Vendors")) //If role is vendors should not delete Register as per req.
			{
			ldriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
			}
			
			waitHelper.waitForElementBy(txtcustomerRoles, 10);
			ldriver.findElement(txtcustomerRoles).click();
					
			
			WebElement listitem;
			
			Thread.sleep(3000);
						
			if(role.equals("Administrators"))
			{
				listitem=ldriver.findElement(lstitemAdministrators); 
			}
			else if(role.equals("Guests"))
			{
				listitem=ldriver.findElement(lstitemGuests);
			}
			else if(role.equals("Registered"))
			{
				listitem=ldriver.findElement(lstitemRegistered);
			}
			else if(role.equals("Vendors"))
			{
				listitem=ldriver.findElement(lstitemVendors);
			}
			else
			{
				listitem=ldriver.findElement(lstitemGuests);
			}
						
			//listitem.click();
			//Thread.sleep(3000);
			
			JavascriptExecutor js = (JavascriptExecutor)ldriver;
			js.executeScript("arguments[0].click();", listitem);
			
	}

	public void setManagerOfVendor(String value)
	{
		Select drp=new Select(ldriver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender)
	{
		if(gender.equals("Male"))
		{
			waitHelper.waitForElementBy(rdMaleGender, 10);
			ldriver.findElement(rdMaleGender).click();
			
		}
		else if(gender.equals("Female"))
		{
			waitHelper.waitForElementBy(rdFeMaleGender, 10);
			ldriver.findElement(rdFeMaleGender).click();
			
		}
		else
		{
			waitHelper.waitForElementBy(rdMaleGender, 10);
			ldriver.findElement(rdMaleGender).click();//Default
			
		}
		
	}
	
	public void setFirstName(String fname)
	{
		ldriver.findElement(txtFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		ldriver.findElement(txtLastName).sendKeys(lname);
	}
	
	public void setDob(String dob)
	{
		ldriver.findElement(txtDob).sendKeys(dob);
	}
	
	public void setCompanyName(String comname)
	{
		ldriver.findElement(txtCompanyName).sendKeys(comname);
	}
	
	public void setAdminContent(String content)
	{
		ldriver.findElement(txtAdminContent).sendKeys(content);
	}
	
	public void clickOnSave()
	{
		ldriver.findElement(btnSave).click();
	}
	
}