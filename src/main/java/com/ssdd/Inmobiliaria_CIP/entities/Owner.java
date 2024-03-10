package com.ssdd.Inmobiliaria_CIP.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Owner {
    private int id;
    private String name;
    private String lastName;
    private Date dob; //date of birth
    private String phoneNumber;
    private String email;
    private List<Property> propertyList;

    public Owner() {
    }

    public Owner( String name, String lastName, Date dob, String phoneNumber, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.propertyList = new ArrayList<>();
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }
}
