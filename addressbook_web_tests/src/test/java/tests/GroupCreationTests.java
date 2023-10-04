package tests;

import manager.ApplicationManager;
import model.GroupDate;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        TestBase.app.openGroupsPage();
        TestBase.app.createGroup(new GroupDate("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        TestBase.app.openGroupsPage();
        ApplicationManager.driver.findElement(By.linkText("groups")).click();
        TestBase.app.createGroup(new GroupDate());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        TestBase.app.openGroupsPage();
        ApplicationManager.driver.findElement(By.linkText("groups")).click();
       var emptyGroup = new GroupDate();
       var groupWithName = emptyGroup.withName("some name");
        TestBase.app.createGroup(groupWithName);
    }
}
