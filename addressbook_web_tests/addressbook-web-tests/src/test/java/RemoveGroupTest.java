import org.junit.jupiter.api.Test;

public class RemoveGroupTest extends TestBase{


    @Test
    public void deletegroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup("group name", "group header", "group footer");
        }
        removeGroup();
    }

}
