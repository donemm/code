Narrative: Employee record is created
    As a user
    I want to create an Employee record
    In order to store Employee details

Scenario: Employee record creation is successful
Given a new Employee record
When the Employee name is <ename>
When the Employee id is <id>
When the Employee dept is <dept>
When the method is POST
Then the returned values should match

Examples:
|ename|id|dept|
|Tom Brown|1|Dept 1|