package com.example.e_commerce.entities;

import java.io.Serializable;
import java.util.Date;

public class Publicity implements Serializable {
    private String id;
    private String product_id;
    private String images;
    private Date create_at;
    private Date end;
    private double day_price;
    private double total_price;

    public Publicity() {
    }

    public Publicity(String id, String product_id, String images, Date create_at, Date end, double day_price, double total_price) {
        this.id = id;
        this.product_id = product_id;
        this.images = images;
        this.create_at = create_at;
        this.end = end;
        this.day_price = day_price;
        this.total_price = total_price;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public double getDay_price() {
        return day_price;
    }

    public void setDay_price(double day_price) {
        this.day_price = day_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "Publicity{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", images='" + images + '\'' +
                ", create_at=" + create_at +
                ", end=" + end +
                ", day_price=" + day_price +
                ", total_price=" + total_price +
                '}';
    }
}
