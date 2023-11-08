package ru.stqa.mantis.model;

public record UserRegistration(String username, String email) {
    public UserRegistration() {
        this("","");
    }

    public UserRegistration withUsername(String username) {
        return new UserRegistration(username, this.email);
    }

    public UserRegistration withEmail(String email) {
        return new UserRegistration(this.username, email);
    }
}
