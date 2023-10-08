package tests;

import model.DateGroup;
import org.junit.jupiter.api.Test;

public class RemoveGroupTest extends TestBase {


    @Test
    public void deletegroup() {
        TestBase.app.openGroupsPage();
        if (!TestBase.app.isGroupPresent()) {
            TestBase.app.createGroup(new DateGroup("group name", "group header", "group footer"));
        }
        TestBase.app.removeGroup();
    }

}
