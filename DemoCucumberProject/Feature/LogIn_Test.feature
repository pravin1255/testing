Feature: Login Action 
Background: Opening the browser 
	Given User opens the browser
@Policy 
Scenario Outline: Verify the amount displayed in Sum insured is same in both pages 
	And opens the policy bazar site 
	When user fills all the details "<Testcase Name>" 
	And selects two policy 
	And clicks on Compare now button 
	Then verify the amount displayed in Sum insured is same in both pages "<Testcase Name>" 
	Examples: 
		| Testcase Name |
		| TC1           |