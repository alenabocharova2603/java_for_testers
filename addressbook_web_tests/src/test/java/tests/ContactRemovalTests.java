package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContactRemovalTests extends TestBase {


    @Test
    public void deleteContact() {
        if (app.contacts().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        int contactCount = app.contacts().getContactCount();
        app.contacts().removeContact();
        int newContactCount = app.contacts().getContactCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }
    @Test
    void canRemoveAllContactAtOnce() {
        if (app.contacts().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,  app.contacts().getContactCount());
    }
}
