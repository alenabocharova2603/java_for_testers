package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {


    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canRemoveAllContactAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0,  app.contacts().getContactCount());
    }

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
                contactForDelete = contactListInGroup.get(0);
                groupData = groupList.get(i);
                break;
            }
        }
        if (contactForDelete == null) {
            groupData = groupList.get(0);
            app.contacts().createContact(
                    new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"),
                    groupData
            );
            var contacts = app.hbm().getContactsInGroup(groupData);
            contactForDelete = contacts.get(0);
        }
        var oldContacts = app.hbm().getContactsInGroup(groupData);
        app.contacts().selectGroupById(groupData);
        app.contacts().removeContactFromGroup(contactForDelete);
        var newContats = app.hbm().getContactsInGroup(groupData);
        var expectedList = new ArrayList<>(oldContacts);
        /*ContactData finalContactForDelete = contactForDelete;
        expectedList.removeIf(contactData -> contactData.id() == finalContactForDelete.id());*/
    }


}
