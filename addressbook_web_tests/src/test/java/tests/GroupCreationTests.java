package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        app.groups().createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        //ApplicationManager.driver.findElement(By.linkText("groups")).click();
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        //ApplicationManager.driver.findElement(By.linkText("groups")).click();
        app.groups().createGroup(new GroupData().withName("some name"));
    }
}
