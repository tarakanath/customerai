@overview
Feature: Customer Overview page validation.

  Background:
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"


#  Scenario: Verify my products on customer overview page.
#    When Validate landed on "Customer Overview" page.
#    Then validate my products list from the file "customerOverview" where the sheet is "myproducts"
#    Then validate my products search
#    Then validate navigation to "Product Cross-Sell" page from my products

  Scenario: Verify customer data.
    And Navigate to "Customer Overview" page
    Then Validate three top category customer tiles from the file "CustomerOverview" where the sheet is "customerData" and DataRowNum is "1"
    Then Validate three bottom category customer tiles from the file "CustomerOverview" where the sheet is "Sheet1" and DataRowNum is "1"
    Then Validate CustomerCount is positive
    Then Validate percentage
    Then Validate total customer count





