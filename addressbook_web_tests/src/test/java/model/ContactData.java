package model;


public record ContactData(String id, String firstname, String lastname, String address, String mobile, String email, String photo) {
    public ContactData() {
        this("", "","","","","","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.mobile, this.email,this.photo);
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.mobile, this.email,this.photo);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.mobile, this.email,this.photo);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.mobile, this.email,this.photo);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, mobile, this.email,this.photo);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, email,this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, photo);
    }
}
