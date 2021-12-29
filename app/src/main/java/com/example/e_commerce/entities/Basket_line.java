package com.example.e_commerce.entities;

import java.io.Serializable;

public class Basket_line implements Serializable {

    private String id;
    private String bask_id;
    private String product_id;
    private double orderQuanttity;
    private boolean status = false;

    public Basket_line() {
    }

    public Basket_line(String id, String bask_id, String product_id, double orderQuanttity) {
        this.id = id;
        this.bask_id = bask_id;
        this.product_id = product_id;
        this.orderQuanttity = orderQuanttity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBask_id() {
        return bask_id;
    }

    public void setBask_id(String bask_id) {
        this.bask_id = bask_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getOrderQuanttity() {
        return orderQuanttity;
    }

    public void setOrderQuanttity(double orderQuanttity) {
        this.orderQuanttity = orderQuanttity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Basket_line{" +
                "id='" + id + '\'' +
                ", bask_id='" + bask_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", orderQuanttity=" + orderQuanttity +
                ", status=" + status +
                '}';
    }
}
