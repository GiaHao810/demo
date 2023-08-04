package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.EnableMBeanExport;

@Entity
public class Products {
//    id is primary key
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "NAME")
    private String productName;
    @Column(name = "YEARS")
    private int years;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "URL")
    private String url;

    public Products() {}

    public Products(String productName, int years, Double price, String url) {
        this.productName = productName;
        this.years = years;
        this.price = price;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year=" + years +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getYear() {
        return years;
    }

    public Double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
