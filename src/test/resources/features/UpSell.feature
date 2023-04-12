@upsell
Feature: Up-sell

  Background:
    Given Customer AI application is up.
    And "admin" logged into application, from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  @smoke1
  Scenario: Verify up-sell report for given segmentation
    Given Generate "Segmentation" filter from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "1"
    When Select "Product Up-Sell" from the file "up_sell" where the sheet is "up_sell_Filter" and DataRowNum is "1"
    Then Verify default selection from the file "up_sell" where the sheet is "up_Sell_Report_EtoE" and DataRowNum is "1"
    Then update and verify customer probability selection from file "up_sell" Where the sheet is "up_Sell_Report_EtoE"
    Then update and verify driver selection