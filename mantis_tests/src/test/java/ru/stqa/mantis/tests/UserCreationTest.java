package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserCreationTest extends TestBase{

    DeveloperMailUser user;

    @Test
    void canCreateUserInDevelopermail() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

    }
    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
