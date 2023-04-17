@customerchrun
Feature: Customer Churn page validation

  @smoke
  Scenario: Login into CustomerAI application and Create segmentation
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"
    When Generate "Segmentation" filter get test data from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "1"
    Then validate created segment appeared in segment list

  @smoke
  Scenario: Verify all features on Cross-sell report page
    When Select "Customer Churn" filter setup data from the file "customer_churn" where the sheet is "customer_churn_Filter" and DataRowNum is "1"
    Then Verify "Customer Churn" filter criteria selection from the data file "customer_churn" where the sheet is "customer_churn_Filter" and DataRowNum is "1"
    Then Verify default selection from the data file "customer_churn" where the sheet is "customer_churn_Report_EtoE" and DataRowNum is "1"
    Then update and verify customer probability selection data from file "customer_churn" Where the sheet is "customer_churn_Report_EtoE" from data row 2 to 7
    Then verify drivers filter in customer profile table.
    Then verify customer profile info page when profile window "minimize"
    Then verify customer profile info page when profile window "expand"
    Then verify customer profile pagination with "minimize"
    Then verify customer profile pagination with "expand"
    Then update and verify driver selection in customer profile table
    When update "Customer Churn" filter criteria from the file "customer_churn" where the sheet is "customer_churn_Filter" and DataRowNum is "2"
    Then Verify "Customer Churn" filter criteria selection from the data file "customer_churn" where the sheet is "customer_churn_Filter" and DataRowNum is "2"

  @smoke
  Scenario: Delete segmentation and logout application
    When "delete" segmentation from the data file "Segmentation" where the sheet is "SegmentManagement" and DataRowNum is "1"
    Then Verify segment deleted
    Then logout from the application