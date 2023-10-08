package manager;

import model.DateGroup;
import org.openqa.selenium.By;

public class GroupHelper {
    private final ApplicationManager manager;

    public GroupHelper(ApplicationManager manager) {

        this.manager = manager;
    }

    public void openGroupsPage() {
        if (!manager.contacts().manager.isElementPresent(By.name("new"))) {
            manager.driver.findElement(By.linkText("groups")).click();
        }
    }

    public void createGroup(DateGroup group) {
        openGroupsPage();
      manager.driver.findElement(By.name("new")).click();
      manager.driver.findElement(By.name("group_name")).click();
      manager.driver.findElement(By.name("group_name")).sendKeys(group.name());
      manager.driver.findElement(By.name("group_header")).click();
      manager.driver.findElement(By.name("group_header")).sendKeys(group.header());
      manager.driver.findElement(By.name("group_footer")).click();
      manager.driver.findElement(By.name("group_footer")).sendKeys(group.footer());
      manager.driver.findElement(By.name("submit")).click();
      manager.driver.findElement(By.linkText("group page")).click();
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }

    public void removeGroup() {
        openGroupsPage();
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.name("delete")).click();
        manager.driver.findElement(By.linkText("group page")).click();
    }
}
