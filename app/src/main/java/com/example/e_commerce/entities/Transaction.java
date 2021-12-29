package com.example.e_commerce.entities;

import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private String id;
    private String wallet_id;
    private double Amount;
    private Date date;
    private String Reference_id;
    private String Paiement;
    private String Other;

    public Transaction() {
    }

    public Transaction(String id, String wallet_id, double amount, Date date, String reference_id, String paiement, String other) {
        this.id = id;
        this.wallet_id = wallet_id;
        Amount = amount;
        this.date = date;
        Reference_id = reference_id;
        Paiement = paiement;
        Other = other;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReference_id() {
        return Reference_id;
    }

    public void setReference_id(String reference_id) {
        Reference_id = reference_id;
    }

    public String getPaiement() {
        return Paiement;
    }

    public void setPaiement(String paiement) {
        Paiement = paiement;
    }

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", wallet_id='" + wallet_id + '\'' +
                ", Amount=" + Amount +
                ", date=" + date +
                ", Reference_id='" + Reference_id + '\'' +
                ", Paiement='" + Paiement + '\'' +
                ", Other='" + Other + '\'' +
                '}';
    }
}
