package manager;
import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

    public void openHomePage() {

        click(By.linkText("home"));
    }

    public void openAddNewPage() {

        click(By.linkText("add new"));
    }

    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        click(By.name("submit"));
    }

    public boolean isContactPresent() {
        openAddNewPage();
        return manager.contacts().manager.isElementPresent(By.name("selected[]"));
    }

    public void removeContact() {
        openHomePage();
        selectContact();
        click(By.cssSelector(".left:nth-child(8) > input"));
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
        click(By.linkText("home page"));
    }

    private void updateContactModification() {

        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.cssSelector("tr:nth-child(2) > .center:nth-child(8) img"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }
}
