package com.cs50.userregistration.model;

public class DataHolder {
    private String ProductName;
    private int price;
    private String type;
    private String desc;
    private String image;

    public DataHolder() {
    }

    public DataHolder(String productName, int price, String type, String desc, String image) {
        ProductName = productName;
        this.price = price;
        this.type = type;
        this.desc = desc;
        this.image = image;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
