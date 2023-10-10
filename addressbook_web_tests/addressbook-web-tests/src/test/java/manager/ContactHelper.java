package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

    public void openHomePage() {
        manager.driver.findElement(By.linkText("home")).click();
    }

    public void openAddNewPage() {
        manager.driver.findElement(By.linkText("add new")).click();
    }

    public  void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        manager.driver.findElement(By.name("submit")).click();
    }

    public boolean isContactPresent() {
        openAddNewPage();
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        manager.driver.findElement(By.cssSelector(".left:nth-child(8) > input")).click();
        manager.driver.switchTo().alert().accept();
    }

    public void modifyContact(ContactData modifiedContact) {
        openHomePage();
        selectContact();
        initContactModification();
        fillContactForm(modifiedContact);
        updateContactModification();
        returnToContactPage();

    }

    private void returnToContactPage() {
        manager.driver.findElement(By.linkText("home page")).click();
    }

    private void updateContactModification() {
        manager.driver.findElement(By.name("update")).click();
    }

    private void initContactModification() {
        manager.driver.findElement(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img")).click();
    }

    private void fillContactForm(ContactData contact) {
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
    }

    private void selectContact() {
        manager.driver.findElement(By.name("selected[]")).click();
    }
}
