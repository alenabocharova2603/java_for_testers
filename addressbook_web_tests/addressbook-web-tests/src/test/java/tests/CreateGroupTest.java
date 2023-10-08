package tests;

import model.DateGroup;
import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase {

  @Test
  public void canCreateGroup() {
    TestBase.app.openGroupsPage();
    TestBase.app.createGroup(new DateGroup("name group", "header group", "footer group"));
  }

  @Test
  public void canCreateGroupWithEmptyName() {
    TestBase.app.openGroupsPage();
    TestBase.app.createGroup(new DateGroup());
  }
  @Test
  public void canCreateGroupWithNameOnly() {
    TestBase.app.openGroupsPage();
    var emptyGroup = new DateGroup();
    var groupWithName = emptyGroup.withName("some name");
    TestBase.app.createGroup(groupWithName);
  }
}
