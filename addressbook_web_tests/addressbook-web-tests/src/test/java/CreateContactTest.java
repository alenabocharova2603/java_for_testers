import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CreateContactTest extends TestBase{

    @Test
    public void createContact() {
        openAddNewPage();
        CreateContact("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com");
        openHomePage();

    }

}
