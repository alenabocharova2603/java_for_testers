package model;


public record ContactData(
        String id,
        String firstname,
        String lastname,
        String address,
        String mobile,
        String email,
        String home,
        String work,
        String secondary,
        String email2,
        String email3,
        String address2) {
    public ContactData() {
        this("", "","","","","", "", "", "","","","");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.secondary, this.email2, this.email3,this.address2);
    }
    public ContactData withFirstname(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.address, this.mobile, this.email,this.home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.address, this.mobile, this.email, this.home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, address, this.mobile, this.email, this.home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }
    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, mobile, this.email, this.home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }
    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, email, this.home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }
    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, home, this.work, this.secondary,this.email2, this.email3,this.address2);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, work, this.secondary,this.email2, this.email3,this.address2);
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, this.work, secondary,this.email2, this.email3,this.address2);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.secondary,email2, this.email3,this.address2);
    }
    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.secondary,this.email2, email3,this.address2);
    }

    public ContactData withAddress2(String address2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.address, this.mobile, this.email, this.home, this.work, this.secondary,this.email2, this.email3, address2);
    }
}
