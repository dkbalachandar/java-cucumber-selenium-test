Feature: Search Restaurants

  Scenario: Go to yelp and search for valid restaurant
    Given a user goes to Yelp
    When Search for taco bell
    Then See the List of taco bell Restaurants and confirm results contains Lower Greenville

  Scenario: Go to yelp and search for restaurant
    Given a user goes to Yelp
    When Search for Qboba
    Then See the List of Qboba Restaurants

  Scenario: Go to yelp and search for restaurant
    Given a user goes to Yelp
    When Search for Chipotle
    Then See the List of Chipotle Restaurants

  Scenario: Go to yelp and search for invalid restaurant
    Given a user goes to Yelp
    When Search for hhahsdahsdhasd
    Then See No Results found error message

  Scenario Outline:Go to yelp and search for <searchText>
    Given a user goes to Yelp
    When Search for <searchText>
    Then See the List of  Restaurants
    Examples:
      | searchText               |
      | Scardello                |
      | Roti Grill               |
      | Mughlai Restaurant       |
      | Spice In The City Dallas |