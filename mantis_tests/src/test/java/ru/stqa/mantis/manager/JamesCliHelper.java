package ru.stqa.mantis.manager;

import org.openqa.selenium.io.CircularOutputStream;
import org.openqa.selenium.os.CommandLine;

public class JamesCliHelper extends HelperBase{
    public JamesCliHelper(ApplicationManager manager) {
        super(manager); // конструктор в котором запонимается ссылка на менеджер
    }

    public void addUser(String email, String password) { //метод, который вызывает команду как в консоли (java -cp "james-server-jpa-app.lib/*" org.apache.james.cli.ServerCmd AddUser user1@localhost password)
        CommandLine cmd = new CommandLine(
                "java", "-cp", "\"james-server-jpa-app.lib/*\"",
                "org.apache.james.cli.ServerCmd",
                "AddUser",email,password);
        cmd.setWorkingDirectory(manager.property("james.workingDir"));//установить рабочую директорию
        CircularOutputStream out = new CircularOutputStream();
        cmd.copyOutputTo(out);
        cmd.execute(); // запуск команды
        cmd.waitFor(); // ожидание завершения работы команды
        System.out.println(out);
    }
}
