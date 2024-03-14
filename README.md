#Test Automation Framework - UI & API
Sample end 2 end test automation framework for UI and API testing.

This sample Software Test Automation Framework is built as a Maven project using Behavior Driven Development principles with Cucumber and Junit in Java. And also utilizing Page Object Module structure to maximize framework re-usability and maintainability. The scenario is written in Gherkin language which is a plain English, that could benefit connecting the team.

And the framework is also structured to support API testing using RestAssured with TestNG, and Database integration capabilities with JDBC.

##Project structure
The project structure is mainly divided into 2 sections, src/test/java and src/test/resource

src/test/java

- api_tests - (API tests written using RestAssured library in TestNG tests structure).
- pages - (Page objects are created within this folder)
- runners - (One or more Cucumber runner classes are within this folder)
- step_definitions - (Test steps implementation for the scenario steps defined in feature files)
- utils - (Any utility and support classes created within this folder)
src/test/resources

- features - (Cucumber feature files are created within this folder to define scenarios for the feature)
- testdata - (Test data / properties files are stored within this folder)
- testfiles - (Test Json or any dummy files are stored here)

reports - (cucumber generated reports are stored in this location)

test-output - (testng generated reports are stored in this location)

pom.xml - (Project configurations, and content/library/dependencies management and build/run configuration)

testng.xml files - (TestNG test suite management and executions control)

##Tools used
UI - tools used for UI test automation:

- Maven - (Project configurations, and content/library/dependencies management and build/run configuration)
- Cucumber - (to define feature scenarios in gherkin, and also to create test suites and execution flow with tagging, and reporting)
- Selenium - (to automate the web application by implementing the step definitions, managing the page objects)
- JUnit - (to run the cucumber scenarios with cucumber options, and assertions)
JavaFaker - (to create dummy data for testing activities)
- JDBC - (to connect to application database for testing and verification activities)
MySql driver - (to allow connection to MySql database that application uses)

API - tools used to API test automation:

- TestNG - (to create API tests and manage test suites, test execution flow, assertion and reporting)
- RestAssured - (to define our API tests)
JavaFaker - (to create dummy data for testing activities)
- JDBC - (to connect to application database for testing and verification activities)
- MySql driver - (to allow connection to MySql database that application uses)

Other tools used for end 2 end testing activities

- Git - (Source code management / version control)
- Github - (Remote source code management / version control platform)
- Jenkins - (CI/CD tool which we have used to run our test suites on demand or periodically)
- Eclipse - (IDE for project development)