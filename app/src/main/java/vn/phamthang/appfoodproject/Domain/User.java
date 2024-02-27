package vn.phamthang.appfoodproject.Domain;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String email;
    private String userName;
    private String password;
    private String address;
    private String numberPhone;

    private String dateCreated;

    // Tạo constructor
    public User(String id, String email, String password, String userName,String address, String numberPhone,String dateCreated) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.address = address;
        this.numberPhone = numberPhone;
        this.dateCreated = dateCreated;
    }

    public User() {
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
}
