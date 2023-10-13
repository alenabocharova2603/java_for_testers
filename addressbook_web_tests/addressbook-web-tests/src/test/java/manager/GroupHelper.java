package manager;

import model.DateGroup;
import org.openqa.selenium.By;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);

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
            click(By.linkText("groups"));
        }
    }

    private void submitGroupCreation() {
        click(By.name("submit"));
    }


    private void initGroupCreation() {
        click(By.name("new"));
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }


    private void removeSelectedGroup() {
        click(By.name("delete"));
    }


    private void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    private void submitGroupModification() {
        click(By.name("update"));
    }

    private void fillGroupForm(DateGroup group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    private void initGroupModification() {
        click(By.name("edit"));
    }

    private void selectedGroup() {
        click(By.name("selected[]"));
    }

    public int getCount() {
        openGroupsPage();
       return manager.driver.findElements(By.name("selected[]")).size();
    }
}
