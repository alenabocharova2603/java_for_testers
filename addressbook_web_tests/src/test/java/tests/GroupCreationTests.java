package tests;

import model.GroupDate;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        app.groups().createGroup(new GroupDate("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        //ApplicationManager.driver.findElement(By.linkText("groups")).click();
        app.groups().createGroup(new GroupDate());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        //ApplicationManager.driver.findElement(By.linkText("groups")).click();
        app.groups().createGroup(new GroupDate().withName("some name"));
    }
}
