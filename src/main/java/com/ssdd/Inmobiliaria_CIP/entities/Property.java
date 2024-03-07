package com.ssdd.Inmobiliaria_CIP.entities;

import java.time.LocalDate;

public class Property {
    private Long id;
    private String name;
    private double price;
    private String type;
    private int rooms;
    private int bathrooms;
    private double sqMetres;
    private String adress;
    private String description;
    private LocalDate constructionDate;

    public Property() {
    }

    public Property(String name, double price, String type, int rooms, int bathrooms, double sqMetres, String adress, String description, LocalDate constructionDate) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.sqMetres = sqMetres;
        this.adress = adress;
        this.description = description;
        this.constructionDate = constructionDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getSqmetres() {
        return sqMetres;
    }

    public void setSqmetres(double sqMetres) {
        this.sqMetres = sqMetres;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }
}
