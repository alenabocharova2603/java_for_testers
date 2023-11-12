package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }
    public void login(String user, String password) {
        type(By.name("username"), user);
        click(By.xpath("//input[@value=\'Login\']"));
        type(By.name("password"), password);
        click(By.xpath("//input[@value=\'Login\']"));
    }
}
