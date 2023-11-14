package ru.stqa.mantis.manager;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Configuration;
import io.swagger.client.api.IssuesApi;
import io.swagger.client.api.UserApi;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.model.Identifier;
import io.swagger.client.model.Issue;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import ru.stqa.mantis.model.IssueData;
import ru.stqa.mantis.model.UserRegistration;

public class RestApiHelper extends HelperBase{
    public RestApiHelper(ApplicationManager manager) { // используется при инициализации помощника
        super(manager);
        ApiClient defaultClient = Configuration.getDefaultApiClient(); // создаём API клиент
        ApiKeyAuth Authorization = (ApiKeyAuth) defaultClient.getAuthentication("Authorization"); // вызываем метод, который будет устанавливать ключ авторизации
        Authorization.setApiKey(manager.property("apiKey"));
    }

// теперь все действия, выполняемые в этом помощнике будут осущетсвляться от имени пользователя, которому принадлежит этот ключ, то есть от имени администратора
    public void createIssue(IssueData issueData) {
        Issue issue = new Issue();
        issue.setSummary(issueData.summary());
        issue.setDescription(issueData.description());
        var projectId = new Identifier();
        projectId.setId(issueData.project());
        issue.setProject(projectId);
        var categoryId = new Identifier();
        categoryId.setId(issueData.category());
        issue.setCategory(categoryId);

        IssuesApi apiInstance = new IssuesApi();
        try {
            apiInstance.issueAdd(issue);
        } catch (ApiException e) {
        new RuntimeException(e);
        }
    }

    public void addUser(UserRegistration registration) {
        UserApi apiInstance = new UserApi();
        User user = new User();
        user.setUsername(registration.username());
        user.setRealName(registration.username());
        user.setEmail(registration.email());
        try {
            UserAddResponse result = apiInstance.userAdd(user);
            System.out.println(result);
        } catch (ApiException e) {
            new RuntimeException(e);
        }
    }
}
