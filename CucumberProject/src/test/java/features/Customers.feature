Feature: Customers page Tests 

@sanity 
Scenario: Add new customer 
	Given User launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User login with username as "admin@yourstore.com" and password as "admin" 
	Then User sees Dashboard 
	When User click on customers Menu 
	And click on customers Menu Item 
	And click on Add new button 
	Then User can view Add new customer page 
	When User enter customer info 
	And click on Save button 
	Then User can view confirmation message "The new customer has been added successfully." 
	And User closes browsers 
	
@regression 
Scenario: Search Customer by EMailID 
	Given User launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User login with username as "admin@yourstore.com" and password as "admin" 
	Then User sees Dashboard 
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer EMail 
	When Click on search button 
	Then User should found Email in the Search table 
	And User closes browsers 
	
@regression 
Scenario: Search Customer by Name 
	Given User launch Chrome browser 
	When User opens URL "https://admin-demo.nopcommerce.com/login" 
	And User login with username as "admin@yourstore.com" and password as "admin" 
	Then User sees Dashboard 
	When User click on customers Menu 
	And click on customers Menu Item 
	And Enter customer FirstName 
	And Enter customer LastName 
	When Click on search button 
	Then User should found Name in the Search table 
	And User closes browsers