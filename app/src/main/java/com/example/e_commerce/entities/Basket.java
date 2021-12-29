package com.example.e_commerce.entities;

import java.io.Serializable;

public class Basket implements Serializable {
    private String Id_basket;
    private String user_id;

    public Basket() {
    }

    public Basket(String id_basket, String user_id) {
        Id_basket = id_basket;
        this.user_id = user_id;
    }

    public String getId_basket() {
        return Id_basket;
    }

    public void setId_basket(String id_basket) {
        Id_basket = id_basket;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
