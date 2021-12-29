package com.example.e_commerce.entities;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {
    private String Id;
    private String user_id;
    private String Name;
    private String Description;
    private double quantity;
    private double price;
    private double qunatity_sell = 0;
    private Date timestamp;
    private String Category_id;
    private String img_id;
    private boolean status = false;
    private int numberInCart;

    public Product() {
    }


    public Product(String id, String user_id, String name, String description, double quantity, double price, Date timestamp, String category_id, String img_id) {
        Id = id;
        this.user_id = user_id;
        Name = name;
        Description = description;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
        Category_id = category_id;
        this.img_id = img_id;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getImg_id() {
        return img_id;
    }

    public void setImg_id(String img_id) {
        this.img_id = img_id;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQunatity_sell() {
        return qunatity_sell;
    }

    public void setQunatity_sell(double qunatity_sell) {
        this.qunatity_sell = qunatity_sell;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getCategory_id() {
        return Category_id;
    }

    public void setCategory_id(String category_id) {
        Category_id = category_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public  float getAvailable(){
        return  (int)(this.quantity - this.qunatity_sell);
    }


    @Override
    public String toString() {
        return "Product{" +
                "Id='" + Id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", qunatity_sell=" + qunatity_sell +
                ", timestamp=" + timestamp +
                ", Category_id='" + Category_id + '\'' +
                ", img_id='" + img_id + '\'' +
                ", status=" + status +
                '}';
    }
}
