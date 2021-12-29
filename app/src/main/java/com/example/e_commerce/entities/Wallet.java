package com.example.e_commerce.entities;

import java.io.Serializable;

public class Wallet implements Serializable {
    private String id;
    private String user_id;
    private double Amount;

    public Wallet() {
    }

    public Wallet(String id, String user_id, double amount) {
        this.id = id;
        this.user_id = user_id;
        Amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", Amount=" + Amount +
                '}';
    }
}
