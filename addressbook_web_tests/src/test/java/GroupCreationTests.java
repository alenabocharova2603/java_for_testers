import model.GroupDate;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class GroupCreationTests extends TestBase {


    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup(new GroupDate("group name", "group header", "group footer"));
    }

    @Test
    public void CanCreateGroupWithEmptyName() {
        openGroupsPage();
        driver.findElement(By.linkText("groups")).click();
        createGroup(new GroupDate());
    }

    @Test
    public void CanCreateGroupWithNameOnly() {
        openGroupsPage();
        driver.findElement(By.linkText("groups")).click();
       var emptyGroup = new GroupDate();
       var groupWithName = emptyGroup.withName("some name");
        createGroup(groupWithName);
    }
}
