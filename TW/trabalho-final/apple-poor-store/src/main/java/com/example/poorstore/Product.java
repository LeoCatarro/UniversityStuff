package com.example.poorstore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column
    private String productName,description, category, imgUrl, fullDescription;

    @Column
    private Integer price, quantity;


    public Product() {}

    public Product(String productName, String description, Integer price, Integer quantity, String category, String imgUrl, String fullDescription) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imgUrl = imgUrl;
        this.fullDescription = fullDescription;

    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, productName='%s', price='%d', quantity='%d', description='%s', category='%s', imgUrl='%s', fullDescription='%s']",
                id, productName, price, quantity, description, category, imgUrl, fullDescription);
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getCategory() { return category; }

    public String getImgUrl() { return imgUrl; }

    public String getFullDescription() { return fullDescription; }
}
