package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;
//тесты на проверку того, что информация в адресной книге предствалена корректно (данные между БД и пользовательским интерфейсом совпадают)
public class ContactInfoTest extends  TestBase {



    @Test
    void testEmailsAddressesPhones() {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData("", "Nina", "Ivanova", "3 Internacounal, 243",
                    "+98567841456", "nina_kot@koler.com", "+4954723212453", "+1214314323", "+3225331144",
                    "Joew@yandex.ru", "outlook@outlook.com", "75 3rd Ave, New York, NY 10003, USA"));
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);

        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);

        var addresses = app.contacts().getAddresses(contact);
        expected = Stream.of(contact.address()/*, contact.address2()*/) // на главной странице нет информации о втором адрессе
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, addresses);

        var phones = app.contacts().getPhones(contact);
        expected = Stream.of(contact.home(), contact.mobile(), contact.work())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, phones);
    }

    }

