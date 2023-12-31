package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
//        for (var firstname : List.of("","Nina")) {
//            for (var lastname : List.of("", "Ivanova")) {
//                for (var address : List.of("", "3 Internacounal, 243")) {
//                    for (var mobile : List.of("", "+98567841456")) {
//                        for (var email : List.of("", "nina_kot@koler.com")) {
//                            for (var photo : List.of("", "src/test/resources/images/avatar.png")) {
//                                result.add(new ContactData().withFirstname(firstname).withLastname(lastname).withAddress(address).withMobile(mobile).withEmail(email).withPhoto(randomFile("src/test/resources/images/")));
//                            }
//                        }
//                    }
//                }
//            }
//        }

        var json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json,  new TypeReference<List<ContactData>>() {});
        result.addAll(value);

        return result;
    }
    public static List<ContactData> singleRandomContact() {
       return List.of(new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(expectedList, newContacts);
        });
        //app.contacts().openHomePage();

    }

    public static List<ContactData>  negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "firstname' ","","","","", "", "", "","","","")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContacts(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(newContacts, oldContacts);
        app.contacts().openHomePage();
        });
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstname(CommonFunctions.randomString(10))
                .withLastname(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10));
        Allure.step("Validating results", step -> {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
    });
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact,group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
        });
    }

}
