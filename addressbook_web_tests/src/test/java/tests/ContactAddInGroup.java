package tests;

import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactAddInGroup extends TestBase{

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
    void canAddContactInGroup(ContactData contact) {
        Allure.step("Checking precondition in Groups", step -> {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        });
        var groupList = app.hbm().getGroupList();

        ContactData contactForAddToGroup = null;
        GroupData groupData = groupList.get(0);
        var oldContactListInGroup = app.hbm().getContactsInGroup(groupData);

        var contactListNotInGroup = app.hbm().getContactsNotInGroup();
        if  ( (contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty()) ) {
            contactForAddToGroup = contactListNotInGroup.get(0); // В полученном с БД списке найден контакт без группы
            app.contacts().addContactInToGroup(contactForAddToGroup, groupData); // Контакт добавляется в группу
        }

        if (contactForAddToGroup == null) {
            app.contacts().createContact(contact);
            contact = contact.withId(app.hbm().getIdContactByName(contact.firstname()));
            app.contacts().addContactInToGroup(contact, groupData);
            contactForAddToGroup = contact;
        }

        var expectedContactListInGroup = app.hbm().getContactsInGroup(groupData);
        var newContactListInGroup = new ArrayList<>(oldContactListInGroup);
        newContactListInGroup.add(contactForAddToGroup);
        Allure.step("Validating results", step -> {
        Assertions.assertEquals(Set.copyOf(expectedContactListInGroup), Set.copyOf(newContactListInGroup));
        });
    }
}
