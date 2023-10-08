package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase {


    @Test
    public void deleteContact() {
        TestBase.app.openHomePage();
        if (!TestBase.app.isContactPresent()) {
            TestBase.app.createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        TestBase.app.removeContact();
    }

}
