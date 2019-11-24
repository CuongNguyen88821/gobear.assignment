Feature: Travel Insurance

  Scenario: Verify Search Result for Travel Insurance should contain at least 3 cards
    Given User navigates to GoBear Home Page
    When User fills Search Details for Travel Insurance
    Then Search Result Page for Travel Insurance displays
    And There're at least 3 cards displayed on Search Result Page

  Scenario: Verify User can filter result list by Insurers
    When User selects 2 Insurers from Filters section
    Then There're only plans from the selected Insurers displayed on Result Page

  Scenario: Verify User can filter result list by sets Range Selector
    Given User clears all filters
    When User sets Range Selector for "Trip termination" with min value = 5000 and max value = 125000
    Then There're only plans with "Trip termination" in selected range displayed on Result Page

  Scenario: Verify User can sort result list by selecting any Sort option
    Given User clears all filters
    When User selects Sort Option "Price: High to low"
    Then Search Result plans are sorted by "Price: High to low"

  Scenario: Verify User can change Destination in Search Result Page
    When User selects Destination as "Philippines"

  Scenario: Verify User can change Start Date in Search Result Page
    When User selects Start Date as "02-12-2019"