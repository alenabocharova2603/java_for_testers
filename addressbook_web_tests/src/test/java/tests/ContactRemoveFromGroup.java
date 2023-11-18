package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContactRemoveFromGroup extends TestBase{

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
    void canContactRemoveFromGroup(ContactData contact) {

        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }

        var groupList = app.hbm().getGroupList();

        ContactData contactForDelete = null;
        GroupData groupData = null;
        for (int i = 0; i < groupList.size() - 1; i++) {
            var contactListInGroup = app.hbm().getContactsInGroup(groupList.get(i));
            if  ( (contactListInGroup != null) && (!contactListInGroup.isEmpty()) ) {
                contactForDelete = contactListInGroup.get(0); //В группе нашелся контакт
                groupData = groupList.get(i);
                break;
            }
        }

        if (contactForDelete == null) {
            var contactListNotInGroup = app.hbm().getContactsNotInGroup();
            if  ( (contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty()) ) {
                contactForDelete = contactListNotInGroup.get(0); // В полученном с БД списке найден контакт без группы
                groupData = groupList.get(0);
                app.contacts().addContactInToGroup(contactForDelete, groupData); // Контакт добавляется в группу
            }
        }

        if (contactForDelete == null) {
            app.contacts().createContact(contact);
            contact = contact.withId(app.hbm().getIdContactByName(contact.firstname()));
            groupData = groupList.get(0);
            app.contacts().addContactInToGroup(contact, groupData);
            contactForDelete = contact;
        }

        var oldContacts = app.hbm().getContactsInGroup(groupData);
        app.contacts().selectGroupById(groupData);
        app.contacts().removeContactFromGroup(contactForDelete);
        var newContacts = app.hbm().getContactsInGroup(groupData);
        var expectedList = new ArrayList<>(oldContacts);

        ContactData finalContactForDelete = contactForDelete;
        expectedList.removeIf(contactData -> finalContactForDelete.id().equals(contactData.id()));

        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newContacts));
    }


}
