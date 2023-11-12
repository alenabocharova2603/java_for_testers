package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.manager.ApplicationManager;
import ru.stqa.mantis.manager.HelperBase;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager manager) {
        super(manager);
    }

//    public void startCreationInDevelopermail(String user, String email) {
//        if (!manager.session().isLoggedIn()) {
//            manager.session().login(manager.property("web.username"),manager.property());
//        }
//        manager.driver().get(String.format("%s/manage_user_create_page.php", manager.property()));
//        type(By.name("username"),user);
//        type(By.name("realname"),user);
//        type(By.name("email"),email);
//        click(By.cssSelector("input[type='submit']"));
//    }
}
