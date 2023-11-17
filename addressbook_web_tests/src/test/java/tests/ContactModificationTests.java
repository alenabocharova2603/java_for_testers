package tests;

import io.qameta.allure.Allure;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243",
                    "+98567841456", "nina_kot@koler.com", "+4954723212453", "+1214314323", "+3225331144",
                    "Joew@yandex.ru", "outlook@outlook.com", "75 3rd Ave, New York, NY 10003, USA"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testContact = new ContactData().withFirstname("modified name");
        app.contacts().modifyContact(oldContacts.get(index), testContact);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testContact.withId(oldContacts.get(index).id())
                .withFirstname(testContact.firstname())
                .withLastname(testContact.lastname())
                .withAddress("")
                .withMobile("")
                .withEmail(""));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(newContacts,expectedList);
    });
    }
}
