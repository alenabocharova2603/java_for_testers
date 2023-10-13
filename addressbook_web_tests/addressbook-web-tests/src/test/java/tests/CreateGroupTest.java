package tests;

import model.DateGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase {

  @Test
  public void canCreateGroup() {
    int groupCount = app.groups().getCount();
    app.groups().createGroup(new DateGroup("name group", "header group", "footer group"));
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  @Test
  public void canCreateGroupWithEmptyName() {
    app.groups().createGroup(new DateGroup());
  }

  @Test
  public void canCreateGroupWithNameOnly() {
    app.groups().createGroup(new DateGroup().withName("some name"));
  }
}
