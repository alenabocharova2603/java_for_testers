package manager.hbm;
import com.beust.jcommander.IStringConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {



    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String firstname;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "address")
    public String address;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "email")
    public String email;

    public Date deprecated = null;
    public String middlename = "";
    public String nickname =  "";
    public String company =  "";
    public String title =  "";
    public String work =  "";
    public String fax =  "";
    public String email2 =  "";
    public String email3 =  "";
    public String im =  "";
    public String im2 =  "";
    public String im3 =  "";
    public String homepage =  "";
    public Integer bday =  new Integer(0);
    public Integer bmonth =  new Integer(0);
    public Integer byear =  new Integer(0);
    public Integer  aday =  new Integer(0);
    public Integer amonth =  new Integer(0);
    public Integer ayear =  new Integer(0);
    public String address2 =  "";
    public String phone2 =  "";
    public String notes =  "";
    public String photo =  "";
    public String x_vcard =  "";
    public String x_activesync =  "";
    public Date created =  new Date();
    public Date modified =  new Date();
    public String role =  "";
    public String home =  "";
    public String addr_long =  "";
    public String addr_lat =  "";
    public String addr_status =  "";


    public ContactRecord() {}

    public ContactRecord(int id, String firstname, String lastname, String address, String mobile, String email) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
    }
}

