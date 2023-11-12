package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.UserRegistration;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class UserRegistrationTests extends TestBase{

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
        app.http().login(registration.username(),"password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    public static List<UserRegistration> singleUser() {
        var name = CommonFunctions.randomString(10);
        return List.of(new UserRegistration()
                .withUsername(name)
                .withEmail(String.format("%s@localhost", name)));
    }

    public static Stream<String> randomUser() {
        UserRegistration userRegistration = new UserRegistration("", "");
        return Stream.of(CommonFunctions.randomString(8));

    }
    @ParameterizedTest
    @MethodSource("singleUser") //тест должен создавать пользователя через администратора
        void canCreateUser(UserRegistration registration) {
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
        app.http().login(registration.username(),"password");
        Assertions.assertTrue(app.http().isLoggedIn());

        


    }
}
