package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        var randomUser = CommonFunctions.randomString(8);
        app.jamesCli().addUser(String.format("%s@localhost", randomUser), "password"); //создать пользователя (адрес) на почтовом сервере (JamesHelper)

        app.registration().canCreateUser();

        int gg = 0;

        //открыть браузер и заполнить форму создания (браузер) (разместить в классе помощника)
        // отправить письмо с подтверждением на почту (браузер) (разместить в классе помощника)
        // ждём (получить) письмо (MailHelper)
        // извлечь из письма ссылку
        //вернуться в браузер и пройти по полученной из письма ссылке
        //завершаем регистрацию пользователя (браузер) (разместить в классе помощника)
        //проверяем,  что пользователь может залогиниться (в обход пользовательского интерфейса через HttpSessionHelper)
    }
}
