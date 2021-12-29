package com.example.e_commerce.entities;

import java.io.Serializable;

public class Preference implements Serializable {
    private String id;
    private String product_id;
    private boolean status = false;

    public Preference() {
    }

    public Preference(String id, String product_id) {
        this.id = id;
        this.product_id = product_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Preference{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", status=" + status +
                '}';
    }
}
