Feature: Create a new Booking


  @PostMsg
  Scenario Outline: Create a booking with valid data
    Given the endpoint "https://automationintesting.online/booking/"
    When customer sends a POST request with the following data:
      | roomid    | firstname  | lastname  | depositpaid | email             | phone          | checkin     | checkout    |
      | <roomid>  | <firstname>| <lastname>| <depositpaid>| <email>           | <phone>        | <checkin>   | <checkout>  |
    Then the response status code should be 201
    And the response should contain a valid bookingId
    And the response should match the JSON schema

    Examples:
      | roomid | firstname | lastname | depositpaid | email                | phone         | checkin    | checkout   |
      | 103     | Yannick   | Delehman | true        | yonas@gmail.com      | 3246534543789 | 2025-03-08 | 2025-03-09 |



  @ErrorMsg
  Scenario Outline: Create a booking with Same data
    Given the endpoint "https://automationintesting.online/booking/"
    When customer sends a POST request with the Same data:
      | roomid    | firstname  | lastname  | depositpaid | email             | phone          | checkin     | checkout    |
      | <roomid>  | <firstname>| <lastname>| <depositpaid>| <email>           | <phone>        | <checkin>   | <checkout>  |
    Then the response status code should be 409

    Examples:
      | roomid | firstname | lastname | depositpaid | email                | phone         | checkin    | checkout   |
      | 103     | Yannick   | Delehman | true        | yonas@gmail.com      | 3246534543789 | 2025-03-08 | 2025-03-09 |

