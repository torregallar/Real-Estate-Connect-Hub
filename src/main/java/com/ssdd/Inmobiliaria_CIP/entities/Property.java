package com.ssdd.Inmobiliaria_CIP.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Property {
    private int id;
    private String name;
    private double price;
    private String type;
    private int rooms;
    private int bathrooms;
    private double sqMetres;
    private String address;
    private String description;


    public Property() {
    }

    public Property(String name, double price, String type, int rooms, int bathrooms, double sqMetres, String address, String description) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.sqMetres = sqMetres;
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getSqMetres() {
        return sqMetres;
    }

    public void setSqMetres(double sqMetres) {
        this.sqMetres = sqMetres;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
