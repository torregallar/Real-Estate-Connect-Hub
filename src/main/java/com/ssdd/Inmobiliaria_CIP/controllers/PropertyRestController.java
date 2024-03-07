package com.ssdd.Inmobiliaria_CIP.controllers;

import com.ssdd.Inmobiliaria_CIP.entities.Property;
import com.ssdd.Inmobiliaria_CIP.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/properties")
public class PropertyRestController {

    @Autowired
    private PropertyService propertyService;

    public PropertyRestController () {}


    @GetMapping
    public ResponseEntity<List<Property>> getProperties() {
        return ResponseEntity.ok().body(propertyService.getProperties());
    }

    @PostMapping("/create")
    public ResponseEntity<Property> createProperty(Property property) {
        Property property1 = propertyService.createProperty(property);
        return ResponseEntity.ok().body(property1);
    }


}
