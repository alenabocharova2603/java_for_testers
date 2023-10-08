package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreateContactTest extends TestBase {

    @Test
    public void createContact() {
        app.contacts().openAddNewPage();
        app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        app.contacts().openHomePage();
    }


    @Test
    public void createContactWithFirstnameOnly() {
        app.contacts().openAddNewPage();
        app.contacts().createContact(new ContactData().withFirstname("some name"));
        app.contacts().openHomePage();
    }
}
