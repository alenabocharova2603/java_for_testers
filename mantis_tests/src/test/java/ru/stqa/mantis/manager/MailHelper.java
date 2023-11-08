package ru.stqa.mantis.manager;

import jakarta.mail.*;
import ru.stqa.mantis.model.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class MailHelper extends HelperBase{
    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String username, String password, Duration duration) {
    var start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + duration.toMillis()) {     //Пока текущее время меньше, чем старт + duration, повторяем
        try {
            Folder inbox = getInbox(username, password);
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
            inbox.getStore().close();
            if (result.size() > 0) {
                return result;
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
 throw new RuntimeException("No mail");
    }

    private static Folder getInbox(String username, String password)  {
        try {
            var session = Session.getInstance(new Properties());
            Store store = session.getStore("pop3");
            store.connect("localhost", username, password);
            //store.connect("localhost", "user1@localhost", "password"); //устанавливаем соединение
            var inbox = store.getFolder("INBOX"); //открываем почтовый ящик
            return inbox;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void drain(String username, String password) { //метод удаляет все письма
        try {
            var inbox = getInbox(username, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
