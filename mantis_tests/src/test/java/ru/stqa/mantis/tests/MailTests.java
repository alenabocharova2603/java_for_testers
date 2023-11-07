package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class MailTests extends TestBase{

    @Test
    void canReceiveEmail() {
       var messages = app.mail().receive("user1@localhost", "password");
    }
}
