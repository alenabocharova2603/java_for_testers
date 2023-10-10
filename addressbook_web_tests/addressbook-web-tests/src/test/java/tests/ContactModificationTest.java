package tests;

import model.ContactData;
import model.DateGroup;
import org.junit.jupiter.api.Test;

public class ContactModificationTest extends TestBase{

    @Test
    void canModifyContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        app.contacts().modifyContact(new ContactData().withFirstname("modified name"));
    }
}
