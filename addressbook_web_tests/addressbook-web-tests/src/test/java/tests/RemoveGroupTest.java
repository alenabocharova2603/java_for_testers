package tests;

import model.DateGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RemoveGroupTest extends TestBase {


    @Test
    public void deleteGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new DateGroup("group name", "group header", "group footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().removeGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

}
