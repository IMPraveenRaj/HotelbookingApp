Feature: Validating Place API'S

Scenario: Verify if place is being successfully added using AddPlaceApi
  Given Add Place Payload
  When user calls "AddPlaceApi" with Post http request
  Then  the API call is success with status code 200
  And "status"  in response body is "OK"
  And "scope" in response body is "APP"


