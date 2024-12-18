# AddressBook Web Tests

Tests are developed for the educational application “Address Book” (a web-based address book). The practice involves creating test scenarios using the Selenium IDE recorder.

The Address Book application allows users to store contact information and organize these contacts into groups.

## Test Scenarios

### Scenario: Creating a New Group

This test scenario outlines the steps to create a new group in the Address Book application.

### Scenario: Creating a New Contact

This test scenario outlines the steps to create a new contact in the Address Book application.

## Fixtures and Preconditions

Overview

A fixture is a setup or device used to secure a workpiece for processing by a tool. In the context of testing, fixtures represent the setup and teardown processes required for running tests. In JUnit 5, the annotations @BeforeEach and @AfterEach are used to define such fixtures.

Usage of Fixtures

### @BeforeEach Block

The @BeforeEach block is used to prepare the necessary preconditions for test execution.

 ### Scenario: Removing a Group (canRemoveGroup)

The test for removing a group cannot execute properly if the group list is initially empty. Therefore, the precondition for this test is the presence of at least one group. To ensure this, the following check is added:

if (app.hbm().getGroupCount() == 0) {
    app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
}

This snippet verifies whether a group exists. If none is found, it creates a new group with predefined data.

### Scenario: Removing All Groups (canRemoveAllGroupsAtOnce)

This scenario requires similar preparations to ensure that groups are available for deletion.

### Scenario: Modifying a Group (canModifyGroup)

Likewise, this scenario relies on the presence of at least one group.

Code Cleanup Steps

After implementing the test scenarios, follow these steps to clean and optimize your code:

#### Rename Classes: Ensure the class name matches the file name for clarity and consistency.

#### Remove Incorrect Imports: Delete imports marked as unused or incorrect (highlighted in red by the IDE).

#### Update Required Imports: Add the necessary imports. In this context, use JUnit 5 imports.

#### Remove Unused Code: Delete unused variables and redundant code.

#### Reformat Code: Format the code using the IDE’s built-in functionality (e.g., Ctrl + A + L in IntelliJ IDEA).

#### Optimize Imports: Remove unused imports with Ctrl + Alt + O in IntelliJ IDEA.

#### Delete Outdated Comments: Remove comments referring to the code’s earlier state (e.g., mentioning that the code was initially a recorder-generated script).

#### Move Initialization Code: Move any actions not directly related to the test scenario into the initialization method.

#### Teardown in @AfterEach: Include cleanup steps in the @AfterEach block, such as logging out after each test to ensure a clean state for subsequent tests.

Example of @AfterEach Block

### @AfterEach
public void tearDown() {
    app.logout();
}

Final Notes

This process ensures the test scenarios are isolated, clean, and maintainable. The resulting test code will:

Follow best practices for setup and teardown.

Be free from unnecessary imports, variables, and comments.

Be formatted and consistent with the project’s style guidelines.

By adhering to these guidelines, you ensure the reliability and clarity of your automated tests.
