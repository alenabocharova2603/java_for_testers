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

        app.registration().canConfirmUser(CommonFunctions.randomString(10), "password");
        app.http().login(registration.username(),"password");
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    public static List<UserRegistration> singleUser() {
        var name = CommonFunctions.randomString(10);
        return List.of(new UserRegistration()
                .withUsername(name)
                .withEmail(String.format("%s@localhost", name)));
    }

    @ParameterizedTest
    @MethodSource("randomUser") //тест должен создавать пользователя через администратора
        void canCreateUser(UserRegistration registration) {
//       var email = String.format("%s@localhost", user);
//       app.jamesApi().addUser(email, "password");

        app.jamesCli().addUser(registration.email(), "password");

       app.registration().startCreation(registration); //нужен новый метод
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

            app.registration().canConfirmUser(CommonFunctions.randomString(10), "password");
            app.http().login(registration.username(),"password");
            Assertions.assertTrue(app.http().isLoggedIn());
//       var messages = app.mail().receive(email, "password", Duration.ofSeconds(10));
//       var url = CommonFunctions.extractUrl(messages.get(0).content());
//       app.registration().finishCreation(url,"password");
//       app.http().login(user,"password");
//       Assertions.assertTrue(app.http().isLoggedIn());


    }
}
