package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void createContact() {
        int contactCount = app.contacts().getContactCount();
        app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        int newContactCount = app.contacts().getContactCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
        app.contacts().openHomePage();
    }


    @Test
    public void createContactWithFirstnameOnly() {
        app.contacts().createContact(new ContactData().withFirstname("some name"));
        app.contacts().openHomePage();
    }
    @Test
    public void canCreateMultipleContacts() {
        int n = 5;
        int contactCount = app.contacts().getContactCount();

        for (int i = 0; i < n; i++) {
            app.contacts().createContact(new ContactData(randomString(i * 10), "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }

        int newContactCount = app.contacts().getContactCount();
        Assertions.assertEquals(contactCount + n, newContactCount);
        app.contacts().openHomePage();
    }
}
