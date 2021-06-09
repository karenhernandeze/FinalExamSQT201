Feature: Book Store Web API
 
  Scenario: Authorization with valid credentials
    Given an existing user
    When the client calls /Account/v1/Authorized with username 'karenhdez' and password 'Karen@1234a.'
    Then the client receives status code of 200
    And the client receives response 'true'
    
    
  Scenario: Authorization with valid credentials
    Given an existing user
    When the client calls /Account/v1/GenerateToken with username 'karenhdez' and password 'Karen@1234a.'
    Then the client receives status code of 200
    And a 'token' property is provided
