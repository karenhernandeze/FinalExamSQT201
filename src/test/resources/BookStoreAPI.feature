Feature: Book Store Web API
 
  Scenario: Authorization with valid credentials
    Given an existing user
    When the client calls /Account/v1/Authorized with username 'karenhdez' and password 'Karen@1234a.'
    Then the client receives status code of 200
    And the client receives response 'true'
    
    
  Scenario: Generate Token with valid credentials
    Given an existing user
    When the client calls /Account/v1/GenerateToken with username 'karenhdez' and password 'Karen@1234a.'
    Then the client receives status code of 200
    And a 'token' property is provided

  Scenario: No authorization with valid credentials
    Given an existing user
    When the client calls /Account/v1/GenerateToken with username 'karenhdez' and password 'Karen@1234a'
    Then the client receives status code of 200
    And a 'token' property is 'null'
    
  Scenario: Find book title with a ISBN 
    Given an existing user with username 'karenhdez' and password 'Karen@1234a.'
    When the client calls /BookStore/v1/Book with ISBN '9781449325862'
    Then the client receives status code of 200
    And the client receives book title 'Git Pocket Guide'
    