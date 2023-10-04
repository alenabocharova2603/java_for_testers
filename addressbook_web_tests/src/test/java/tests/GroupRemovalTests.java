package tests;

import model.GroupDate;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase {


    @Test
    public void canRemoveGroup() {
        TestBase.app.openGroupsPage();
        if (!TestBase.app.isGroupPresent()) {
            TestBase.app.createGroup(new GroupDate("group name", "group header", "group footer"));
        }
        TestBase.app.removeGroup();
    }

}
