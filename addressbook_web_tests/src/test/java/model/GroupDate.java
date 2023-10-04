package model;


public record GroupDate(String name, String header, String footer) {

 public GroupDate(){
     this("","","");
 }

    public GroupDate withName(String name) {
     return new GroupDate(name, this.header, this.footer);
    }
    public GroupDate withHeader(String header) {
        return new GroupDate(this.name, header, this.footer);
    }
    public GroupDate withFooter(String footer) {
        return new GroupDate(this.name, this.header, footer);
    }
}