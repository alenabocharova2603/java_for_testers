import model.ContactData;
import org.junit.jupiter.api.Test;

public class CreateContactTest extends TestBase {

    @Test
    public void createContact() {
        openAddNewPage();
        createContact(new ContactData("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com"));
        openHomePage();
    }


    @Test
    public void createContactWithFirstnameOnly() {
        openAddNewPage();
        var emptyContact = new ContactData();
        var contactWithFirstname = emptyContact.withFirstname("some name");
        createContact(contactWithFirstname);
        openHomePage();
    }
}
