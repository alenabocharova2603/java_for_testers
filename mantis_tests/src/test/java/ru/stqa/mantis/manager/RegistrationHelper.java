package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserRegistration;

import java.time.Duration;

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

    public void startCreation(DeveloperMailUser developerMailUser, String email) {
        openPageForCreateUser();
        clickCreateNewAccount();
        fillCreateNewAccount(developerMailUser, email);
        clickCreateUser();
        waitElementAppears(new By.ByXPath("//*[text()[contains(.,'Password sends the confirmation URL via e-mail')]]"), Duration.ofMinutes(5));
    }

    private void openPageForCreateUser() {
        linkManage();
        openUsers();
    }

    private void openUsers() {
        manager.driver().findElement(By.xpath("//a[@href='/mantisbt-2.26.0/manage_user_page.php']")).click();
    }

    private void linkManage() {
        manager.driver().findElement(By.xpath("//a[@href='/mantisbt-2.26.0/manage_overview_page.php']")).click();
    }

    private void clickCreateUser() {
        manager.driver().findElement(By.xpath("//input[@value='Create User']")).click();
    }

    private WebElement waitElementAppears (By.ByXPath xPath, Duration duration) {
        return new WebDriverWait(manager.driver(), duration).until(ExpectedConditions.presenceOfElementLocated(xPath));
    }

    private void fillCreateNewAccount(UserRegistration registration) {
        type(By.name("username"), registration.username());
        type(By.name("realname"), registration.realname());
        type(By.name("email"), registration.email());
    }

    private void fillCreateNewAccount(DeveloperMailUser developerMailUser, String email) {
        type(By.name("username"), developerMailUser.name());
        type(By.name("realname"), developerMailUser.name());
        type(By.name("email"), email);
    }

    private void clickCreateNewAccount() {
        manager.driver().findElement(By.xpath("//a[@href='manage_user_create_page.php']")).click();
    }
}
