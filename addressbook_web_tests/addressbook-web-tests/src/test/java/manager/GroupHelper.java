package manager;

import model.DateGroup;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {

        this.manager = manager;
    }

    public void createGroup(DateGroup group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void modifyGroup(DateGroup modifiedGroup) {
        openGroupsPage();
        selectedGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void removeGroup() {
        openGroupsPage();
        selectedGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void openGroupsPage() {
        if (!manager.contacts().manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }
    
    private void submitGroupCreation() {
        manager.driver.findElement(By.name("submit")).click();
    }

    private void initGroupCreation() {
        manager.driver.findElement(By.name("new")).click();
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }


    private void removeSelectedGroup() {
        manager.driver.findElement(By.name("delete")).click();
    }


    private void returnToGroupsPage() {
        manager.driver.findElement(By.linkText("group page")).click();
    }

    private void submitGroupModification() {
     manager.driver.findElement(By.name("update")).click();
    }

    private void fillGroupForm(DateGroup group) {
        manager.driver.findElement(By.name("group_name")).click();
        manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
        manager.driver.findElement(By.name("group_header")).click();
        manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
        manager.driver.findElement(By.name("group_footer")).click();
        manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
    }

    private void initGroupModification() {
     manager.driver.findElement(By.name("edit")).click();
    }

    private void selectedGroup() {
        manager.driver.findElement(By.name("selected[]")).click();
    }
}
