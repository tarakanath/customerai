@overview
Feature: Customer Overview page validation.

  Background:
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  Scenario: Verify customer data.
    Then Validate three top category customer tiles from the file "Customer_Overview" where the sheet is "customerData" and DataRowNum is "1"
    Then Validate three bottom category customer tiles from the file "Customer_Overview" where the sheet is "Sheet1" and DataRowNum is "1"
    Then Validate customer stats from Customer Overview Page are positive and match the count from DB
    Then Validate Active and Newly Onboarded Customers Percentage from UI
    Then Validate total count of Active and Inactive customers





