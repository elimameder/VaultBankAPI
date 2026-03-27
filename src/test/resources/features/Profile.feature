Feature: user can manipulate profile information

Background:
Given user is authorized

Scenario: user can get the profile info by ID
Given profile exists with user ID "d87fd373-a02d-411d-8563-c23511cf9f7c"
When user hits GET "/rest/v1/profiles?user_id=eq.d87fd373-a02d-411d-8563-c23511cf9f7c"
Then verify status code is 200
And the response body should contain the profile for user ID "d87fd373-a02d-411d-8563-c23511cf9f7c"
