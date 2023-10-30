package manager;
import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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

    public void createContact(ContactData contact, GroupData group) {
        openAddNewPage();
        fillContactForm(contact);
        selectGroup(group);
        click(By.name("submit"));
    }

    private void selectGroup(GroupData group) {
       new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
        manager.driver.findElement(By.cssSelector("div.msgbox"));

    }

    public void removeSelectedContacts() {
        click(By.cssSelector(".left:nth-child(8) > input"));
        manager.driver.switchTo().alert().accept();
    }


    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
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
        click(By.xpath("//input[@name='update']"));
    }

    private void initContactModification(ContactData contact) {
        var row = manager.driver.findElement(By.xpath(
                //String.format("//input[@id='%s']/parent::td/parent::tr", contact.id())
                String.format("//a[@href='edit.php?id=%s']", contact.id())
                )
        );

        row.click();
        //row.findElement(By.xpath(".//img[@alt='Edit']")).click();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobile());
        type(By.name("email"), contact.email());
        //attach(By.name("photo"),contact.photo());
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
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));//manager.driver.findElements(By.cssSelector("tr.name.entry"));
        for (var tr : trs) {
            var lName = tr.findElement(By.xpath(".//td[2]"));
            var fName = tr.findElement(By.xpath(".//td[3]"));
            var sL_Name = lName.getText();
            var sF_Name = fName.getText();

            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstname(sF_Name).withLastname(sL_Name));
        }
        return contacts;
    }
}
