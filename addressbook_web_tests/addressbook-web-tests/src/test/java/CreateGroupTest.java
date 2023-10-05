import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase{

    @Test
    public void CanCreateGroup() {
        openGroupsPage();
        createGroup("group name", "group header", "group footer");

    }

    @Test
    public void canCreateGroupWithEmptyName() {
        openGroupsPage();
        createGroup("", "", "");
    }

}
