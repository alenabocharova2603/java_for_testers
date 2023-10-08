
import model.DateGroup;
import org.junit.jupiter.api.Test;

public class RemoveGroupTest extends TestBase {


    @Test
    public void deletegroup() {
        openGroupsPage();
        if (!isGroupPresent()) {
            createGroup(new DateGroup("group name", "group header", "group footer"));
        }
        removeGroup();
    }

}
