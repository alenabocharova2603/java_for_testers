package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.UserRegistration;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void canCreateUser(UserRegistration registration) {
    pushLinkForRegistration();
    fillRegistrationForm(registration);
    clickSignup();

    }

    private void clickSignup() {
        manager.driver().findElement(By.xpath("//input[@value='Signup']")).click();
    }

    private void pushLinkForRegistration() {
        manager.driver().findElement(By.xpath("//a[@href='signup_page.php']")).click();
    }

    private void fillRegistrationForm(UserRegistration registration) {
        type(By.name("username"), registration.username());
        type(By.name("email"), registration.email());
    }

    public void canConfirmUser(String realName, String password) {
        type(By.name("realname"), realName);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        updateUser();
    }

    private void updateUser() {
        manager.driver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void startCreation(UserRegistration registration) {
        openPageForCreateUser();
        clickCreateNewAccount();
        fillCreateNewAccount(registration);
        clickCreateUser();

    }

    private void clickCreateUser() {
        manager.driver().findElement(By.xpath("//input[@value='Create User']")).click();
    }

    private void fillCreateNewAccount(UserRegistration registration) {
        type(By.name("username"), registration.username());
        type(By.name("realname"), registration.username());
        type(By.name("email"), registration.email());
    }

    private void clickCreateNewAccount() {
        manager.driver().findElement(By.xpath("//a[@href='manage_user_create_page.php']")).click();
    }
}
