package com.example.e_commerce.entities;

import java.io.Serializable;

public class Commande implements Serializable {
    private String Id;
    private String user_id;

    public Commande() {
    }

    public Commande(String id, String user_id) {
        Id = id;
        this.user_id = user_id;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "Id='" + Id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
