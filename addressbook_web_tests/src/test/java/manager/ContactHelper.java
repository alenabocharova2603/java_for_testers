package manager;
import model.ContactData;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }
    public void createContact(ContactData contact) {
        openAddNewPage();
        fillContactForm(contact);
        click(By.name("submit"));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();

    }

    public void removeSelectedContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification();
        fillContactForm(modifiedContact);
        updateContactModification();
        returnToContactPage();
    }

    public void openHomePage() {

        click(By.linkText("home"));
    }

    public void openAddNewPage() {

        click(By.linkText("add new"));
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

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public int getContactCount() {
        openHomePage();
      return  manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContacts();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getList() {
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));//manager.driver.findElements(By.cssSelector("tr.name.entry"));
        for (var tr : trs) {
            var name = tr.getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstname(name));
        }
        return contacts;
    }
}
