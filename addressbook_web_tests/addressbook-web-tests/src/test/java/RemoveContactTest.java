import org.junit.jupiter.api.Test;

public class RemoveContactTest extends TestBase {


    @Test
    public void untitled() {
        openHomePage();
        if (!isContactPresent()) {
            CreateContact("Nina", "Ivanova", "3 Internacounal, 243", "+98567841456", "nina_kot@koler.com");
        }
        RemoveContact();
    }

}
