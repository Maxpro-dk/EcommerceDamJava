package com.example.e_commerce.entities;

import java.io.Serializable;

public class Commande_line implements Serializable {
    private String Id;
    private String order_id;
    private String product_id;
    private double price;
    private double OderQuantity;
    private double total_price;

    public Commande_line() {
    }

    public Commande_line(String id, String order_id, String product_id, double price, double oderQuantity, double total_price) {
        Id = id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.price = price;
        OderQuantity = oderQuantity;
        this.total_price = total_price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOderQuantity() {
        return OderQuantity;
    }

    public void setOderQuantity(double oderQuantity) {
        OderQuantity = oderQuantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Commande_line{" +
                "Id='" + Id + '\'' +
                ", order_id='" + order_id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", price=" + price +
                ", OderQuantity=" + OderQuantity +
                ", total_price=" + total_price +
                '}';
    }
}
