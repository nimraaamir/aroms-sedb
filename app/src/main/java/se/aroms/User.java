package se.aroms;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String cnic;
    private String telephone;
    private String email;
    private String password;
    private String dob;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    User(){}

    public User(String name, String cnic, String telephone, String email, String password, String dob,String role) {
        this.name = name;
        this.cnic = cnic;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


}
