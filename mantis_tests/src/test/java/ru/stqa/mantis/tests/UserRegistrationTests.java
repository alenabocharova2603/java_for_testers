package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase{

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        //открыть браузер и заполнить форму создания (браузер) (разместить в классе помощника)
        // отправить письмо с подтверждением на почту (браузер) (разместить в классе помощника)
        // получить письмо (MailHelper)
        // извлечь из письма ссылку
        //вернуться в браузер и пройти по полученной из письма ссылке
        //завершаем регистрацию пользователя (браузер) (разместить в классе помощника)
        //проверяем,  что пользователь может залогиниться (в обход пользовательского интерфейса через HttpSessionHelper)
    }
}
