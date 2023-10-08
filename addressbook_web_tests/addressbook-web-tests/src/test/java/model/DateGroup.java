package model;


public record DateGroup(String name, String header, String footer) {
    public DateGroup() {
        this("","","");
    }

    public DateGroup withName(String name) {
        return new DateGroup(name, this.header, this.footer);
    }
    public DateGroup withHeader(String header) {
        return new DateGroup(this.name, header, this.footer);
    }
    public DateGroup withFooter(String footer) {
        return new DateGroup(this.name, this.header, footer);
    }
}