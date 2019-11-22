package com.deals.models;

import com.deals.models.enums.Category;
import com.deals.models.enums.EngineType;
import com.deals.models.enums.TransmissionType;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "articles")

public class Article {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "imageLink")
    private String imageLink;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne(targetEntity = User.class)
    private User user;
    @Column(name = "power")
    private int power;
    @Column(name = "eurostandard")
    private String eurostandard;
    @Column(name = "mileage")
    private int mileage;
    @Column(name = "color")
    private String color;
    @Column(name = "brand")
    private String brand;
    @Column(name = "model")
    private String model;
    @Column(name = "region")
    private String region;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;
    @Column(name = "engine")
    @Enumerated(EnumType.STRING)
    private EngineType engine;
    @Column(name = "manufacturerYear")
    private int manufacturerYear;


    public Article() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String image) {
        this.imageLink = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int poster) {
        this.power = poster;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEurostandard() {
        return eurostandard;
    }

    public void setEurostandard(String eurostandard) {
        this.eurostandard = eurostandard;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public void setManufacturerYear(int manufacturerYear) {
        this.manufacturerYear = manufacturerYear;
    }

    public int getManufacturerYear() {
        return manufacturerYear;
    }
}
