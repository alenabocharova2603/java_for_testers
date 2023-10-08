package tests;

import model.DateGroup;
import org.junit.jupiter.api.Test;

public class CreateGroupTest extends TestBase {

  @Test
  public void canCreateGroup() {
    app.groups().createGroup(new DateGroup("name group", "header group", "footer group"));
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
