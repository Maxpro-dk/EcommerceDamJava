package com.example.e_commerce.entities;

import java.io.Serializable;
import java.util.Date;

public class Image implements Serializable {
    private String productId;
    private Date timestamp;
    private String Url;

    public Image() {
    }

    public Image(String imgId, Date timestamp, String url) {
        productId = imgId;
        this.timestamp = timestamp;
        Url = url;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "ImgId='" + productId + '\'' +
                ", timestamp=" + timestamp +
                ", Url='" + Url + '\'' +
                '}';
    }
}
