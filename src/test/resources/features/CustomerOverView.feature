@overview
Feature: Customer Overview page validation.

  Scenario: Login into application
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"
  @overview1
  Scenario: Verify my products on customer overview page.
     And Navigate to "Customer Overview" page
     Then validate my product list from Database
     Then Enter product name from the file "customerOverview" where the sheet is "my_products" and DataRowNum is "2"
     Then validate potential customers for product from the file "customerOverview" where the sheet is "my_products" and DataRowNum is "3"
     Then validate active customer count from Database for the product widget
     Then validate new customer count for the product widget
     Then validate new customer percentage count for the product widget

  @overview2
  Scenario: Verify customer data.
    Then Validate three top category customer tiles from the file "Customer_Overview" where the sheet is "customerData" and DataRowNum is "1"
    Then Validate three bottom category customer tiles from the file "Customer_Overview" where the sheet is "Sheet1" and DataRowNum is "1"
    Then Validate customer stats from Customer Overview Page are positive and match the count from DB
    Then Validate Active and Newly Onboarded Customers Percentage from UI
    Then Validate total count of Active and Inactive customers

  Scenario: logout application
      Then logout from the application




