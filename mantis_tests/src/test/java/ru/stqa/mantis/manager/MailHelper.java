package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase{
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password) {

        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost",username, password); //устанавливаем соединение
            var inbox = store.getFolder("INBOX"); //открываем почтовый ящик
            inbox.open(Folder.READ_ONLY);//в качестве параметра передаём режим, в котором мы открываем этот почтовый ящик
            var messages = inbox.getMessages(); // читаем почту (На данный момент возвращается массив, который нужно преобразовать в список модельных объектов)

           var result =  Arrays.stream(messages)
                   .map(m -> {
                       try {
                           return new MailMessage() //превращаем массив в поток
                                    .withFrom(m.getFrom()[0].toString()) // в качестве параметра используем трансформатор одних объектов в другие. Берем первого отправителя
                                    .withContent((String) m.getContent());
                       } catch (MessagingException | IOException e) {
                           throw new RuntimeException(e);
                       }
                   })
                    .toList(); //преобразуем в возвращаемый список
            inbox.close();
            store.close();
            return result;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
