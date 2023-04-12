@overview
Feature: Customer Overview page validation.

  Scenario: Login into Customer AI with valida credentials.
    Given Customer AI application is up.
    When "admin" logged into application, from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"
    Then Validate landed on "Customer Overview" page.

  Scenario: Verify my products on customer overview page.
    When Validate landed on "Customer Overview" page.
    Then validate my products list from the file "customerOverview" where the sheet is "myproducts"
    Then validate my products search
    Then validate navigation to "Product Cross-Sell" page from my products

  Scenario: Verify customer data.
    When Validate landed on "Customer Overview" page.
    Then Validate three category customer tiles from the file "customerOverview" where the sheet is "customerData"
    Then validate three category customers data.





