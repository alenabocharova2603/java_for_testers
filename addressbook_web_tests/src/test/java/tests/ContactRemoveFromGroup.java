package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class ContactRemoveFromGroup extends TestBase{

    @Test
    void canContactRemoveFromGroup() {
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
            var contactListNotInGroup = app.hbm().getContactList();
            if  ( (contactListNotInGroup != null) && (!contactListNotInGroup.isEmpty()) ) {
                contactForDelete = contactListNotInGroup.get(0); // В полученном с БД списке найден контакт без группы
                groupData = groupList.get(0);
                app.contacts().addContactInToGroup(contactForDelete, groupData); // Контакт добавляется в группу
            }
        }

        if (contactForDelete == null) {
            groupData = groupList.get(0);
            app.contacts().createContact(
                    new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com", "", "", "","","",""),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForDelete = contacts.get(0);
        }

        var oldContacts = app.hbm().getContactsInGroup(groupData);
        app.contacts().selectGroupById(groupData);
        app.contacts().removeContactFromGroup(contactForDelete);
        var newContacts = app.hbm().getContactsInGroup(groupData);
        var expectedList = new ArrayList<>(oldContacts);

        ContactData finalContactForDelete = contactForDelete;
        expectedList.removeIf(contactData -> finalContactForDelete.id().equals(contactData.id()));

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        expectedList.sort(compareById);
        newContacts.sort(compareById);

        Assertions.assertEquals(expectedList, newContacts);
    }


}
