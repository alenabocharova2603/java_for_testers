package ru.stqa.mantis.manager.developermail;

import ru.stqa.mantis.model.DeveloperMailUser;

public record AddUserResponse (Boolean success, Object errors, DeveloperMailUser result) {
}
