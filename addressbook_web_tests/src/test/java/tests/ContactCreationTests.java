package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstname : List.of("","contact firstname")) {
            for (var lastname : List.of("", "contact lastname")) {
               for (var address : List.of("","contact address")) {
                   for (var mobile : List.of("","contact mobile")) {
                       for (var email : List.of("","contact email")) {
                           result.add(new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withMobile(mobile).withEmail(email));
                       }
                   }
               }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData()
                    .withFirstname(randomString(i * 10))
                    .withLastname(randomString(i * 10))
                    .withAddress(randomString(i * 10))
                    .withMobile(randomString(i * 10))
                    .withEmail(randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withLastname("")
                .withAddress("")
                .withMobile(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
        app.contacts().openHomePage();
    }

    public static List<ContactData>  negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstname' ","","","","")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(newContacts, oldContacts);
        app.contacts().openHomePage();
    }
}
