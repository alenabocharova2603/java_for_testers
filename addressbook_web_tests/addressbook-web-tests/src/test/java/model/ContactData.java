package model;


public record ContactData(String firstname, String lastname, String address, String mobile, String email) {
    public ContactData() {
        this("","","","","");
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(firstname, this.lastname, this.address, this.mobile, this.email);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.firstname, lastname, this.address, this.mobile, this.email);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.lastname, address, this.mobile, this.email);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.lastname, this.address, mobile, this.email);
    }
    public ContactData withEmail(String lastname) {
        return new ContactData(this.firstname, this.lastname, this.address, this.mobile, email);
    }
}