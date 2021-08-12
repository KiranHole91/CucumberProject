package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import pageObjects.AddCustomerPage;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

@RunWith(Cucumber.class)
public class steps extends BaseClass{
	
	@Before
	public void setup() throws IOException
	{
		//Logging
		logger=Logger.getLogger("nopCommerceSDET");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
		//Load properties file
		configProp= new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProp.load(configPropfile);
		
		String br=configProp.getProperty("browser"); //getting the browser name from config.properties file
		
		//Launching browser
		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		
		else if (br.equals("ie")) {
			System.setProperty("webdriver.ie.driver",configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
	
	}
	
	@After
	@Then("User closes browsers")
	public void user_closes_browsers() {
		logger.info("************* closing browser/s *****************");
		driver.quit();
	}
	
	@Given("User launch Chrome browser")
	public void user_launch_chrome_browser() {
		logger.info("************* Launching Browser *****************");
		login = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("************* Opening URL  *****************");
		driver.get(url);
		driver.manage().window().maximize();
	}

	@When("User login with username as {string} and password as {string}")
	public void user_login_with_username_as_and_password_as(String email, String password) {
		logger.info("************* Login with user and password *****************");
		login.setEmail(email);
		login.setPassword(password);
		login.clickLogin();
	}

	@Then("User sees page title {string}")
	public void user_sees_page_title(String title) {
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{
			logger.info("************* Login failed *****************");
			driver.close();
			Assert.assertTrue(false);
		}
		else {
			logger.info("************* Login Passed *****************");
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@Then("User logout using logout button")
	public void user_logout_using_logout_button() {
		logger.info("************* clicking on logout *****************");
		dashboard=new DashboardPage(driver);
		dashboard.clickLogout();
	}

	// Dashboard step definations
	
	@Then("User sees Dashboard")
	public void user_sees_dashboard() {
		logger.info("************* Dashboard page verification *****************");
		dashboard=new DashboardPage(driver);
	    Assert.assertTrue(dashboard.getDashboard());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000); 
		addCust = new AddCustomerPage(driver);
		logger.info("********* Clicking on customer main menu **************");
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("********* Clicking on customer sub menu **************");
		addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		  addCust.clickOnAddnew();
		  
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email = randomestring() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
		// The customer cannot be in both 'Guests' and 'Registered' customer roles
		// Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRoles("Guest");
		Thread.sleep(3000);

		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Kumar");
		addCust.setDob("7/05/1985"); // Format: D/MM/YYY
		addCust.setCompanyName("busyQA");
		addCust.setAdminContent("This is for testing.........");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("********* Saving customer details **************");   
		addCust.clickOnSave();
		   Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	//Searching customer by email ID.............................
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
		searchCust=new SearchCustomerPage(driver);
		logger.info("********* Searching customer details by Email **************");
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(3000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
		boolean status=searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	}
	
	//steps for searching a customer by Name................
		@When("Enter customer FirstName")
		public void enter_customer_FirstName() {
			logger.info("********* Searching customer details by Name **************");
			searchCust=new SearchCustomerPage(driver);
			searchCust.setFirstName("Victoria");
		}

		@When("Enter customer LastName")
		public void enter_customer_LastName() {
			searchCust.setLastName("Terces");
		}

		@Then("User should found Name in the Search table")
		public void user_should_found_Name_in_the_Search_table() {
			boolean status=searchCust.searchCustomerByName("Victoria Terces");
			Assert.assertEquals(true, status);
		}
	

}