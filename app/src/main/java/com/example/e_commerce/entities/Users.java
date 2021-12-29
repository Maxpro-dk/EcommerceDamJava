package com.example.e_commerce.entities;

import java.io.Serializable;
import java.util.Date;

public class Users implements Serializable {
    private String Id;
    private String Full_name;
    private String Email;
    private String Address = null;
    private String Password;
    private String Sexe = null;
    private String city = null;
    private Date birth_day = null;
    private String user_image = null;
    private boolean status = true;

    public Users() {
    }


    public Users(String id, String full_name, String email, String address, String password, String sexe, String city, Date birth_day, String user_image, boolean status) {
        Id = id;
        Full_name = full_name;
        Email = email;
        Address = address;
        Password = password;
        Sexe = sexe;
        this.city = city;
        this.birth_day = birth_day;
        this.user_image = user_image;
        this.status = status;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFull_name() {
        return Full_name;
    }

    public void setFull_name(String full_name) {
        Full_name = full_name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public Date getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(Date birth_day) {
        this.birth_day = birth_day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Users{" +
                "Id='" + Id + '\'' +
                ", Full_name='" + Full_name + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Password='" + Password + '\'' +
                ", Sexe='" + Sexe + '\'' +
                ", city='" + city + '\'' +
                ", birth_day=" + birth_day +
                ", user_image='" + user_image + '\'' +
                ", status=" + status +
                '}';
    }

}
