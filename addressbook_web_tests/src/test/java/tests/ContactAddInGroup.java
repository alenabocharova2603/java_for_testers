package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactAddInGroup extends TestBase{

    @Test
    void canAddContactInGroup() {
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
                    new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com", "", "", "", "", "", ""),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForDelete = contacts.get(0);
        }
    }
}
