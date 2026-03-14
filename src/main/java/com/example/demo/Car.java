package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String model;

    @Column(name="price_per_day")
    private double pricePerDay;

    @Column(name="image_url")
    private String imageUrl;

    public Car(){}

    public Long getId(){ return id; }

    public String getName(){ return name; }

    public void setName(String name){ this.name=name; }

    public String getModel(){ return model; }

    public void setModel(String model){ this.model=model; }

    public double getPricePerDay(){ return pricePerDay; }

    public void setPricePerDay(double pricePerDay){ this.pricePerDay=pricePerDay; }

    public String getImageUrl(){ return imageUrl; }

    public void setImageUrl(String imageUrl){ this.imageUrl=imageUrl; }

}