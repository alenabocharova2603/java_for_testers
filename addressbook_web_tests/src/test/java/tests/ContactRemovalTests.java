package tests;

import io.qameta.allure.Allure;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase {


    @Test
    public void canRemoveContact() {
        Allure.step("Checking precondition", step -> {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com", "", "", "","","",""));
        }
        });
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(newContacts, expectedList);
        });
    }

    @Test
    void canRemoveAllContactAtOnce() {
        Allure.step("Checking precondition", step -> {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com", "", "", "","","",""));
        }
        });
        app.contacts().removeAllContacts();
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(0,  app.contacts().getContactCount());
        });
    }
}
