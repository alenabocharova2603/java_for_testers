package tests;

import model.DateGroup;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new DateGroup("group name", "group header", "group footer"));
        }
        app.groups().modifyGroup(new DateGroup().withName("modified name"));
    }
}
