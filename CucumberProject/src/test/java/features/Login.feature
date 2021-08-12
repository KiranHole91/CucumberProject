Feature: Login page Tests

@sanityLogin
Scenario: successfully login with valid credentials
Given User launch Chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User login with username as "admin@yourstore.com" and password as "admin"
Then User sees page title "Dashboard / nopCommerce administration"
Then User logout using logout button
And User sees page title "Your store. Login"
And User closes browsers

Scenario Outline: login with data driven
Given User launch Chrome browser
When User opens URL "https://admin-demo.nopcommerce.com/login"
And User login with username as "<username>" and password as "<password>"
Then User sees page title "Dashboard / nopCommerce administration"
Then User logout using logout button
And User sees page title "Your store. Login"
And User closes browsers

Examples:
|username|password|
|admin@yourstore.com|admin|
|admin@yourstore.com|admin123|