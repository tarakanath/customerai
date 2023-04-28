@overview
Feature: Customer Overview page validation.

  Background:
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  Scenario: Verify my products on customer overview page.
     And Navigate to "Customer Overview" page
     Then validate my products list from the file "customerOverview" where the sheet is "my_products" and DataRowNum is "1"
     Then Enter product name from the file "customerOverview" where the sheet is "my_products" and DataRowNum is "2"
     Then validate potential customers for product from the file "customerOverview" where the sheet is "my_products" and DataRowNum is "3"
     Then validate data for the product widget
#
#  Scenario: Verify customer data.
#    And Navigate to "Customer Overview" page
#    Then Validate three category customer tiles from the file "customerOverview" where the sheet is "customerData"
#    Then validate three category customers data.
#
#
#


