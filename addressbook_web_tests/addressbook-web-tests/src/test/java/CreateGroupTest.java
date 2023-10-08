import model.DateGroup;
import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase {

  @Test
  public void canCreateGroup() {
    openGroupsPage();
    createGroup(new DateGroup("name group", "header group", "footer group"));
  }

  @Test
  public void canCreateGroupWithEmptyName() {
    openGroupsPage();
    createGroup(new DateGroup());
  }
  @Test
  public void canCreateGroupWithNameOnly() {
    openGroupsPage();
    var emptyGroup = new DateGroup();
    var groupWithName = emptyGroup.withName("some name");
    createGroup(groupWithName);
  }
}
