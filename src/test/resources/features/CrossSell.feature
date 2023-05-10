@crosssell
Feature: Cross-sell page validation

  @smoke
  Scenario: Login into CustomerAI application and Create segmentation
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"
    When Generate "Segmentation" filter get test data from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "1"
    Then validate created segment appeared in segment list

  @smoke
  Scenario: Verify all features on Cross-sell report page
    When Select "Product Cross-Sell" filter setup data from the file "Cross_sell" where the sheet is "Cross_sell_Filter" and DataRowNum is "1"
    Then Verify "Product Cross-Sell" filter criteria selection from the data file "Cross_sell" where the sheet is "Cross_sell_Filter" and DataRowNum is "1"
    Then Verify default selection from the data file "Cross_sell" where the sheet is "Cross_Sell_Report_EtoE" and DataRowNum is "1"
    Then update and verify customer probability selection data from file "Cross_sell" Where the sheet is "Cross_Sell_Report_EtoE" from data row 2 to 7
    Then verify drivers filter in customer profile table.
    Then verify customer profile info page when profile window "minimize"
    Then verify customer profile info page when profile window "expand"
    Then verify customer profile pagination with "minimize"
    Then verify customer profile pagination with "expand"
    Then update and verify driver selection in customer profile table
    When update "Product Cross-Sell" filter criteria from the file "Cross_sell" where the sheet is "Cross_sell_Filter" and DataRowNum is "2"
    Then Verify "Product Cross-Sell" filter criteria selection from the data file "Cross_sell" where the sheet is "Cross_sell_Filter" and DataRowNum is "2"

  @smoke
  Scenario: Delete segmentation and logout application
    When "delete" segmentation from the data file "Segmentation" where the sheet is "SegmentManagement" and DataRowNum is "1"
    Then Verify segment deleted
    Then logout from the application