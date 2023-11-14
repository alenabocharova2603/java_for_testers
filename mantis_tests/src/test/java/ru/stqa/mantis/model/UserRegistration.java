package ru.stqa.mantis.model;

public record UserRegistration(String username, String realname, String email) {
    public UserRegistration() {
        this("","","");
    }

    public UserRegistration withUsername(String username) {
        return new UserRegistration(username, this.realname, this.email);
    }
    public UserRegistration withRealname(String realname) {
        return new UserRegistration(this.username, realname, this.email);
    }

    public UserRegistration withEmail(String email) {
        return new UserRegistration(this.username, this.realname, email);
    }
}
