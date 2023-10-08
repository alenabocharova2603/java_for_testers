package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper {
    final ApplicationManager manager;

    public ContactHelper(ApplicationManager manager) {

        this.manager = manager;
    }

    public void openHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    public void openAddNewPage() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public  void createContact(ContactData contact) {
        manager.driver.findElement(By.name("firstname")).click();
        manager.driver.findElement(By.name("firstname")).sendKeys(contact.firstname());
        manager.driver.findElement(By.name("lastname")).click();
        manager.driver.findElement(By.name("lastname")).sendKeys(contact.lastname());
        manager.driver.findElement(By.name("address")).click();
        manager.driver.findElement(By.name("address")).sendKeys(contact.address());
        manager.driver.findElement(By.name("mobile")).click();
        manager.driver.findElement(By.name("mobile")).sendKeys(contact.address());
        manager.driver.findElement(By.name("email")).click();
        manager.driver.findElement(By.name("email")).sendKeys(contact.email());
        manager.driver.findElement(By.name("submit")).click();
    }

    public boolean isContactPresent() {
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        manager.driver.findElement(By.name("selected[]")).click();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        manager.driver.switchTo().alert().accept();
    }

}
