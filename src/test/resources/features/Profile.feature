Feature: user can manipulate profile information

Background:
Given user is authorized

Scenario: user can get the profile info by ID
Given profile exists with user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
When user hits GET "/rest/v1/profiles?user_id=eq.a6c5ead2-112b-4ed4-9348-a1908ec87926"
Then verify status code is 200
And the response body should contain the profile for user ID "a6c5ead2-112b-4ed4-9348-a1908ec87926"
