package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact() {
        if (app.contacts().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        app.contacts().modifyContact(new ContactData().withFirstname("modified name"));
    }
}
