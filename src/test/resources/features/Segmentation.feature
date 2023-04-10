@segmentation
Feature: Segmentation Feature

  Background:
    Given Customer AI application is up.
    And "admin" logged into application, from the file "loginPage" where the sheet is "LoginCredentials" and DataRowNum  is "1"

  @smoke1
  Scenario: Generate new segmentations and delete
    When Generate "Segmentation" filter from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "1"
    Then validate created segment appeared in segment list
    #And "delete" segmentation

  @smoke1
  Scenario: Generate duplicate segmentations
    When Generate "Segmentation" filter from the file "Segmentation" where the sheet is "Segmentation" and DataRowNum  is "2"
    Then validate click on existing segmentation link from error message to navigate to existing segmentation