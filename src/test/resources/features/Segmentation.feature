@segmentation
Feature: Segmentation Feature

  Background:
    Given User landed on Customer AI Application
    And "admin" logged into application get login data from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  @smoke11
  Scenario: Generate new segmentations and delete
    When Generate "Segmentation" filter get test data from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "1"
    Then validate created segment appeared in segment list

  @smoke11
  Scenario: Generate duplicate segmentations
    When Generate "Segmentation" filter get test data from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "2"
    Then validate click on existing segmentation link from error message to navigate to existing segmentation