package Sixtel;

/**
 * Created by branden on 2/16/16 at 12:50.
 */
public class Contact {

    private String name, phone, email;



    //Constructor

    public Contact(String name, String phone, String email) {
        setName(name);
        setPhone(phone);
        setEmail(email);
    }


    //G's and S'


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}