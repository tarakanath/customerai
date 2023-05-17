@Acquisition
Feature: Acquisition page validation

  Scenario: Login into CustomerAI application and Create segmentation
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  Scenario: Verify all features on acquisition page
    And Navigate to "Customer Acquisition" page
    When Select "Customer Acquisition" filter setup data from the file "Acquisition" where the sheet is "Acquisition_Filter" and DataRowNum is "1"
    Then Verify "Customer Acquisition" filter criteria selection from the data file "Acquisition" where the sheet is "Acquisition_Filter" and DataRowNum is "1"
    Then Verify default selection from the data file "Acquisition" where the sheet is "Acquisition_Report_EtoE" and DataRowNum is "1"
    Then update and verify customer probability selection data from file "Acquisition" Where the sheet is "Acquisition_Report_EtoE" from data row 2 to 7
    Then verify drivers filter in customer profile table.
    Then verify customer profile info page when profile window "minimize"
    Then verify customer profile info page when profile window "expand"
    Then update and verify driver selection in customer profile table
#    Then verify customer profile pagination with "minimize"
#    Then verify customer profile pagination with "expand"