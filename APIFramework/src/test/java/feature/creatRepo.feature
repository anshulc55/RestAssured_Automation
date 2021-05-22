Feature: 	Validate Create Repository & Delete Repository

Scenario Outline: 	Verify Create Repo API
	Given 	Starting Test Case "Verify Create Repo API"
	Given 	Create Repo Payload name "<name>" amd description "<description>"
	When 	User calls Create API "/user/repos" Post API Call
	Then 	API Call got Successful with Status Code 201
	And 	Verify Repository "name" is "<name>"
	And 	Verify Repository "description" is "<description>"
	And 	Ending TestCase

Examples: 
	| name 			| description 						|	
	| Anshul-Repo 	| This is Data Driven Test Repo 1	|	
	| Meek-Kyle		| This is Data Driven Test Repo 2 	|	
	| Lisa-Webber	| This is Data Driven Test Repo 3	|
	
Scenario Outline: 	Verify Delete Repo API
	Given 	Starting Test Case "Verify Delete Repo API"
	Given 	Create Repo Payload name "<name>" amd description "<description>"
	When 	User calls Delete API "/repos/levelup360/" Delete Call
	Then 	API Call got Successful with Status Code 204
	And 	Ending TestCase
	
Examples: 
	| name 			| description 						|	
	| Anshul-Repo 	| This is Data Driven Test Repo 1	|	
	| Meek-Kyle		| This is Data Driven Test Repo 2 	|	
	| Lisa-Webber	| This is Data Driven Test Repo 3	|