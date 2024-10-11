Feature: User Task Completion

  Scenario: Check user tasks completion
    Given User has the todo tasks
    And User belongs to the city FanCode
    Then User Completed task percentage should be greater than 50%

