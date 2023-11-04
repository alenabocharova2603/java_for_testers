package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTest extends  TestBase {

    @Test
    void testPhones() {
     var contacts = app.hbm().getContactList(); // добавить проверку предусловий, так как контакты могут быть пустыми
     var contact = contacts.get(0);
     var phones = app.contacts().getPhones(contact);
     var expected = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }
}
