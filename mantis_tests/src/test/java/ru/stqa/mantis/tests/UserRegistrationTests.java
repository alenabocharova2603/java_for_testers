package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserRegistration;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase {

    private DeveloperMailUser developerMailUser;

    @ParameterizedTest
    @MethodSource("singleUser")
    void canRegisterUser(UserRegistration registration) {
        app.jamesCli().addUser(registration.email(), "password"); //создать пользователя (адрес) на почтовом сервере (JamesHelper)

        app.registration().canCreateUser(registration);
        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(60));
        var text = messages.get(0).content(); // текст из которого нужно извлечь ссылку
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            //System.out.println(url);
        }
        app.driver().get(url);

        app.registration().canConfirmUser(registration.username(), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    public static List<UserRegistration> singleUser() {
        var name = CommonFunctions.randomString(10);
        return List.of(new UserRegistration()
                .withUsername(name)
                .withRealname(name)
                .withEmail(String.format("%s@localhost", name)));
    }

    @ParameterizedTest
    @MethodSource("singleUser")
        //тест должен создавать пользователя через администратора
    void canCreateUserByJames(UserRegistration registration) {
        app.jamesCli().addUser(registration.email(), "password");
        app.loginSession().login("administrator", "root");

        app.registration().startCreation(registration);

        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(60));
        var text = messages.get(0).content(); // текст из которого нужно извлечь ссылку
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            //System.out.println(url);
        }
        app.driver().get(url);

        app.registration().canConfirmUser(registration.username(), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canCreateUserByMail() {
        var password = "password";
        developerMailUser = app.developerMail().addUser();
        app.loginSession().login("administrator", "root");

        var email = String.format("%s@developermail.com", developerMailUser.name());
        app.registration().startCreation(developerMailUser, email);

        var message = app.developerMail().receive(developerMailUser, Duration.ofMinutes(5));

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        String url = null;
        if (matcher.find()) {
            url = message.substring(matcher.start(), matcher.end());
            //System.out.println(url);
        }
        app.driver().get(url);

        app.registration().canConfirmUser(developerMailUser.name(), password);
        app.http().login(developerMailUser.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    //@AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(developerMailUser);
    }

    @ParameterizedTest
    @MethodSource("singleUser")
    void canCreateUserByJamesUseRestApi(UserRegistration registration) {
        app.jamesApi().addUser(registration.email(), "password");
        app.rest().addUser(registration);
        var messages = app.mail().receive(registration.email(), "password", Duration.ofSeconds(60));
        var text = messages.get(0).content(); // текст из которого нужно извлечь ссылку
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = null;
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            //System.out.println(url);
        }
        app.driver().get(url);

        app.registration().canConfirmUser(registration.username(), "password");
        app.http().login(registration.username(), "password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }


}
