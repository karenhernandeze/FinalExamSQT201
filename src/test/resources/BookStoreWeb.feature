Feature: Book Store Web Log In
 
  Scenario: Login with valid credentials
  	Given a registered user
    When the client calls /login page with username 'karenhdez' and password 'Karen@1234a.'
    Then the client is redirected to the 'Profile' page
    
  Scenario: Login with invalid credentials
  	Given a not registered user
    When the client calls /login page with username 'karenhdez' and password 'Karen@1234a'
    Then the message 'Invalid username or password!' is displayed
    