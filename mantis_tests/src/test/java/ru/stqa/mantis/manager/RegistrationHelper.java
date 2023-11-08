package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserRegistration;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void canCreateUser(UserRegistration registration) {
    //openStartPage();
    pushLinkForRegistration();
    fillRegistrationForm(registration);
    clickSignup();

    }

    private void clickSignup() {
        manager.driver().findElement(By.name("Signup"));
    }

    private void pushLinkForRegistration() {
        manager.driver().findElement(By.cssSelector(".back-to-login-link"));
    }

    private void fillRegistrationForm(UserRegistration registration) {
        type(By.name("User1"), registration.username());
        type(By.name("user1@localhost"), registration.email());
    }


}
