Feature: Implement Salesforce Login page

 	Background:
		Given user navigates to salesforce login page
 		When  user on "SFLoginPage"
 		
 	Scenario: checking login with invalid login credentials
	 	When  "testsales@divya.com" logs in with wrong password
	 	Then error message "Please enter your password." is displayed	
	 	
	Scenario: checking login with valid login credentials
   	When "testsales@divya.com" logs in
   	Then the page title is "Home Page ~ Salesforce - Developer Edition"
   	
   Scenario: checking username saved when remember me option selected
   	 When "testsales@divya.com" logs in with remember Me checked
   	 When user on "HomePage"
   	 And 	logs out
   	 And 	user on "SFLoginPage"
   	 Then "testsales@divya.com" should be saved in the username field
   	 
   Scenario: checking forgot password link
   When  	clicks forgot password link  
   When 	user on "ForgotPasswordPage"
   And   	enters "testsales@divya.com" and clicks continue
   Then 	reset password email is sent and title of the page is "Check Your Email"
   	
 	

 