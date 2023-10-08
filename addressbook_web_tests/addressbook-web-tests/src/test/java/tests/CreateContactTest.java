package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreateContactTest extends TestBase {

    @Test
    public void createContact() {
        TestBase.app.openAddNewPage();
        TestBase.app.createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        TestBase.app.openHomePage();
    }


    @Test
    public void createContactWithFirstnameOnly() {
        TestBase.app.openAddNewPage();
        var emptyContact = new ContactData();
        var contactWithFirstname = emptyContact.withFirstname("some name");
        TestBase.app.createContact(contactWithFirstname);
        TestBase.app.openHomePage();
    }
}
