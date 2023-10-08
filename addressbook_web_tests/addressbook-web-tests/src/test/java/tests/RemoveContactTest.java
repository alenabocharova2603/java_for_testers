package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase {


    @Test
    public void deleteContact() {
        app.contacts().openHomePage();
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        app.contacts().removeContact();
    }

}
